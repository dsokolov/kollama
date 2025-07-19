package me.ilich.kollama.me.ilich.kollama.domain

import me.ilich.kollama.domain.model.OllamaModelDetails
import me.ilich.kollama.domain.model.OllamaModelName
import me.ilich.kollama.domain.model.OllamaModelShort
import me.ilich.kollama.domain.model.OllamaVersion

interface OllamaManipulations {

    /**
     * Get the version of the Ollama server
     *
     * @return The version information
     */
    suspend fun version(): OllamaVersion

    /**
     * Get a list of available models
     *
     * @return List of available models
     */
    suspend fun models(): List<OllamaModelShort>

    /**
     * Get detailed information about a specific model
     *
     * @param modelName The name of the model
     * @param verbose Whether to include verbose information
     * @return Detailed model information
     */
    suspend fun model(modelName: OllamaModelName, verbose: Boolean? = null): OllamaModelDetails

    /**
     * Pull a model from the library
     *
     * @param modelName The name of the model to pull
     * @param insecure Whether to allow insecure connections
     */
    suspend fun pull(modelName: String, insecure: Boolean = false)

    /**
     * Push a model to the library
     *
     * @param modelName The name of the model to push
     * @param insecure Whether to allow insecure connections
     */
    suspend fun push(modelName: String, insecure: Boolean = false)

    /**
     * Create a model from a Modelfile
     *
     * @param name The name for the new model
     * @param modelfile The Modelfile content
     * @param path Optional path to the model
     */
    suspend fun create(name: String, modelfile: String, path: String? = null)

    /**
     * Copy a model
     *
     * @param source The source model name
     * @param destination The destination model name
     */
    suspend fun copy(source: String, destination: String)

    /**
     * Delete a model
     *
     * @param modelName The name of the model to delete
     */
    suspend fun delete(modelName: String)
}