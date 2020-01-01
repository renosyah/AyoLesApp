package com.syahputrareno975.ayolesapp.model.courseMaterial

import com.syahputrareno975.ayolesapp.model.BaseModel

class AllCourseMaterialRequest : BaseModel {
    var CourseId = ""
    var SearchBy = "title"
    var SearchValue = ""
    var OrderBy = "material_index"
    var OrderDir = "asc"
    var Offset = 0
    var Limit = 10

    constructor()

    constructor(CourseId: String, SearchBy: String, SearchValue: String, OrderBy: String, OrderDir: String,Offset: Int,Limit: Int) {
        this.CourseId = CourseId
        this.SearchBy = SearchBy
        this.SearchValue = SearchValue
        this.OrderBy = OrderBy
        this.OrderDir = OrderDir
        this.Offset = Offset
        this.Limit = Limit
    }


    fun toSchema() : String {
        return "query { course_material_list( course_id:\"${CourseId}\", search_by:\"$SearchBy\", search_value:\"$SearchValue\", order_by:\"$OrderBy\", order_dir:\"$OrderDir\", offset:$Offset, limit:$Limit ) { id, course_id, material_index, title }}"
    }
}