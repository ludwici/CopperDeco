# Copper Deco — Forge 1.20.1

Port of Copper Deco 1.0.3 for Minecraft 1.20.1 / Forge 47.3.1 (Java 17).

## Required mods

- [Carpets Core 1.1.0 for Forge 1.20.1](https://www.curseforge.com/minecraft/mc-mods/carpets-core)
- [Vanilla Backport 1.2.2 for Forge 1.20.1](https://www.curseforge.com/minecraft/mc-mods/vanillabackport/files/9318528)

All 32 copper tile variants, including Chiseled Copper and Copper Grate from Vanilla Backport, are registered under the original `copperdeco` IDs. Copper tiles support oxidation, waxing, axe scraping, lightning cleaning, waterlogging, and independent face placement via Carpets Core.

The 1.21.1 models are retained. The `preparePortResources` task converts static JSON to the 1.20.1 paths and recipe syntax during builds without overwriting the source assets.

Run `./gradlew clean build`. GitHub Actions builds and verifies the JAR when the library release file ID is available.

Build Carpets Core 1.1.0 from its `1.20.1` branch (`./gradlew :forge:build`), then copy `forge/build/libs/carpetscore-forge-1.20.1-1.1.0.jar` to this repository's `libs/` directory. GitHub CI performs these steps automatically until the new CurseForge file has finished processing.
