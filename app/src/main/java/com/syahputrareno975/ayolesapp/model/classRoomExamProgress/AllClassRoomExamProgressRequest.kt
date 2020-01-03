package com.syahputrareno975.ayolesapp.model.classRoomExamProgress

import com.syahputrareno975.ayolesapp.model.BaseModel

class AllClassRoomExamProgressRequest : BaseModel {
    var ClassroomId : String = ""
    var OrderBy = "create_at"
    var OrderDir = "asc"
    var Offset = 0
    var Limit = 10

    constructor()

    constructor(ClassroomId: String, OrderBy: String, OrderDir: String, Offset: Int, Limit: Int) {
        this.ClassroomId = ClassroomId
        this.OrderBy = OrderBy
        this.OrderDir = OrderDir
        this.Offset = Offset
        this.Limit = Limit
    }


    fun toSchema() : String {
        return "query {\n" +
                "classroom_exam_progress_list(\n" +
                "classroom_id:\"${ClassroomId}\",\n" +
                "order_by:\"${OrderBy}\",\n" +
                "order_dir:\"${OrderDir}\",\n" +
                "offset:${Offset},\n" +
                "limit:${Limit}\n" +
                ")\n" +
                "{\n" +
                "id,\n" +
                "classroom_id,\n" +
                "course_exam_id,\n" +
                "course_exam_answer_id\n" +
                " }\n" +
                "}"
    }
}