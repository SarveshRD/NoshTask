package org.example.noshtask

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform