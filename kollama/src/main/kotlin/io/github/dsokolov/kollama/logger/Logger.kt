package io.github.dsokolov.kollama.logger

interface Logger {
    fun i(s: String)
    fun e(th: Throwable)
}