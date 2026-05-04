# Tunnelex - FPS Boost Fabric Mod

🚀 Enhance your Minecraft gameplay with FPS boosts for PvP, Bedwars, and Skyblock!

## Features

✅ **FPS Boost System** - Dynamically increase FPS during gameplay  
✅ **AFK Detection** - Get +30 FPS boost when AFK for 2 minutes  
✅ **Combat Mode** - Additional FPS boost during PvP battles  
✅ **Double AFK System** - FPS drops to 10 when both mod & Minecraft AFK timers are active  
✅ **In-Game Config Menu** - Press `Y` to customize all settings  
✅ **Persistent Settings** - All configurations saved to `config/tunnelex.json`  
✅ **No Cheats** - Completely legitimate performance enhancement  

## Installation

1. Download the latest release from [GitHub Releases](https://github.com/ItsN4x/Tunnelex/releases)
2. Place `tunnelex-1.0.0.jar` in your `mods/` folder
3. Make sure you have [Fabric Loader](https://fabricmc.net/) and [Fabric API](https://modrinth.com/mod/fabric-api) installed
4. Launch Minecraft with the Fabric profile

## Usage

### Open Configuration Menu
- **Press `Y`** in-game to open the configuration screen

### Configuration Options
- **FPS Boost**: Enable/disable FPS boosting
- **AFK Detection**: Enable/disable AFK detection and boost
- **Combat Boost**: Enable/disable combat mode FPS boost
- **Double AFK**: Enable/disable double AFK mode (mod + Minecraft AFK)
- **AFK Threshold**: Set how long (in seconds) before being marked AFK
- **AFK FPS Boost**: Amount of FPS to add when AFK
- **Combat FPS Boost**: Amount of FPS to add in combat
- **Low FPS Cap**: FPS cap when double AFK is active

## How It Works

### AFK Detection
- Detects when you stop moving for the configured time (default: 120 seconds)
- Grants you a +30 FPS boost while AFK
- FPS returns to normal when you resume moving

### Combat Mode
- Automatically activates when you engage in PvP
- Provides +20 FPS boost during combat
- Deactivates after 5 seconds of inactivity in combat

### Double AFK Mode
- When both your mod AFK timer AND Minecraft's AFK timer are active
- FPS is reduced to 10 for energy efficiency
- Useful for extended AFK sessions

## Requirements

- Minecraft 1.20.1
- Fabric Loader (0.15.3+)
- Java 17+

## Configuration File

Settings are saved in: `%APPDATA%/.minecraft/config/tunnelex.json`

```json
{
  "fpsBoostEnabled": true,
  "afkDetectionEnabled": true,
  "combatBoostEnabled": true,
  "doubleAFKDetectionEnabled": true,
  "afkThresholdSeconds": 120,
  "afkFPSBoost": 30,
  "combatFPSBoost": 20,
  "lowFPSCapForDoubleAFK": 10
}
```

## License

MIT License - See LICENSE file for details

## Support

For issues, suggestions, or feedback, visit [GitHub Issues](https://github.com/ItsN4x/Tunnelex/issues)

---

Enjoy better FPS without compromising fairness! 🎮