package me.ilich.kollama

import me.ilich.kollama.domain.model.OllamaModelName
import me.ilich.kollama.domain.model.OllamaModelShort
import me.ilich.kollama.domain.model.OllamaModelDetails
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class OllamaModelTest {

    @Test
    fun `test OllamaModelName creation and access`() {
        val modelName = OllamaModelName("llama2")
        assertEquals("llama2", modelName.model)
    }

    @Test
    fun `test OllamaModelShort creation`() {
        val modelName = OllamaModelName("llama2")
        val modelShort = OllamaModelShort(modelName)
        
        assertEquals("llama2", modelShort.name.model)
    }

    @Test
    fun `test OllamaModelDetails creation with all fields`() {
        val modelDetails = OllamaModelDetails(
            modelFile = "llama2.gguf",
            parameters = "7B"
        )
        
        assertEquals("llama2.gguf", modelDetails.modelFile)
        assertEquals("7B", modelDetails.parameters)
    }

    @Test
    fun `test OllamaModelDetails creation with null fields`() {
        val modelDetails = OllamaModelDetails(
            modelFile = null,
            parameters = null
        )
        
        assertNull(modelDetails.modelFile)
        assertNull(modelDetails.parameters)
    }

    @Test
    fun `test OllamaModelName equality`() {
        val modelName1 = OllamaModelName("llama2")
        val modelName2 = OllamaModelName("llama2")
        val modelName3 = OllamaModelName("llama3")
        
        assertEquals(modelName1, modelName2)
        assertNotEquals(modelName1, modelName3)
    }
} 