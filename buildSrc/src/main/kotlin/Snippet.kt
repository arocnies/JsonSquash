package dev.nies.template

abstract class Snippet(name: String) {
    private val regex = Regex("@$name\\(([^)]*)\\)")

    abstract fun transform(string: String): String

    fun resolve(input: String): String {
        return input.replace(regex) { match ->
            transform(match.groupValues[1])
        }
    }
}