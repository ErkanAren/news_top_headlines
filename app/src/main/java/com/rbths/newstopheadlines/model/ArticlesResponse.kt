package com.rbths.newstopheadlines.model

import com.squareup.moshi.Json

data class ArticlesResponse(
    @Json(name = "status") val status: String,
    @Json(name = "totalResults") val totalResults: Int?,
    @Json(name = "articles") val articles: List<Article>?
)

