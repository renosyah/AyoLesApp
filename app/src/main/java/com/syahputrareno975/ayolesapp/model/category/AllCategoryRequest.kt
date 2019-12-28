package com.syahputrareno975.ayolesapp.model.category

import java.io.Serializable

class AllCategoryRequest : Serializable {
    var SearchBy = "name"
    var SearchValue = ""
    var OrderBy = "name"
    var OrderDir = "asc"
    var Offset = 0
    var Limit = 10

    constructor()

    constructor(SearchBy: String, SearchValue: String, OrderBy: String, OrderDir: String, Offset: Int, Limit: Int) {
        this.SearchBy = SearchBy
        this.SearchValue = SearchValue
        this.OrderBy = OrderBy
        this.OrderDir = OrderDir
        this.Offset = Offset
        this.Limit = Limit
    }

    fun toSchema() : String {
        return "query { category_list( search_by:\"$SearchBy\", search_value:\"$SearchValue\", order_by:\"$OrderBy\", order_dir:\"$OrderDir\", offset:$Offset, limit:$Limit ) { id, name, image_url }}"
    }
}