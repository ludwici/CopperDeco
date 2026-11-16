# Copper Deco — Fabric 1.20.1

Port of Copper Deco 1.0.3 for Minecraft 1.20.1 / Fabric (Java 17).

## Required mods

- [Carpets Core 1.1.0 for Fabric 1.20.1](https://www.curseforge.com/minecraft/mc-mods/carpets-core)
- [Vanilla Backport 1.2.2 for Fabric 1.20.1](https://www.curseforge.com/minecraft/mc-mods/vanillabackport/files/9318538)
- [Fabric API 0.92.2+1.20.1](https://www.curseforge.com/minecraft/mc-mods/fabric-api)

All 32 copper tile variants are registered under stable `copperdeco` IDs. Chiseled Copper and Copper Grate come from Vanilla Backport. The gameplay includes copper oxidation, waxing and scraping, waterlogging, multi-face placement, and lightning cleaning.

Assets are retained from the original mod. `preparePortResources` converts recipes, advancements, loot tables, and tag paths for Minecraft 1.20.1 during the build.

Use `./gradlew clean build` after placing the Carpets Core 1.1.0 Fabric distribution JAR in `libs/`. GitHub CI builds this library directly from its source branch while new CurseForge files undergo review.
