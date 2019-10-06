import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import java.io.Reader

class JsonSquash {
    private val parser: Parser = Parser.default()

    /**
     * Reads a JSON object and flattens all nested objects and arrays.
     */
    fun squash(reader: Reader): Map<String, JsonObject> {
        val rootJson: JsonObject = parser.parse(reader) as JsonObject
        return extractNestedObjects(rootJson)
    }

    /**
     * Recursively extracts nested [JsonObject]s and removes them from their parent object.
     * @return All extracted JSON objects in the tree
     */
    private fun extractNestedObjects(unsquashed: JsonObject): Map<String, JsonObject> {
        @Suppress("UNCHECKED_CAST")
        val nestedJsons: MutableMap<String, JsonObject> =
            unsquashed.filterValues { it is JsonObject } as MutableMap<String, JsonObject>

        nestedJsons.map { (name, nestedObject) ->
            unsquashed.remove(name)
            nestedJsons += extractNestedObjects(nestedObject)
        }

        return nestedJsons
    }
}