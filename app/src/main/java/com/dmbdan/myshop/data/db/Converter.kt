package com.dmbdan.myshop.data.db

import androidx.room.TypeConverter
import com.dmbdan.myshop.domain.model.Rating

class Converter {

    @TypeConverter
    fun fromRating(rating: Rating): Double {
        return rating.rate
    }

    @TypeConverter
    fun toRating(rate: Double):Rating{
        return Rating(rate.toInt(), rate)
    }
}