package com.syahputrareno975.ayolesapp.model.courseDetail

import com.syahputrareno975.ayolesapp.model.BaseModel

class AllCourseDetailRequest : BaseModel {
    var CourseId = ""
    var SearchBy = "overview_text"
    var SearchValue = ""
    var OrderBy = "overview_text"
    var OrderDir = "asc"
    var Offset = 0
    var Limit = 10

    constructor()

    constructor(CourseId: String, SearchBy: String, SearchValue: String, OrderBy: String, OrderDir: String,
                Offset: Int,
                Limit: Int
    ) {
        this.CourseId = CourseId
        this.SearchBy = SearchBy
        this.SearchValue = SearchValue
        this.OrderBy = OrderBy
        this.OrderDir = OrderDir
        this.Offset = Offset
        this.Limit = Limit
    }


    fun toSchema() : String {
        return "query { course_detail_list( course_id:\"${CourseId}\", search_by:\"$SearchBy\", search_value:\"$SearchValue\", order_by:\"$OrderBy\", order_dir:\"$OrderDir\", offset:$Offset, limit:$Limit ) { id,course_id , overview_text, description_text,image_url }}"
    }
}