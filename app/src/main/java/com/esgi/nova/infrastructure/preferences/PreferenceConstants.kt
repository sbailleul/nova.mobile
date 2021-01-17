package com.esgi.nova.infrastructure.preferences

import com.esgi.nova.application_state.application.IsSynchronized

object PreferenceConstants {
    object User{
        const val UsernameKey = "username"
        const val TokenKey = "token"
        const val Key = "user"
        const val PasswordKey = "password"
    }
    object ApplicationState{
        const val Key = "applicationState"
        const val IsSynchronizedKey = "isSynchronized"
    }

    object Parameters{
        const val Key = "parameters"
        const val IsDarkModeKey = "isDarkMode"
        const val HasDailyEventsKey = "hasDailyEvents"
        const val HasNotificationsKey = "hasNotifications"
        const val HasMusicKey = "hasMusic"
    }

}