package com.tugbaolcer.foreignexchangeapp.data.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CryptoDto(
    val result: List<Result>,
    val success: Boolean
): Parcelable {
    @Parcelize
    data class Result(
        val changeDay: Double,
        val changeDaystr: String,
        val changeHour: Double,
        val changeHourstr: String,
        val changeWeek: Double,
        val changeWeekstr: String,
        val circulatingSupply: String,
        val code: String,
        val currency: String,
        val marketCap: Double,
        val marketCapstr: String,
        val name: String,
        val price: Double,
        val pricestr: String,
        val volume: Double,
        val volumestr: String
    ): Parcelable
}

//fun CryptoDto.toCrypto():List<Crypto>{
//    return result.map { result -> Crypto(
//        name = result.name,
//        pricestr = result.pricestr,
//        currency = result.currency,
//        code = result.code
//    ) }
//}