package com.syahputrareno975.ayolesapp.ui.activity.certificate_view

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.print.PrintAttributes
import android.print.PrintManager
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.syahputrareno975.ayolesapp.R
import com.syahputrareno975.ayolesapp.di.component.DaggerActivityComponent
import com.syahputrareno975.ayolesapp.di.module.ActivityModule
import com.syahputrareno975.ayolesapp.model.classRoom.ClassRoomModel
import com.syahputrareno975.ayolesapp.model.classRoomCertificate.OneClassRoomCertificateRequest
import com.syahputrareno975.ayolesapp.model.classRoomCertificate.OneClassRoomCertificateResponse
import com.syahputrareno975.ayolesapp.service.RetrofitService
import kotlinx.android.synthetic.main.activity_certificate.*
import javax.inject.Inject


class CertificateActivity : AppCompatActivity(),CertificateActivityContract.View {

    @Inject
    lateinit var presenter: CertificateActivityContract.Presenter

    lateinit var context: Context
    lateinit var classRoomModel : ClassRoomModel
    var urlCert = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_certificate)
        initWidget()
    }

    fun initWidget(){
        context = this@CertificateActivity

        classRoomModel = intent.getSerializableExtra("data") as ClassRoomModel

        title_certificate_textview.text = "${classRoomModel.Course.CourseName}'s Certificate"
        back_imageview.setOnClickListener {
            finish()
        }

        injectDependency()
        presenter.attach(this)
        presenter.subscribe()

        loading_web.visibility = View.VISIBLE
        certificate_webview.visibility = View.GONE

        settingWebview()
        presenter.getOneClassRoomCertificate(OneClassRoomCertificateRequest(classRoomModel.Id))
    }

    @SuppressLint("SetJavaScriptEnabled")
    fun settingWebview(){
        certificate_webview.settings.javaScriptEnabled = true
        certificate_webview.settings.loadWithOverviewMode = true
        certificate_webview.settings.useWideViewPort = true
        certificate_webview.webViewClient = object : WebViewClient(){
            override fun onPageFinished(view: WebView?, url: String?) {
                loading_web.visibility = View.GONE
                certificate_webview.visibility = View.VISIBLE
                super.onPageFinished(view, url)
            }
        }
    }
    private fun createWebPrintJob(webView: WebView) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val printManager = (context as Activity).getSystemService(Context.PRINT_SERVICE) as PrintManager
            val jobname = "${classRoomModel.Course.CourseName}_certificate"
            val printAdapter = webView.createPrintDocumentAdapter(jobname)

            val attrib = PrintAttributes.Builder()
                .setMediaSize(PrintAttributes.MediaSize.UNKNOWN_LANDSCAPE)
                .build()

            printManager.print(jobname,printAdapter,attrib)

        } else {

            AlertDialog.Builder(context)
                .setTitle("Cannot Print")
                .setMessage("Sorry, your device is not support for print this certificate directly, would you like to open and print it via browser?")
                .setPositiveButton("Yes") { dialog, which ->

                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("$urlCert?print=yes"))
                    startActivity(browserIntent)

                    dialog.dismiss()

                }.setNegativeButton("No"){dialog, which ->
                    dialog.dismiss()

                }.setCancelable(false)
                .create()
                .show()
        }
    }
    override fun showProgress(show: Boolean) {

    }

    override fun showError(error: String) {

    }

    override fun onGetOneClassRoomCertificate(s: OneClassRoomCertificateResponse) {
        urlCert = "${RetrofitService.baseURL}cert/${s.Data.ClassRoomCertificateDetail.HashId}"
        print_certificate.setOnClickListener {
           createWebPrintJob(certificate_webview)
        }
        certificate_webview.loadUrl(urlCert)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unsubscribe()
    }


    private fun injectDependency(){
        val listcomponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .build()

        listcomponent.inject(this)
    }
}
