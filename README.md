<h1 align="center">Welcome to KMP Searchable Dropdown 👋</h1>

<p align="center">
  <a href="https://developer.android.com/guide/topics/manifest/uses-sdk-element">
    <img src="https://img.shields.io/badge/API-15%2B-blue.svg?style=flat" alt="Minimum API Level" />
  </a>
  <a href="https://maven-badges.herokuapp.com/maven-central/com.example/your-library">
    <img src="https://maven-badges.herokuapp.com/maven-central/com.example/your-library/badge.svg" alt="Maven Central" />
  </a>
  <a href="https://opensource.org/licenses/MIT">
    <img src="https://img.shields.io/badge/License-MIT-yellow.svg" alt="License: MIT" />
  </a>
  <a href="https://android-arsenal.com/">
    <img src="https://img.shields.io/badge/Android%20Arsenal-Searchable%20Dropdown-green.svg?style=flat" alt="Android Arsenal" />
  </a>
</p>


## ✨ Demo

<div style="display: flex; justify-content: center; align-items: center;">
  <img 
    src="https://raw.githubusercontent.com/mejdi14/KMP-Searchable-Dropdown/main/demo/output_demo.gif"
    height="500"
    width="305"
    style="margin-right: 20px;"
  />

</div>


## Installation

Add this to your module's `build.gradle` file (make sure the version matches the JitPack badge
above):

```gradle
dependencies {
	...
	implementation("io.github.mejdi14:KMP-Searchable-Dropdown:0.0.2")
}
```

## :fire:How to use

``` kotlin
/* use your own list of objects
here I used this Data Class as an example:
data class People(
    val name: String,
    val photo: DrawableResource,
    val job: String
)
*/
val people = listOf(
    People("Arij", Res.drawable.student2, "Software engineer"),
    People("Mejdi", Res.drawable.student1, "Software engineer"),
    People("Sami", Res.drawable.student3, "Designer"),
    )
    SearchableDropdown(
        items = people,
        searchSettings = SearchSettings(
            searchProperties = listOf(
                People::name,
                People::job,
            )
        ),
        dropdownConfig = DropdownConfig(shape = RoundedCornerShape(8.dp)),
        itemContentConfig = ItemContentConfig.Default(DefaultDropdownItem<Student>(title = Student::name)),
        ),
    )
```

Search Settings
-----

## Properties Table

## Fields Table for `SearchSettings`

| Field                 | Type                                   | Default Value             | Description                                                                                           |
|-----------------------|----------------------------------------|---------------------------|-------------------------------------------------------------------------------------------------------|
| `searchEnabled`       | `Boolean`                             | `true`                    | Indicates whether the search functionality is enabled (also controles its visibility).                                               |
| `searchProperties`    | `List<KProperty1<T, *>>`              | `emptyList()`             | A list of properties in your object to be searched.                                               |
| `separator`           | `@Composable () -> Unit`              | `{ SearchSeparator() }`   | A separator between the search and the items of the Dropdown.                         |
| `searchIcon`          | `SearchIcon`                          | `SearchIcon()`            | Configuration for the search icon, including its appearance and behavior.                            |
| `searchInput`         | `SearchInput`                         | `SearchInput()`           | Configuration for the search input, such as placeholders or initial text.                            |
| `searchType`          | `SearchType`                          | `SearchType.CONTAINS`     | Defines the search type (e.g., `CONTAINS`, `STARTS_WITH`, etc.).                                      |
| `ignoreCase`          | `Boolean`                             | `true`                    | Determines whether the search is case-insensitive.                                                   |
| `searchActionListener`| `SearchActionListener<T>`             | `object : SearchActionListener<T> { ... }` | Listener for handling search-related actions, such as text changes or results.                       |



Dropdown Config
-----
 ## Fields Table for `DropdownConfig`

| Field                | Type        | Default                     | Description                                                                                      |
|----------------------|-------------|-----------------------------|--------------------------------------------------------------------------------------------------|
| `backgroundColor`    | `Color`     | `Color.White`               | The background color of the dropdown.                                                           |
| `shape`              | `Shape`     | `RoundedCornerShape(20.dp)` | The shape of the dropdown, defining corner radius.                                               |
| `maxHeight`          | `Dp`        | `300.dp`                    | Maximum height of the dropdown.                                                                 |
| `dropdownShadow`     | `DropdownShadow` | `DropdownShadow(...)`   | Configuration for the dropdown's shadow, including its shape and elevation.                     |
| `horizontalPadding`  | `Dp`        | `30.dp`                     | Horizontal padding inside the dropdown.                                                         |
| `headerPlaceholder`  | `@Composable` | `{ Text(...) }`          | Composable function for the dropdown's header placeholder.                                       |
| `withItemSelection`  | `Boolean`   | `true`                      | Indicates if item selection is enabled.                                                         |
| `separationSpace` | `Int`  | `20`           | Space in pixels between the header and dropdown content.                                        |
| `toggleIcon`         | `ToggleIcon` | `ToggleIcon()`             | Configuration for the dropdown toggle icon.                                                     |
| `itemSeparator`      | `DropdownItemSeparator` | `DropdownItemSeparator()` | Configuration for separators between dropdown items.                                            |
| `emptySearchPlaceholder` | `@Composable` | `{ EmptySearchPlaceholder() }` | Composable function displayed when no search results are found.                                 |
| `selectItemActionListener` | `SelectActionListener<T>` | `object : SelectActionListener<T> { ... }` | Listener for handling item selection actions in the dropdown.                                   |



ItemContentConfig Guide
-----


The `ItemContentConfig` class allows you to configure how items in your dropdown are displayed. You can choose between two approaches:

1. **Default Content**: Use a predefined layout for your items with a title, optional subtitle, and optional icon.
2. **Custom Content**: Define completely custom layouts for your items and optionally for the header.

---

## Default Content

To use the default configuration, you can provide a `DefaultDropdownItem`. This is a quick and easy way to display items with a consistent appearance.

<p align="center">
  <img src="https://github.com/mejdi14/KMP-Searchable-Dropdown/blob/main/demo/demo_image.jpg" alt="Default Content Demo" width="400" />
</p>

### Example:

```kotlin
val defaultConfig = ItemContentConfig.Default(
    defaultItem = DefaultDropdownItem(
        title = Person::name,
        subtitle = Person::job,
        withIcon = true
    )
)
```

## Custom Content

If you want full control over how the dropdown items and header are displayed, you can use the `Custom` configuration. This allows you to provide composable functions for both `content` (how each item appears) and `header` (the dropdown header).

### Key Points:
- **`content`**: Defines the layout and styling of each item in the dropdown.
- **`header`** (Optional): Defines the layout and styling of the dropdown header. If not provided, it will default to using the `content`.

### Example:

```kotlin
val customConfig = ItemContentConfig.Custom(
    content = { person, _ ->
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = person.name,
                fontSize = 16.sp
            )
        }
    },
    header = { person, _ ->
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Selected: ${person.name}",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
    }
)
```

## Upcoming Features

Here's what's coming next:

### 🔜 Multiselection

### 🔜 Animations

If you have suggestions or feature requests, feel free to open an issue or contribute to the repository.


## 🤝 Contributing

Contributions, issues and feature requests are welcome.<br />
Feel free to check [issues page] if you want to contribute.<br />

## Author

👤 **Mejdi Hafiane**

- profile: [@MejdiHafiane](https://twitter.com/mejdi141)

## Show your support

Please ⭐️ this repository if this project helped you!

## 📝 License

Copyright © 2019 [Mejdi Hafiane](https://github.com/mejdi14).<br />
This project is [MIT](https://github.com/mejdi14/readme-md-generator/blob/master/LICENSE) licensed.
