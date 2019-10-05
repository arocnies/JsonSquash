Squash your nested data into flat structures!

DataSquash takes hierarchical data and produces the flatten components for that data.
This is particularly useful when trying to visualize data in Kibana, which does not fully support nested objects [LINK TO ISSUE].
```json5
{
  "menu": {
    "id": "file",
    "value": "File",
    "popup": {
      "menuitem": [
        {
          "value": "New",
          "onclick": "CreateNewDoc()"
        },
        {
          "value": "Open",
          "onclick": "OpenDoc()"
        },
        {
          "value": "Close",
          "onclick": "CloseDoc()"
        }
      ]
    }
  }
}
```
Gets squashed into...
```json5
{
  "_menu_id": "123", // Generated values start with "_"
  "menu_id": "file", // Keys are prefixed with their owning object's key
  "menu_value": "File"
}
```
```json5
{
  "_menu_id": "123", // All attributes propagate to child objects
  "menu_Id": "file",
  "menu_value": "File",
  "_popup_Id": "123"
}
```
```json5
{
  "_menu_id": "123",
  "menu_Id": "file",
  "menu_value": "File",
  "_popup_Id": "123",
  "_menuitem_id": "123",
  "_menuitem_index": "1", // List index
  "menuitem_value": "New",
  "menuitem_onclick": "CreateNewDoc()"
}
```