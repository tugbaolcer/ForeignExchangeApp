package com.tugbaolcer.foreignexchangeapp.data.network.dto

import android.os.Parcelable
import com.google.gson.annotations.JsonAdapter
import com.tugbaolcer.foreignexchangeapp.data.network.adapter.SafeDoubleAdapter
import kotlinx.parcelize.Parcelize

@Parcelize
data class StockDto(
    val result: List<StockDtoItem>
) : Parcelable {
    @Parcelize
    data class StockDtoItem(
        val code: String?,
        @JsonAdapter(SafeDoubleAdapter::class)
        val max: Double?,
        @JsonAdapter(SafeDoubleAdapter::class)
        val hacim: Double?,
        @JsonAdapter(SafeDoubleAdapter::class)
        val min: Double?,
        @JsonAdapter(SafeDoubleAdapter::class)
        val rate: Double?,
        val text: String?,
        val time: String?
    ) : Parcelable
}