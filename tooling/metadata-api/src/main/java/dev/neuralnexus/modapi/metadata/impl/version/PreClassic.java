/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.metadata.impl.version;

import dev.neuralnexus.modapi.metadata.MinecraftVersion;

// 1
public interface PreClassic {
    MinecraftVersion RD_132211 = MinecraftVersionImpl.of("rd-132211");
    MinecraftVersion RD_132328 = MinecraftVersionImpl.of("rd-132328");
    MinecraftVersion RD_20090515 = MinecraftVersionImpl.of("rd-20090515");
    MinecraftVersion RD_160052 = MinecraftVersionImpl.of("rd-160052");
    MinecraftVersion RD_161348 = MinecraftVersionImpl.of("rd-161348");
}
