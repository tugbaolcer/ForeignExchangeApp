package com.tugbaolcer.foreignexchangeapp.presentation.onboarding

import androidx.annotation.DrawableRes
import com.tugbaolcer.foreignexchangeapp.R

data class OnboardingData(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

val pages = listOf(
    OnboardingData(
        title = "Secure",
        description = "Quick money transfer in wallet",
        image = R.drawable.onboarding_one
    ),
    OnboardingData(
        title = "Real Time",
        description = "Exchange different currencies easily",
        image = R.drawable.onboarding_two
    ),
    OnboardingData(
        title = "Keep Control",
        description = "Always track your transactions",
        image = R.drawable.onboarding_three
    )
)
