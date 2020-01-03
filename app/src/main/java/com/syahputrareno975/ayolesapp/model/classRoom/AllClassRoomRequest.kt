package com.syahputrareno975.ayolesapp.model.classRoom

import com.syahputrareno975.ayolesapp.model.BaseModel

class AllClassRoomRequest : BaseModel {
    var StudentId = ""
    var SearchBy = "course.course_name"
    var SearchValue = ""
    var OrderBy = "course.create_at"
    var OrderDir = "asc"
    var Offset = 0
    var Limit = 10

    constructor()

    constructor(StudentId: String, SearchBy: String, SearchValue: String, OrderBy: String, OrderDir: String, Offset: Int, Limit: Int) {
        this.StudentId = StudentId
        this.SearchBy = SearchBy
        this.SearchValue = SearchValue
        this.OrderBy = OrderBy
        this.OrderDir = OrderDir
        this.Offset = Offset
        this.Limit = Limit
    }

    fun toSchema() : String {
        return "query {\n" +
                "classroom_list(\n" +
                "student_id:\"${StudentId}\",\n" +
                "search_by:\"${SearchBy}\",\n" +
                "search_value:\"${SearchValue}\",\n" +
                "order_by:\"${OrderBy}\",\n" +
                "order_dir:\"${OrderDir}\",\n" +
                "offset:${Offset},\n" +
                "limit:${Limit}\n" +
                ")\n" +
                "{\n" +
                "id,\n" +
                "course {\n" +
                "id,\n" +
                "course_name,\n" +
                "image_url,\n" +
                "teacher {id, name, email } ,\n" +
                "category {id, name, image_url},\n" +
                "course_details { id,course_id , overview_text, description_text,image_url }\n" +
                "},\n" +
                "student_id\n" +
                "}\n" +
                "}"
    }


}