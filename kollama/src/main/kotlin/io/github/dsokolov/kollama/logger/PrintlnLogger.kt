package io.github.dsokolov.kollama.logger

import java.util.Date

class PrintlnLogger : Logger {

    private fun log(level: String, s: String) {
        val now = Date()
        println("$now $level: $s")
    }

    override fun i(s: String) {
        log("I", s)
    }

    override fun e(th: Throwable) {
        log("E", th.stackTraceToString())
    }
}