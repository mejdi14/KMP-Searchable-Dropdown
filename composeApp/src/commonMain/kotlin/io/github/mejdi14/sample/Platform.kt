package io.github.mejdi14.sample

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform