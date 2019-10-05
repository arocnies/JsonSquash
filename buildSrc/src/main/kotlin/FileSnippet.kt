package dev.nies.template

import java.io.File

class FileSnippet(private val fileRoot: String) : Snippet("file") {
    override fun transform(string: String): String {
        return File("$fileRoot/$string").readText()
    }
}