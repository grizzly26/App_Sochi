package com.example.app_sochi.ui.utils

import androidx.compose.runtime.Composable
import com.example.app_sochi.R



@Composable
fun getImageForFish(fishName: String): Int {
    return when (fishName) {
        "Белая акула" -> R.drawable.great_white_shark
        "Тигровая акула" -> R.drawable.tiger_shark
        "Молотоголовая акула" -> R.drawable.hammerhead_shark
        "Электрический скат" -> R.drawable.electric_ray
        "Манта" -> R.drawable.manta_ray
        "Шиповатый скат" -> R.drawable.stingray
        "Речной окунь" -> R.drawable.river_perch
        "Морской окунь" -> R.drawable.sea_bass
        "Атлантический лосось" -> R.drawable.atlantic_salmon
        "Чавыча" -> R.drawable.chinook_salmon
        "Горбуша" -> R.drawable.pink_salmon
        "Обыкновенная щука" -> R.drawable.northern_pike
        "Амурская щука" -> R.drawable.amur_pike
        "Жёлтый тунец" -> R.drawable.yellowfin_tuna
        "Большеглазый тунец" -> R.drawable.bigeye_tuna
        "Синий тунец" -> R.drawable.bluefin_tuna
        "Зеркальный карп" -> R.drawable.mirror_carp
        "Чешуйчатый карп" -> R.drawable.scaly_carp
        "Кои" -> R.drawable.koi
        "Европейский сом" -> R.drawable.european_catfish
        "Канальный сом" -> R.drawable.channel_catfish
        "Плоскоголовый сом" -> R.drawable.flathead_catfish
        else -> R.drawable.placeholder // Используйте изображение по умолчанию
    }
}
