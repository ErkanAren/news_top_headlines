package com.rbths.newstopheadlines.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class Utils {

    companion object{
        /**
         * This function returns a Long value representing the number of milliseconds since the Unix epoch, it takes a string in ISO8601 format
         * The function first checks if the input string is null or empty, and if so, returns 0L (zero milliseconds).
         * It will also return 0L if the dateString has wrong format
         */
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

        /**
         * This function returns a string representing the same date and time in a different format (more user friendly format), it takes a dateString as a parameter in ISO8601 format
         * It will return an empty string if dateString is null
         * It will also return an empty string when the dateString has wrong format or it is empty
         */
        fun getDateStringFromISO8601(dateString: String?): String {
            if (dateString==null) return ""
            val cal = Calendar.getInstance()
            val tz = cal.timeZone

            /* date formatter in local timezone */
            val sdf = SimpleDateFormat(Constants.DATE_FULL_ISO8601, Locale.US)//"EEE, dd MMM yyyy HH:mm:ss zzz"

            sdf.timeZone = TimeZone.getTimeZone("GMT")

            var localTime = ""
            try {
                val localDate = sdf.parse(dateString)
                val sdf2 = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US)
                sdf2.timeZone = tz
                localTime = localDate?.let { sdf2.format(it) }.toString()
            } catch (e: ParseException) {
                e.printStackTrace()
            }

            return localTime
        }
    }



}