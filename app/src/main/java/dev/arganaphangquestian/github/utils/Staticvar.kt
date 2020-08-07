package dev.arganaphangquestian.github.utils

import dev.arganaphangquestian.github.ui.favourite.FavouriteFragment
import dev.arganaphangquestian.github.ui.home.HomeFragment
import dev.arganaphangquestian.github.ui.setting.SettingFragment
import java.util.*

val BOTTOMMENU = arrayListOf(
    HomeFragment.newInstance(),
    FavouriteFragment.newInstance(),
    SettingFragment.newInstance()
)

const val SETREMINDER = "SETREMINDER"

const val REMINDERID = 100

val REMINDERTIME: Calendar = Calendar.getInstance().apply {
    timeInMillis = System.currentTimeMillis()
    set(Calendar.HOUR_OF_DAY, 9)
    set(Calendar.MINUTE, 0)
    set(Calendar.SECOND, 0)
}