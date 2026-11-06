# Copper Deco — Fabric 26.3

Source-only port based on the working `26.2-fabric` branch with the **Carpets Core dependency retained**. All 32 copper tile variants, recipes, block models, tags, loot tables, oxidation and waxing logic are preserved. The block placement implementation still comes from `com.ludwici.carpetscore.api.block.NoGravityVariantBlock`.

## Requirements

- Minecraft 26.3
- Fabric Loader 0.19.5 (or compatible newer version)
- Fabric API 0.165.2+26.3
- Java 25
- A **Fabric 26.3 build** of Carpets Core

## Set up the Carpets Core version

When your 26.3 Carpets Core library is available, edit `gradle.properties`:

```properties
carpetcore_file_id=REPLACE_WITH_26_3_FABRIC_CURSEFORGE_FILE_ID
carpetcore_version=1.3.1
```

Replace `carpetcore_file_id` with the numeric **CurseForge file ID** for the Fabric 26.3 artifact. Set `carpetcore_version` to the minimum supported **mod version**; this value populates the `carpetscore` dependency requirement in `fabric.mod.json`. The file ID and the mod version are different numbers.

The Gradle dependency remains required, and the source continues to use the library's `NoGravityVariantBlock`. No internal geometry replacement has been added.

## Build and status

After updating these properties, build locally using `./gradlew clean build`. Resources are packaged from `src/generated/resources`.

**Not build-verified:** CI is deliberately omitted for this branch. Any additional 26.3 changes to the Carpets Core API must be accounted for in the new library version.
