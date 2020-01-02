package com.syahputrareno975.ayolesapp.model.courseExam

import com.syahputrareno975.ayolesapp.model.BaseModel

class AllCourseExamRequest  : BaseModel {
    var CourseId = ""
    var SearchBy = "text"
    var SearchValue = ""
    var OrderBy = "exam_index"
    var OrderDir = "asc"
    var Offset = 0
    var Limit = 10
    var LimitAnswer = 4

    constructor()

    constructor(CourseId: String, SearchBy: String, SearchValue: String, OrderBy: String, OrderDir: String,
                Offset: Int, Limit: Int,LimitAnswer : Int
    ) {
        this.CourseId = CourseId
        this.SearchBy = SearchBy
        this.SearchValue = SearchValue
        this.OrderBy = OrderBy
        this.OrderDir = OrderDir
        this.Offset = Offset
        this.Limit = Limit
        this.LimitAnswer = LimitAnswer
    }


    fun toSchema() : String {
        return "query {\n" +
                "course_exam_list(\n" +
                "course_id : \"${CourseId}\",\n" +
                "search_by:\"${SearchBy}\",\n" +
                "search_value:\"${SearchValue}\",\n" +
                "order_by:\"${OrderBy}\",\n" +
                "order_dir:\"${OrderDir}\",\n" +
                "offset:${Offset},\n" +
                "limit:${Limit},\n" +
                "limit_answer:${LimitAnswer}" +
                ")\n" +
                "{\n" +
                "id,\n" +
                "course_id,\n" +
                "type_exam,\n" +
                "exam_index,\n" +
                "text,\n" +
                "image_url,\n" +
                "answers {\n" +
                "id,\n" +
                "course_exam_id,\n" +
                "type_answer,\n" +
                "label,\n" +
                "text,\n" +
                "image_url\n" +
                "}\n" +
                "}\n" +
                "} "
    }
}