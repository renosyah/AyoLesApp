package com.syahputrareno975.ayolesapp.model.course

import com.syahputrareno975.ayolesapp.model.BaseModel

class AllCourseRequest : BaseModel {
    var CategoryId = ""
    var SearchBy = "course_name"
    var SearchValue = ""
    var OrderBy = "course_name"
    var OrderDir = "asc"
    var Offset = 0
    var Limit = 10

    constructor()

    constructor(CategoryId: String, SearchBy: String, SearchValue: String, OrderBy: String, OrderDir: String,
        Offset: Int,
        Limit: Int
    ) {
        this.CategoryId = CategoryId
        this.SearchBy = SearchBy
        this.SearchValue = SearchValue
        this.OrderBy = OrderBy
        this.OrderDir = OrderDir
        this.Offset = Offset
        this.Limit = Limit
    }


    fun toSchema() : String {
        return "query { course_list( category_id:\"${CategoryId}\", search_by:\"$SearchBy\", search_value:\"$SearchValue\", order_by:\"$OrderBy\", order_dir:\"$OrderDir\", offset:$Offset, limit:$Limit ) { id, course_name,image_url,teacher{id,name email},category {id,name,image_url},course_details { id,course_id , overview_text, description_text,image_url }}}"
    }
}