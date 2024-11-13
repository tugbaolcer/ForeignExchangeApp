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
        title = "Güncel Döviz Kurları",
        description = "Anlık güncellemelerle döviz kurlarını takip edin.\n" +
                "İhtiyaç duyduğunuz tüm para birimlerini elinizin altında bulun.\n" +
                "Döviz fiyatlarını kaçırmadan işlem yapın!",
        image = R.drawable.onboarding_one
    ),
    OnboardingData(
        title = "Kişiselleştirilmiş Bildirimler",
        description = "Hedef kur seviyenizi belirleyin, size haber verelim!\n" +
                "Favori para birimlerinizi seçin, anında takip edin.\n" +
                "Döviz değişikliklerini kaçırmamak için özelleştirilmiş bildirimler alın.",
        image = R.drawable.onboarding_two
    ),
    OnboardingData(
        title = "Kolay ve Hızlı Kullanım",
        description = "Kullanıcı dostu arayüz ile işlemlerinizi hızla gerçekleştirin.\n" +
                "Döviz bilgilerini kolayca analiz edin ve karşılaştırın.\n" +
                "Tüm döviz ihtiyacınız için tek bir platform kullanın!",
        image = R.drawable.onboarding_three
    )
)
