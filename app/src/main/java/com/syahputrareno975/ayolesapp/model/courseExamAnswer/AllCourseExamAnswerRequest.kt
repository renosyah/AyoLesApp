package com.syahputrareno975.ayolesapp.model.courseExamAnswer

import com.syahputrareno975.ayolesapp.model.BaseModel

class AllCourseExamAnswerRequest : BaseModel {
    var CourseExamId = ""
    var SearchBy = "text"
    var SearchValue = ""
    var OrderBy = "exam_index"
    var OrderDir = "asc"
    var Offset = 0
    var Limit = 10

    constructor()

    constructor(CourseExamId: String, SearchBy: String, SearchValue: String, OrderBy: String, OrderDir: String,
                Offset: Int, Limit: Int
    ) {
        this.CourseExamId = CourseExamId
        this.SearchBy = SearchBy
        this.SearchValue = SearchValue
        this.OrderBy = OrderBy
        this.OrderDir = OrderDir
        this.Offset = Offset
        this.Limit = Limit
    }


    fun toSchema() : String {
        return "query {\n" +
                "course_exam_answer_list(\n" +
                "course_exam_id : \"${CourseExamId}\",\n" +
                "search_by:\"${SearchBy}\",\n" +
                "search_value:\"${SearchValue}\",\n" +
                "order_by:\"${OrderBy}\",\n" +
                "order_dir:\"${OrderDir}\",\n" +
                "offset:${Offset},\n" +
                "limit:${Limit}\n" +
                ")\n" +
                "{\n" +
                "id,\n" +
                "course_exam_id,\n" +
                "type_answer,\n" +
                "label,\n" +
                "text,\n" +
                "image_url\n" +
                "}\n" +
                "} "
    }
}