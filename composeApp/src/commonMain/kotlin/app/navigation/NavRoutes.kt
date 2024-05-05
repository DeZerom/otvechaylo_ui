package app.navigation

enum class NavRoutes {
    AUTH, REGISTRATION;

    val route get() = when (this) {
        AUTH -> "auth"
        REGISTRATION -> "registration"
    }
}
