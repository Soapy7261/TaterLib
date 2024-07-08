/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_18.vanilla;

import dev.neuralnexus.taterapi.Builders;
import dev.neuralnexus.taterlib.v1_18.vanilla.util.VanillaResourceLocation;
import dev.neuralnexus.taterlib.v1_18.vanilla.world.VanillaLocation;

/** The Vanilla bootstrap class. */
public class VanillaBootstrap {
    /** Initializes the Vanilla bootstrap. */
    public static void init() {
        Builders.locationBuilder = VanillaLocation.Builder::new;
        Builders.resourceLocationBuilder = VanillaResourceLocation.Builder::new;
    }
}
