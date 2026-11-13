# Copper Deco — NeoForge 26.3

Prepared source port from the working `26.2-neo` branch, retaining the **Carpets Core** dependency and all 32 copper tile variants. The original face-placement logic remains in `com.ludwici.carpetscore.api.block.NoGravityVariantBlock`; Copper Deco does not replace it.

## Requirements

- Minecraft 26.3
- NeoForge 26.3.0.99 (or a compatible later 26.3 build)
- Java 25
- A **NeoForge 26.3 build** of Carpets Core

## Set up the Carpets Core version

The 26.3 dependency is now pinned to the [Carpets Core NeoForge 26.3 CurseForge file](https://www.curseforge.com/minecraft/mc-mods/carpets-core/files/9578858):

```properties
carpetcore_file_id=9578858
carpetcore_version=1.3.1
```

Update the file ID and minimum version only when publishing a newer Carpets Core build; CurseForge file IDs and mod versions are different values.

The Gradle dependency and runtime dependency declaration both remain required. The code keeps the library's `NoGravityVariantBlock` inheritance. No separate geometry implementation is included.

## Links

- [CurseForge](https://www.curseforge.com/minecraft/mc-mods/copper-deco) — mod downloads
- [Source code](https://github.com/ludwici/CopperDeco) — GitHub repository
- [Issue tracker](https://github.com/ludwici/CopperDeco/issues) — bug reports and requests

## Build and status

After updating the library dependency, run `./gradlew clean build` locally. `runData` writes to `build/generated/datagen/` so that the committed block models, loot tables, recipes, and tags in `src/generated/resources/` are not overwritten.

GitHub Actions builds this branch with the NeoForge 26.3 Carpets Core JAR from CurseForge and checks the packaged metadata and resources. The build result is reported by CI. If the Carpets Core API changes later, matching source adjustments may be required.
