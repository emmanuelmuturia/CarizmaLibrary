package emmanuelmuturia.commons

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform