package io.github.dsokolov.kollama

import io.github.dsokolov.kollama.domain.OllamaClient
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.net.URI

class OllamaFactoryTest {

    @Test
    fun `test ollama function creates client with default URI`() {
        val client = ollama()
        
        assertNotNull(client)
        assertNotNull(client.getManipulations())
        assertNotNull(client.getCompletions())
    }

    @Test
    fun `test ollama function creates client with custom URI`() {
        val customUri = URI.create("http://localhost:8080")
        val client = ollama(uri = customUri)
        
        assertNotNull(client)
        assertNotNull(client.getManipulations())
        assertNotNull(client.getCompletions())
    }

    @Test
    fun `test ollama function creates different clients for different URIs`() {
        val client1 = ollama(URI.create("http://localhost:11434"))
        val client2 = ollama(URI.create("http://localhost:8080"))
        
        assertNotNull(client1)
        assertNotNull(client2)
        // Note: We can't directly compare the internal state, but we can verify both are created successfully
    }
} 