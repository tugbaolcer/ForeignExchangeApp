package com.tugbaolcer.foreignexchangeapp.data.network.adapter

import com.google.gson.*
import java.lang.reflect.Type

class SafeDoubleAdapter : JsonDeserializer<Double?> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Double? {
        return try {
            json?.asString?.toDoubleOrNull()
        } catch (e: Exception) {
            null
        }
    }
}