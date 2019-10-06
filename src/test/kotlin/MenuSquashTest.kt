import com.beust.klaxon.JsonObject
import java.io.StringReader
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class MenuSquashTest {
    private lateinit var squashedJson: Map<String, JsonObject>

    @BeforeTest fun `Squash menu JSON`() {
        val menuJson = this::class.java.getResource("menu/unsquashed_menu.json5").readText()
        squashedJson = DataSquash().squash(StringReader(menuJson))
    }

    @Test fun `has no nested objects`() {
        assert(squashedJson.values.all { resultingObject ->
            resultingObject.values.none { it is JsonObject }
        })
    }

    @Test fun `has menu object`() {
        assert(squashedJson.containsKey("menu"))
        assertEquals("file", squashedJson["menu"]?.get("id"))
        assertEquals("File", squashedJson["menu"]?.get("value"))
    }

    @Test fun `has popup object`() {
        assert(squashedJson.containsKey("popup"))
    }
}