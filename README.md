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
    width="255"
    style="margin-right: 20px;"
  />

</div>



## Installation

Add this to your module's `build.gradle` file (make sure the version matches the JitPack badge
above):

```gradle
dependencies {
	...
	implementation("io.github.mejdi14:KMP-Searchable-Dropdown:0.1.0")
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


The ItemContentConfig class (and its subtypes) allows you to configure how items in your dropdown are displayed. Depending on your use case, you can choose:

Single Item Selection – Only one item can be chosen.
Multi-Item Selection – Multiple items can be chosen at once.
Within each selection mode, there are two approaches to rendering items:

Default Content: Use a predefined layout with minimal setup.
Custom Content: Fully control the composable layout of your items (and optionally the header).
Below, you’ll find an overview of each approach in a format similar to the one shown for single-item usage.

# Single Item Selection
### Default Content
If you want a quick, predefined appearance (title, optional subtitle, and optional icon), you can pass a DefaultDropdownItem to a Default configuration. This is the easiest way to get started—just map the fields (e.g., title, subtitle) to your data’s properties.

``` kotlin
val defaultConfig = SingleItemContentConfig.Default(
    defaultItem = DefaultDropdownItem(
        title = Person::name,
        subtitle = Person::job,
        withIcon = true
    )
)
```
<p align="center"> <img src="https://github.com/mejdi14/KMP-Searchable-Dropdown/blob/main/demo/demo_image.jpg" alt="Default Content Demo" width="400" /> </p>
Tip: You can hide the subtitle or the icon if you don’t need them by simply not providing those properties.

### Custom Content
For maximum flexibility, use Custom. You’ll define a composable function for the content (how each item appears) and optionally a separate header layout (how the selected item is shown in the closed dropdown state).

``` kotlin
val customConfig = SingleItemContentConfig.Custom(
    content = { person, _ ->
        // Define each item's row layout here
    },
    header = { person, _ ->
        // Define how to show the selected item in the header
    }
)
```

Key Point: If you omit the header parameter, it will use the same composable as content. This is perfect when you want both the dropdown items and the header to look the same.

## Multi-Item Selection
###  Default Content (Multi)
Multi-selection also supports a Default approach. You can still provide something like a DefaultDropdownItem for consistency, but with multiple selections in mind. Additionally, you can tweak multi-selection options—such as the maximum number of items a user can select or whether to show a built-in checkbox.

<img 
    src="https://raw.githubusercontent.com/mejdi14/KMP-Searchable-Dropdown/main/demo/output.gif"
    height="500"
    width="455"
    style="margin-right: 20px;"
  />

``` kotlin
val multipleDefaultConfig = MultipleItemContentConfig.Default(
    defaultItemCustomization = DefaultDropdownItem(
        title = Person::name,
        subtitle = Person::job,
        withIcon = true
    ),
    options = MultipleItemOptions(
        selectionMaxCount = 3,       // For example, limit to 3 selections
        useDefaultSelector = true    // Enable built-in checkboxes/icons
    )
)
```

Info: This gives you a quick setup where each selected item is managed automatically, and the dropdown shows a checkbox or icon by default.

###  Custom Content (Multi)
When you need full control over each item’s layout (including how you indicate “selected” vs. “not selected”), as well as how selected items appear in the header, choose Custom.

``` kotlin
val multipleCustomConfig = MultipleItemContentConfig.Custom(
    content = { person, isSelected, multipleSelectActionListener ->
        // Define how each item row should look,
        // and call `onSelect` or `onDeselect` on click.
    },
    header = { person, selectedPerson, removeItemListener ->
        // Define how each selected item appears
        // in the header (e.g. chips or horizontal list).
    },
    options = MultipleItemOptions(
        selectionMaxCount = 5,
        useDefaultSelector = false
    )
)
```



You receive isSelected for each item, so you can visually reflect the selection state.

The multipleSelectActionListener helps you handle toggling (select/deselect) with a simple function call.

The header composable is called for each selected item if you want to display them (like chips or icons) above the list.
Extras: MultipleItemOptions
For multi-selection specifically, the options parameter lets you control various behaviors:

selectionMaxCount: Prevents users from selecting more than a certain number of items.
useDefaultSelector: Adds a built-in checkbox or icon next to each item.
defaultSelectorPosition: Positions that icon on the start or end of the item row.
defaultCheckboxParams: Styles the checkbox if useDefaultSelector is true.

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
