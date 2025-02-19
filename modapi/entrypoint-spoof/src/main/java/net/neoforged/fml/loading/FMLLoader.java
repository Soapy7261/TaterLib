/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package net.neoforged.fml.loading;

import net.neoforged.api.distmarker.Dist;

/** Fake NeoForge class */
public class FMLLoader {
    private static Dist dist;

    public static VersionInfo versionInfo() {
        return new VersionInfo();
    }

    public static Dist getDist() {
        return dist;
    }
}
