package com.rbths.newstopheadlines.model

import android.os.Parcelable
import androidx.room.Entity
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

// parcelable article response, it is parcelable so it can be passed through fragments using SafeArgs
@Parcelize
data class ArticlesResponse(
    @Json(name = "status") val status: String?,
    @Json(name = "totalResults") val totalResults: Int?,
    @Json(name = "articles") val articles: List<Article>?
) : Parcelable

