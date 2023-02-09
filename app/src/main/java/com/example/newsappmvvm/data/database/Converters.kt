package com.example.newsappmvvm.data.database

import androidx.room.TypeConverter
import com.example.newsappmvvm.data.dto.Source


class Converters {

    @TypeConverter
    fun fromSource(source: Source):String{
        return source.name
    }

    @TypeConverter
    fun toSource(name:String):Source{
        return Source(
            name,name
        )
    }
}