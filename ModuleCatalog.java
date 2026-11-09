package pl.carbclient.module;

import java.util.List;

/** Full planned 100-module catalog. Most entries are names/placeholders, not implemented features yet. */
public final class ModuleCatalog {
    private ModuleCatalog() {}

    public record Entry(String category, String name) {}

    public static final List<Entry> ALL = List.of(
        new Entry("COMBAT","Kill Aura"), new Entry("COMBAT","Aim Assist"),
        new Entry("COMBAT","Trigger Bot"), new Entry("COMBAT","Auto Clicker"),
        new Entry("COMBAT","Criticals"), new Entry("COMBAT","Reach Display"),
        new Entry("COMBAT","Hitbox Display"), new Entry("COMBAT","Target HUD"),
        new Entry("COMBAT","CPS Counter"), new Entry("COMBAT","Auto Weapon"),
        new Entry("MOVEMENT","Sprint"), new Entry("MOVEMENT","Toggle Sprint"),
        new Entry("MOVEMENT","Toggle Sneak"), new Entry("MOVEMENT","Auto Jump"),
        new Entry("MOVEMENT","Safe Walk"), new Entry("MOVEMENT","Step"),
        new Entry("MOVEMENT","Speed"), new Entry("MOVEMENT","Fly - testowy"),
        new Entry("MOVEMENT","No Slow"), new Entry("MOVEMENT","Motion HUD"),
        new Entry("RENDER","Fullbright"), new Entry("RENDER","ESP"),
        new Entry("RENDER","Tracers"), new Entry("RENDER","Name Tags"),
        new Entry("RENDER","Custom Crosshair"), new Entry("RENDER","Chams"),
        new Entry("RENDER","Item ESP"), new Entry("RENDER","Chest ESP"),
        new Entry("RENDER","Mob ESP"), new Entry("RENDER","Entity Highlight"),
        new Entry("PLAYER","Auto Tool"), new Entry("PLAYER","Inventory HUD"),
        new Entry("PLAYER","Coordinates"), new Entry("PLAYER","Potion Effects"),
        new Entry("PLAYER","Keystrokes"), new Entry("PLAYER","FPS Counter"),
        new Entry("PLAYER","Armor HUD"), new Entry("PLAYER","Durability HUD"),
        new Entry("PLAYER","Ping Display"), new Entry("PLAYER","Player Statistics"),
        new Entry("WORLD","Block Finder"), new Entry("WORLD","Ore Finder"),
        new Entry("WORLD","Cave Finder"), new Entry("WORLD","Waypoints"),
        new Entry("WORLD","World Time"), new Entry("WORLD","Biome Display"),
        new Entry("WORLD","Block Coordinates"), new Entry("WORLD","Chunk Borders"),
        new Entry("WORLD","Light Level"), new Entry("WORLD","World Information"),
        new Entry("EXPLOITS","Reach Tester"), new Entry("EXPLOITS","Hit Delay Tester"),
        new Entry("EXPLOITS","Knockback Tester"), new Entry("EXPLOITS","Movement Tester"),
        new Entry("EXPLOITS","Attack Range Tester"), new Entry("EXPLOITS","Click Delay Tester"),
        new Entry("EXPLOITS","Packet Monitor"), new Entry("EXPLOITS","Latency Tester"),
        new Entry("EXPLOITS","Rotation Tester"), new Entry("EXPLOITS","Collision Tester"),
        new Entry("HUD","Custom HUD"), new Entry("HUD","Array List"),
        new Entry("HUD","Module List"), new Entry("HUD","Watermark"),
        new Entry("HUD","Target Info"), new Entry("HUD","Speed Display"),
        new Entry("HUD","Session Timer"), new Entry("HUD","Direction HUD"),
        new Entry("HUD","Coordinates HUD"), new Entry("HUD","Server Info HUD"),
        new Entry("UTILITY","Friend List"), new Entry("UTILITY","Enemy List"),
        new Entry("UTILITY","Module Manager"), new Entry("UTILITY","Configuration Manager"),
        new Entry("UTILITY","Theme Editor"), new Entry("UTILITY","Notifications"),
        new Entry("UTILITY","Panic Button"), new Entry("UTILITY","Keybind Manager"),
        new Entry("UTILITY","Profile Manager"), new Entry("UTILITY","Screenshot Manager"),
        new Entry("MISC","Auto GG"), new Entry("MISC","Auto Respawn"),
        new Entry("MISC","Chat Timestamps"), new Entry("MISC","Chat Cleaner"),
        new Entry("MISC","Chat Translator"), new Entry("MISC","Message History"),
        new Entry("MISC","Quick Commands"), new Entry("MISC","Name Protect"),
        new Entry("MISC","Session Info"), new Entry("MISC","Client Settings"),
        new Entry("VISUALS","Trail Effects"), new Entry("VISUALS","Hit Particles"),
        new Entry("VISUALS","Damage Numbers"), new Entry("VISUALS","Custom Totem Animation"),
        new Entry("VISUALS","Custom Cape"), new Entry("VISUALS","Hit Sound"),
        new Entry("VISUALS","Custom Weather"), new Entry("VISUALS","Custom Time"),
        new Entry("VISUALS","Kill Effect"), new Entry("VISUALS","GUI Blur")
    );
}
