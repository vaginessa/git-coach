package com.vishal2376.gitcoach.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.vishal2376.gitcoach.R

object LoadSettings {

    fun checkNotificationSwitch(context: Context): Boolean {
        return context.getSharedPreferences(Constants.NOTIFICATION, MODE_PRIVATE)
            .getBoolean(Constants.NOTIFICATION_SWITCH, false)
    }

    fun getNotificationTime(context: Context): String? {
        return context.getSharedPreferences(Constants.NOTIFICATION, MODE_PRIVATE)
            .getString(Constants.NOTIFICATION_TIME, "09:00")
    }

    fun loadTheme(context: Context) {
        //load saved values
        val sp =
            context.getSharedPreferences("SETTINGS", MODE_PRIVATE).getString("user_theme", "yellow")

        when (sp.toString()) {
            "red" -> {
                context.setTheme(R.style.Theme_RED)
            }

            "blue" -> {
                context.setTheme(R.style.Theme_YELLOW)
            }

            "green" -> {
                context.setTheme(R.style.Theme_GREEN)
            }

            else -> {
                context.setTheme(R.style.Theme_GitCoach)
            }
        }
    }
}