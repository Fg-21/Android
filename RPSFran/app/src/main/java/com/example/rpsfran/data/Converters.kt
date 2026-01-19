package com.example.rpsfran.data

import androidx.room.TypeConverter
import com.example.rpsfran.domain.model.GameChoice
import com.example.rpsfran.domain.model.GameResult

class Converters {

    @TypeConverter
    fun fromGameChoice(value: GameChoice): String {
        return value.name
    }

    @TypeConverter
    fun toGameChoice(value: String): GameChoice {
        return GameChoice.valueOf(value)
    }

    @TypeConverter
    fun fromGameResult(value: GameResult): String {
        return value.name
    }

    @TypeConverter
    fun toGameResult(value: String): GameResult {
        return GameResult.valueOf(value)
    }
}