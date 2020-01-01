package com.syahputrareno975.ayolesapp.model.courseMaterialDetail

import com.syahputrareno975.ayolesapp.model.BaseModel

class AllCourseMaterialDetailRequest : BaseModel {
    var CourseMaterialId = ""
    var SearchBy = "title"
    var SearchValue = ""
    var OrderBy = "position"
    var OrderDir = "asc"
    var Offset = 0
    var Limit = 10

    constructor()

    constructor(CourseMaterialId: String, SearchBy: String, SearchValue: String, OrderBy: String, OrderDir: String, Offset: Int, Limit: Int) {
        this.CourseMaterialId = CourseMaterialId
        this.SearchBy = SearchBy
        this.SearchValue = SearchValue
        this.OrderBy = OrderBy
        this.OrderDir = OrderDir
        this.Offset = Offset
        this.Limit = Limit
    }


    fun toSchema() : String {
        return "query {\n" +
                "course_material_detail_list(\n" +
                "course_material_id : \"${CourseMaterialId}\",\n" +
                "search_by:\"${SearchBy}\",\n" +
                "search_value:\"${SearchValue}\",\n" +
                "order_by:\"${OrderBy}\",\n" +
                "order_dir:\"${OrderDir}\",\n" +
                "offset:${Offset},\n" +
                "limit:${Limit}\n" +
                ")\n" +
                "{\n" +
                "id,\n" +
                "course_material_id,\n" +
                "position,\n" +
                "title,\n" +
                "type_material,\n" +
                "content,\n" +
                "image_url\n" +
                "}" +
                "}"
    }
}