package com.syahputrareno975.ayolesapp.model.classRoomExamResult

import com.syahputrareno975.ayolesapp.model.BaseModel

class AllClassRoomExamResultRequest : BaseModel {
    var ClassRoomId : String = ""
    var CourseExamId : String = ""
    var SearchBy : String = "course_exam.text"
    var SearchValue : String = ""
    var OrderBy  : String = "classroom_exam_progress.create_at"
    var OrderDir : String = "asc"
    var Offset : Int = 0
    var Limit  : Int = 10
    var LimitAnswer :Int = 4

    constructor()

    constructor(ClassRoomId: String, CourseExamId: String, SearchBy: String, SearchValue: String, OrderBy: String, OrderDir: String, Offset: Int, Limit: Int, LimitAnswer: Int) {
        this.ClassRoomId = ClassRoomId
        this.CourseExamId = CourseExamId
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
                "classroom_exam_result_list(\n" +
                "classroom_id : \"${ClassRoomId}\",\n" +
                "search_by:\"${SearchBy}\",\n" +
                "search_value:\"${SearchValue}\",\n" +
                "order_by:\"${OrderBy}\",\n" +
                "order_dir:\"${OrderDir}\",\n" +
                "offset:${Offset},\n" +
                "limit:${Limit},\n" +
                "limit_answer:${LimitAnswer}\n" +
                ")\n" +
                "{\n" +
                "course_exam_id,\n" +
                "course_id,\n" +
                "classroom_id,\n" +
                "student_answer_id,\n" +
                "valid_answer_id,\n" +
                "type_exam,\n" +
                "exam_index,\n" +
                "text,\n" +
                "image_url\n" +
                "answers {\n" +
                "id,\n" +
                "course_exam_id,\n" +
                "type_answer,\n" +
                "label,\n" +
                "text,\n" +
                "image_url\n" +
                "}\n" +
                "}\n" +
                "}"
    }

}