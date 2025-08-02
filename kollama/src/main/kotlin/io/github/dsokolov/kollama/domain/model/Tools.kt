package io.github.dsokolov.kollama.domain.model


typealias FunctionName = String
typealias ParamName = String

data class Tool(
    val functionName: FunctionName,
    val description: String?,
    val params: List<Param>,
)

data class Param(
    val name: ParamName,
    val type: ParamType,
    val description: String?,
    val isRequired: Boolean,
)

enum class ParamType {
    STRING,
}

class CreateToolsContext {

    private val tools = mutableListOf<Tool>()

    fun function(
        name: FunctionName,
        description: String? = null,
        block: (CreateFunctionContext.() -> Unit)? = null
    ) {
        val functionContext = CreateFunctionContext(
            name = name,
            description = description
        )
        if (block != null) {
            functionContext.block()
        }
        val tool = functionContext.build()
        tools.add(tool)
    }

    fun build(): List<Tool> {
        return tools
    }
}

class CreateFunctionContext(
    private val name: FunctionName,
    private val description: String?,
) {

    val params = mutableListOf<Param>()

    fun param(name: ParamName, type: ParamType, description: String? = null, isRequired: Boolean = true) {
        val param = Param(name, type, description, isRequired)
        params.add(param)
    }

    fun build(): Tool {
        return Tool(name, description, params)
    }
}

fun tools(block: CreateToolsContext.() -> Unit): List<Tool> {
    val context = CreateToolsContext()
    context.block()
    return context.build()
}


@JvmInline
value class ToolName(val toolName: String)

@JvmInline
value class ArgumentName(val argumentName: String)

@JvmInline
value class ArgumentValue(val argumentValue: String)

data class ToolCall(
    val name: ToolName,
    val arguments: Map<ArgumentName, ArgumentValue>
)
