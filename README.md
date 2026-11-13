# Copper Deco — NeoForge 26.3

Prepared source port from the working `26.2-neo` branch, retaining the **Carpets Core** dependency and all 32 copper tile variants. The original face-placement logic remains in `com.ludwici.carpetscore.api.block.NoGravityVariantBlock`; Copper Deco does not replace it.

## Requirements

- Minecraft 26.3
- NeoForge 26.3.0.99 (or a compatible later 26.3 build)
- Java 25
- A **NeoForge 26.3 build** of Carpets Core

## Set up the Carpets Core version

When your 26.3 Carpets Core build is available, edit `gradle.properties`:

```properties
carpetcore_file_id=REPLACE_WITH_26_3_NEO_CURSEFORGE_FILE_ID
carpetcore_version=1.3.1
```

Replace `carpetcore_file_id` with the numeric **CurseForge file ID** of the NeoForge 26.3 library artifact. Set `carpetcore_version` to its minimum supported **mod version**; this is used in `neoforge.mods.toml`. A CurseForge file ID and the library's semantic version are different values.

The Gradle dependency and runtime dependency declaration both remain required. The code keeps the library's `NoGravityVariantBlock` inheritance. No separate geometry implementation is included.

## Links

- [CurseForge](https://www.curseforge.com/minecraft/mc-mods/copper-deco) — mod downloads
- [Source code](https://github.com/ludwici/CopperDeco) — GitHub repository
- [Issue tracker](https://github.com/ludwici/CopperDeco/issues) — bug reports and requests

## Build and status

After updating the library dependency, run `./gradlew clean build` locally. `runData` writes to `build/generated/datagen/` so that the committed block models, loot tables, recipes, and tags in `src/generated/resources/` are not overwritten.

**This 26.3 branch is source-only and is not build-verified.** CI workflows are intentionally absent from the branch. If the updated Carpets Core API also changes, its corresponding methods may require additional adjustments.
