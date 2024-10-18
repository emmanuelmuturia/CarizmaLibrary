package emmanuelmuturia.sonux.samples

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform