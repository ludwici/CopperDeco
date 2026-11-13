# Copper Deco — Fabric 26.3

Source-only port based on the working `26.2-fabric` branch with the **Carpets Core dependency retained**. All 32 copper tile variants, recipes, block models, tags, loot tables, oxidation and waxing logic are preserved. The block placement implementation still comes from `com.ludwici.carpetscore.api.block.NoGravityVariantBlock`.

## Requirements

- Minecraft 26.3
- Fabric Loader 0.19.5 (or compatible newer version)
- Fabric API 0.165.2+26.3
- Java 25
- A **Fabric 26.3 build** of Carpets Core

## Set up the Carpets Core version

The 26.3 dependency is now pinned to the [Carpets Core Fabric 26.3 CurseForge file](https://www.curseforge.com/minecraft/mc-mods/carpets-core/files/9578816):

```properties
carpetcore_file_id=9578816
carpetcore_version=1.3.1
```

Update the file ID and minimum version only when publishing a newer Carpets Core build; CurseForge file IDs and mod versions are different values.

The Gradle dependency remains required, and the source continues to use the library's `NoGravityVariantBlock`. No internal geometry replacement has been added.

## Links

- [CurseForge](https://www.curseforge.com/minecraft/mc-mods/copper-deco) — mod downloads
- [Source code](https://github.com/ludwici/CopperDeco) — GitHub repository
- [Issue tracker](https://github.com/ludwici/CopperDeco/issues) — bug reports and requests

## Build and status

After updating these properties, build locally using `./gradlew clean build`. Resources are packaged from `src/generated/resources`.

GitHub Actions builds this branch with the Fabric 26.3 Carpets Core JAR from CurseForge and checks the packaged metadata and resources. The build result is reported by CI. Future Carpets Core API changes may require source adjustments.
