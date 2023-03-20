package com.rbths.newstopheadlines.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "source")
data class Source(
    @PrimaryKey @SerializedName("id") val id : String?,
    @SerializedName("name") val name : String?,
    @SerializedName("description") val descriptionSource:String?,
    @SerializedName("url") val urlSource : String?,
    @SerializedName("language") val language : String?,
    @SerializedName("country") val country : String?
    ) : Parcelable

