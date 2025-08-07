# kollama: Ollama Kotlin Library

Kotlin library for [ollama](https://ollama.com/)

Inspired by [Ollama Python Library](https://github.com/ollama/ollama-python)

## Usage

Add this dependency to module-level `build.gradle.kts`:

```kotlin
dependencies {
    implementation("io.github.dsokolov:kollama:0.2")
}
```

and aks AI about sky color:

```kotlin
fun main() = runBlocking {
    val ollama = ollama()
    val models = ollama.manipulations().models()
    if (models.isEmpty()) {
        print("No models installed")
    } else {
        val model = models.first()
        val generation = ollama.completions(model.name).generate("Why is the sky blue?")
        println(generation.response)
    }
}
```

## Links

* Ollama REST API https://github.com/ollama/ollama/blob/main/docs/api.md


## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.