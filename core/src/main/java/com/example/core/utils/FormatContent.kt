package com.example.core.utils


import java.text.SimpleDateFormat
import java.util.*

object FormatContent {

    fun ParsingFormatDate(releaseDate:String):String{
        val apiFormat = SimpleDateFormat("yyyy-MM-dd",Locale.getDefault())
        val MoviesimpleDataFormat = apiFormat.parse(releaseDate)
        val dateFormat = SimpleDateFormat("EEEE, dd-MMMM-yyyy",Locale.getDefault())
        return dateFormat.format(MoviesimpleDataFormat)
    }
}