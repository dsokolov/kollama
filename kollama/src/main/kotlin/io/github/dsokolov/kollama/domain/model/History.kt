package io.github.dsokolov.kollama.domain.model

data class History(
    val messages: List<Message>
)

operator fun History.plus(message: Message) =
    History(
        messages = this.messages + message
    )

fun history(block: HistoryCreateContext.() -> Unit): History {
    val context = HistoryCreateContext()
    context.block()
    val messages = context.messages
    return History(messages)
}

class HistoryCreateContext {
    val messages = mutableListOf<Message>()

    fun system(content: MessageContent) {
        val msg = Message(
            role = MessageRole.System,
            content = content,
        )
        messages.add(msg)
    }

    fun user(content: MessageContent) {
        val msg = Message(
            role = MessageRole.User,
            content = content,
        )
        messages.add(msg)
    }
}