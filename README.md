# Ragdoll Cosmetic Armor Reworked Forked Compatibility Fix

A client-side compatibility bridge for Minecraft 1.21.1 that makes
[Sable Ragdolls](https://github.com/Leo-T22/sable-player-ragdoll) respect
[Cosmetic Armor Reworked Forked](https://github.com/zzik2/CosmeticArmorReworkedForked)
rendering settings.

## Behavior

- Cosmetic armor replaces equipped armor on player ragdolls.
- Hidden cosmetic slots render no armor.
- Disabling Cosmetic Armor restores equipped armor rendering.
- `CosArmorStackRendering` keeps equipped armor and adds the cosmetic layer.
- Chest-slot decisions are also applied to elytra and cape rendering.

## Requirements

- Minecraft 1.21.1
- NeoForge 21.1.249
- Sable Ragdolls 0.8.1 (0.8.x) and its required Sable library
- Cosmetic Armor Reworked Forked 1.21.1-0.0.4 or a compatible 0.0.x release
- Architectury API 13.0.8 or newer

This bridge only needs to be installed on the client.

## Installation

Install the required mods, then place this mod's JAR in the client `mods` directory.

## Building

Java 21 is required.

On Windows:

```powershell
.\gradlew.bat clean build
```

On Linux or macOS:

```bash
./gradlew clean build
```

The resulting JAR is written to `build/libs/`.

## Credits

- Original mod by [TanukiBear](https://github.com/TanukiBearMan/RagdollCosmeticArmorReworkedForkedCompatibilityFix).
- Updated for Sable Ragdolls 0.8.1 by [mic9371](https://github.com/mic9371).
- [Sable: Ragdolls](https://modrinth.com/mod/sable-ragdolls) by Leonardoinc22 and
  [Cosmetic Armor Reworked Forked](https://github.com/zzik2/CosmeticArmorReworkedForked) by their respective authors.

## License

This project is licensed under the [GNU General Public License v3.0](LICENSE).
