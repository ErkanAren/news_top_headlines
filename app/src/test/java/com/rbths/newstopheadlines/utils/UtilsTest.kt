package com.rbths.newstopheadlines.utils

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class UtilsTest{

    /***
     * getLongFromISO8601
     */
    @Test
    fun `the empty string of date returns 0 in getLongFromISO8601`(){
        val result = Utils.getLongFromISO8601("")
        assertThat(result).isEqualTo(0)
    }

    @Test
    fun `the ISIO8601 string of date returns the correct long value in getLongFromISO8601`(){
        val result = Utils.getLongFromISO8601("2023-03-01T14:22:27.0000000Z")
        assertThat(result).isEqualTo(1677680547000)
    }

    @Test
    fun `the wrong format string of date returns 0 in getLongFromISO8601`(){
        val result = Utils.getLongFromISO8601("2023-03-01T14:22:27")
        assertThat(result).isEqualTo(0)
    }

    @Test
    fun `null value instead of string of date returns 0 in getLongFromISO8601`(){
        val result = Utils.getLongFromISO8601(null)
        assertThat(result).isEqualTo(0)
    }

    /***
     * getDateStringFromISO8601
     */
    @Test
    fun `the empty string of date returns empty string in getDateStringFromISO8601`(){
        val result = Utils.getDateStringFromISO8601("")
        assertThat(result).isEqualTo("")
    }

    @Test
    fun `the ISIO8601 string of date returns the correct long value in getDateStringFromISO8601`(){
        val result = Utils.getDateStringFromISO8601("2023-03-01T14:22:27.0000000Z")
        assertThat(result).isEqualTo("2023-03-01 16:22")
    }

    @Test
    fun `the wrong format string of date returns empty string in getDateStringFromISO8601`(){
        val result = Utils.getDateStringFromISO8601("2023-03-01T14:22:27")
        assertThat(result).isEqualTo("")
    }

    @Test
    fun `null instead of string of date returns empty string in getDateStringFromISO8601`(){
        val result = Utils.getDateStringFromISO8601(null)
        assertThat(result).isEqualTo("")
    }
}