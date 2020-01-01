package com.syahputrareno975.ayolesapp.model.banner

import com.syahputrareno975.ayolesapp.model.BaseModel

class AllBannerRequest : BaseModel  {
    var SearchBy = "title"
    var SearchValue = ""
    var OrderBy = "title"
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
        return "query { banner_list( search_by:\"$SearchBy\", search_value:\"$SearchValue\", order_by:\"$OrderBy\", order_dir:\"$OrderDir\", offset:$Offset, limit:$Limit ) { id, title, content, image_url }}"
    }
}