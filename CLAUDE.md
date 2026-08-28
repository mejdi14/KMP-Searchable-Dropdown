# CLAUDE.md

Guidance for working in this repository.

## What this is

A **Compose Multiplatform searchable dropdown library** plus a demo app.
Targets: **Android, iOS, Desktop (JVM), and Web (wasmJs)**.

- `searchableDropdown/` — the published library (Maven group `io.github.mejdi14`, artifact `KMP-Searchable-Dropdown`). All library code lives in `commonMain`.
- `composeApp/` — the demo/sample app that consumes the library. Not published.

## Build environment (important)

The machine default `java` is JDK 26, which **Gradle 8.9 cannot parse** (build fails with a bare version string like `26.0.1`). Build with Android Studio's bundled JBR 21:

```bash
export JAVA_HOME="/Applications/Android Studio.app/Contents/jbr/Contents/Home"
```

Android Studio itself already uses this JDK, so builds from the IDE are fine; only command-line Gradle needs the export.

## Common commands

Fast verification (most library code is in `commonMain`, so the desktop target exercises it):

```bash
./gradlew :searchableDropdown:compileKotlinDesktop
./gradlew :composeApp:compileKotlinDesktop
```

Per-target compile: swap the suffix — `compileDebugKotlinAndroid`, `compileKotlinWasmJs`, `compileKotlinIosSimulatorArm64`.

Run the demo:
- Desktop: `./gradlew :composeApp:run`
- Web: `./gradlew :composeApp:wasmJsBrowserDevelopmentRun` (serves on `http://localhost:8080`; a `web` config also exists in `.claude/launch.json`)
- Android/iOS: run from Android Studio / Xcode.

There is currently **no test source set**.

## Package conventions

- Library code: `io.github.mejdi14.searchabledropdown.*` (Android namespace matches).
- Demo app code: `io.github.mejdi14.sample.*` (namespace + applicationId match).
- Generated Compose resources package (`kmp_searchable_dropdown.<module>.generated.resources.*`) derives from the **root project name + module name**, not the Kotlin package — renaming packages does not affect it.

## Library architecture

Public entry point: `SearchableDropdown(...)` in `searchableDropdown/.../ui/SearchableDropDown.kt`.

Rendering is driven by a sealed `ItemContentConfig<T>`:
- `SingleItemContentConfig` — `.Default` (uses `DefaultDropdownItem`) or `.Custom`. Single-select writes to a `MutableState<T?>` and shows the selection in the header.
- `MultipleItemContentConfig` — `.Default` or `.Custom`, plus `MultipleItemOptions` (checkbox selector, `selectionMaxCount`, position). Multi-select tracks a `SnapshotStateList<T>` and renders chips in the header.

Supporting pieces:
- `DropdownConfig` / `SearchSettings` — the two config data classes (colors, shape, shadow, search behavior, listeners).
- `data/listener/` — `DropdownActionListener`, `SearchActionListener`, `MultipleSelectActionListener`, `MultipleRemoveItemListener`.
- `helper/SearchHelper.kt` — filtering (`filterOperation`, `matchesQuery`) over `KProperty1` search properties with `SearchType`.
- `helper/SelectionHelper.kt` — `selectWithLimit` and the `multipleSelectActionListener` factory that enforce `selectionMaxCount`.

The dropdown content renders inside a Compose `Popup`, so it overlays and does not affect the header's layout height.

## Conventions / gotchas

- **Do not add code comments.** No explanatory comments, no KDoc, no "why" notes in new or edited code. Write self-explanatory names instead. Only exception: a comment the user explicitly asks for.
- Keep the library's public API in `io.github.mejdi14.searchabledropdown`; the module directory must mirror the package path.
- Publishing coordinates live in `gradle.properties` (`GROUP`, `POM_ARTIFACT_ID`); signing is gated behind `RELEASE_SIGNING_ENABLED`.
- When changing the demo app's `applicationId`, uninstall the old package from the device first (`adb uninstall <old-id>`) — a stale install causes "Activity class does not exist" launch errors.
- The library is a library: it has no app entry point / `compose.desktop.application` block. Don't add one.
- iOS `MainViewControllerKt` (the ObjC-exported name used from `iosApp` Swift) derives from the Kotlin **file name**, not the package.
