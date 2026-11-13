# Copper Deco

Decorative copper tiles for **Minecraft 26.2 / NeoForge**. Tiles can be placed on floors, walls and ceilings, and retain copper weathering and waxing interactions.

Features:
- 32 copper tile variants: four block families, four oxidation stages, and waxed or unwaxed forms.
- Oxidation over time, scraping with an axe, waxing with honeycomb, and lightning cleaning.
- Crafting and stonecutting recipes, creative inventory entries and mining tags.

## Requirements

- Minecraft **26.2**
- NeoForge **26.2.0.88** or newer within the 26.2 series
- [Carpets Core](https://www.curseforge.com/minecraft/mc-mods/carpets-core) **1.3.1** (NeoForge build for 26.2)

## Links

- [CurseForge](https://www.curseforge.com/minecraft/mc-mods/copper-deco) — mod page and downloads
- [Source code](https://github.com/ludwici/CopperDeco) — repository
- [Issue tracker](https://github.com/ludwici/CopperDeco/issues) — bug reports and feature requests

## Build

Use **Java 25** and run `./gradlew clean build`. The mod JAR will be created under `build/libs/`. To regenerate block models, loot tables, tags and recipes, run `./gradlew runData`.

[![NeoForge 26.2 CI](https://github.com/ludwici/CopperDeco/actions/workflows/build.yml/badge.svg?branch=26.2-neo)](https://github.com/ludwici/CopperDeco/actions/workflows/build.yml)

The GitHub Actions workflow builds the mod, validates data generation, and checks that the NeoForge `iconFile` and homepage/issue-tracker links resolve correctly in the packaged JAR.
