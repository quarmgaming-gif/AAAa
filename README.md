# CARB CLIENT — Fabric 1.21.4

Starter project, Java 21, Fabric Loader + Fabric API.

## Controls
- `K` — open CARB CLIENT GUI
- Combat tab: left-click Kill Aura to toggle
- Combat tab: right-click Kill Aura to open settings
- Drag the Range slider from 1.0 to 6.0 blocks
- `ESC` — return from settings

## Build
1. Install JDK 21.
2. Open the folder in IntelliJ IDEA or use Gradle.
3. Run `gradle build` (or `./gradlew build` after adding the Gradle wrapper).
4. The mod JAR will be in `build/libs/`.

## Included
- Neon purple click GUI with 10 category tabs.
- Kill Aura module and range settings screen.
- Catalog of 100 planned modules in `ModuleCatalog.java`.

## Important
Only Kill Aura is implemented as a working module in this starter. The other 99 catalog entries are names/placeholders and need separate implementations. Use gameplay-altering features only in private worlds/servers where you have permission. This source has not been compiled or tested in a Minecraft launch in this environment.
