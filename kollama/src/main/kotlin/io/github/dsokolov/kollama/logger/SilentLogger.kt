package io.github.dsokolov.kollama.logger

class SilentLogger : Logger {
    override fun i(s: String) = Unit
    override fun e(th: Throwable) = Unit
}
