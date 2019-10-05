package dev.nies.template

import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File

class TemplateSnippetsPlugin : Plugin<Project> {
    private val snippets = mutableListOf<Snippet>()
    private lateinit var snippetsRoot: String

    override fun apply(project: Project) {
        initialize(project)

        project.tasks.create("templateSnippets") { task ->
            task.doLast {
                getSnippetFiles(project).forEach { file -> file.resolveTemplate() }
            }
        }
    }

    private fun initialize(project: Project) {
        if (!this::snippetsRoot.isInitialized) snippetsRoot = project.projectDir.path
        snippets.add(FileSnippet(snippetsRoot))
    }

    private fun getSnippetFiles(project: Project): List<File> {
        return project.rootDir.listFiles { file: File -> file.extension == "template" }?.toList() ?: emptyList()
    }

    private fun File.resolveTemplate() {
        val resolvedText = snippets.fold(readText()) { text, snippet ->
            snippet.resolve(text)
        }
        File(this.nameWithoutExtension).writeText(resolvedText)
    }
}



