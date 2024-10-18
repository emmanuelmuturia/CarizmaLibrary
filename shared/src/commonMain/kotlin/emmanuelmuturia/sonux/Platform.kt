package emmanuelmuturia.sonux

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform