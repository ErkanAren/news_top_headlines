package com.rbths.newstopheadlines.utils

import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class Utils {

    companion object{
        // we use it to sort our articles
        fun getLongFromISO8601(dateString:String?) : Long {
            if (dateString.isNullOrEmpty()) return 0L
            var milliseconds:Long = 0
            val f = SimpleDateFormat(Constants.DATE_FULL_ISO8601, Locale.US)

            f.timeZone = TimeZone.getTimeZone("UTC")//calendar.timeZone//

            try {
                val d = f.parse(dateString)
                milliseconds = d!!.time
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return milliseconds
        }
    }


    //we use it to show it to user
    fun getDateStringFromISO8601(noteDate: String?): String {
        if (noteDate==null) return ""
        val cal = Calendar.getInstance()
        val tz = cal.timeZone

        /* date formatter in local timezone */
        val sdf = SimpleDateFormat(Constants.DATE_FULL_ISO8601, Locale.US)//"EEE, dd MMM yyyy HH:mm:ss zzz"

        sdf.timeZone = TimeZone.getTimeZone("GMT")

        var localTime = ""
        try {
            val localDate = sdf.parse(noteDate)
            val sdf2 = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US)
            sdf2.timeZone = tz
            localTime = localDate?.let { sdf2.format(it) }.toString()
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return localTime
    }
}