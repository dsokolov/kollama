# Kollama

Kotlin client library for Ollama API

## Features

- Full Ollama API support
- Kotlin-first design
- Coroutines support
- Type-safe models
- Comprehensive error handling

## Installation

Add the dependency to your `build.gradle.kts`:

```kotlin
dependencies {
    implementation("io.github.dsokolov:kollama:0.1.0")
}
```

## Quick Start

```kotlin
import io.github.dsokolov.kollama.ollama
import io.github.dsokolov.kollama.completions
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val client = ollama()
    val completions = client.completions("llama2")
    
    val response = completions.chat(
        history = history(),
        message = Message(role = MessageRole.User, content = "Hello!")
    )
    
    println(response.content)
}
```

## Error Handling

The library provides comprehensive error handling for HTTP errors. When the Ollama API returns an error status code, the library throws `OllamaHttpException` with detailed information.

### HTTP Error Handling

```kotlin
import io.github.dsokolov.kollama.data.OllamaHttpException
import io.github.dsokolov.kollama.data.OllamaException

try {
    val completions = client.completions("non-existent-model")
    val response = completions.chat(
        history = history(),
        message = Message(role = MessageRole.User, content = "Hello!")
    )
    println("Response: ${response.content}")
} catch (e: OllamaHttpException) {
    when (e.statusCode) {
        400 -> println("Bad Request: ${e.message}")
        404 -> println("Model not found: ${e.message}")
        500 -> println("Server error: ${e.message}")
        else -> println("HTTP ${e.statusCode}: ${e.message}")
    }
} catch (e: OllamaException) {
    println("Ollama error: ${e.message}")
} catch (e: Exception) {
    println("Unexpected error: ${e.message}")
}
```

### Common HTTP Status Codes

- **400 Bad Request**: Invalid request parameters (e.g., invalid model name, malformed request)
- **404 Not Found**: Model or resource not found
- **500 Internal Server Error**: Server-side error

### Exception Hierarchy

- `OllamaException`: Base exception for all Ollama-related errors
- `OllamaHttpException`: Exception for HTTP errors with status code and message

## Examples

See the [examples](kollama-examples/src/main/kotlin/io/github/dsokolov/kollama/examples/) directory for more usage examples.

## License

MIT License - see [LICENSE](LICENSE) file for details.