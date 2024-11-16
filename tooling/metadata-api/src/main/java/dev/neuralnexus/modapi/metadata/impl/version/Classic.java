/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.metadata.impl.version;

import static dev.neuralnexus.modapi.metadata.ProtocolType.PRE_PRE_NETTY;

import dev.neuralnexus.modapi.metadata.MinecraftVersion;

// 2
@SuppressWarnings("unused")
public interface Classic {
    MinecraftVersion CMC_161607 = MinecraftVersionImpl.of("mc-161607");
    MinecraftVersion CMC_161616 = MinecraftVersionImpl.of("mc-161616");
    MinecraftVersion CMC_161625 = MinecraftVersionImpl.of("mc-161625");
    MinecraftVersion CMC_161648 = MinecraftVersionImpl.of("mc-161648");
    MinecraftVersion C0_0_2A = MinecraftVersionImpl.of("0.0.2a");
    MinecraftVersion C0_0_3A = MinecraftVersionImpl.of("0.0.3a");
    MinecraftVersion C0_0_9A = MinecraftVersionImpl.of("0.0.9a");
    MinecraftVersion C0_0_10A = MinecraftVersionImpl.of("0.0.10a");
    MinecraftVersion C0_0_11A = MinecraftVersionImpl.of("0.0.11a");
    MinecraftVersion C0_0_12A_D = MinecraftVersionImpl.of("0.0.12a/Development");
    MinecraftVersion C0_0_12A = MinecraftVersionImpl.of("0.0.12a");
    MinecraftVersion C0_0_12A_01 = MinecraftVersionImpl.of("0.0.12a_01");
    MinecraftVersion C0_0_12A_02 = MinecraftVersionImpl.of("0.0.12a_02");
    MinecraftVersion C0_0_12A_03 = MinecraftVersionImpl.of("0.0.12a_03");
    MinecraftVersion C0_0_13A_D = MinecraftVersionImpl.of("0.0.13a/Development");
    MinecraftVersion C0_0_13A = MinecraftVersionImpl.of("0.0.13a");
    MinecraftVersion C0_0_13A_03 = MinecraftVersionImpl.of("0.0.13a_03");
    MinecraftVersion C0_0_14A_D = MinecraftVersionImpl.of("0.0.14a/Development");
    MinecraftVersion C0_0_14A = MinecraftVersionImpl.of("0.0.14a");
    MinecraftVersion C0_0_14A_01 = MinecraftVersionImpl.of("0.0.14a_01");
    MinecraftVersion C0_0_14A_03 = MinecraftVersionImpl.of("0.0.14a_03");
    MinecraftVersion C0_0_14A_04 = MinecraftVersionImpl.of("0.0.14a_04");
    MinecraftVersion C0_0_14A_05 = MinecraftVersionImpl.of("0.0.14a_05");
    MinecraftVersion C0_0_14A_06 = MinecraftVersionImpl.of("0.0.14a_06");
    MinecraftVersion C0_0_14A_07 = MinecraftVersionImpl.of("0.0.14a_07");
    MinecraftVersion C0_0_14A_08 = MinecraftVersionImpl.of("0.0.14a_08");
    MinecraftVersion C0_0_15A_D = MinecraftVersionImpl.of("0.0.15a/Development");
    MinecraftVersion C0_0_15A_M1 = MinecraftVersionImpl.of("0.0.15a (Multiplayer Test 1)");
    MinecraftVersion C0_0_15A_M2 = MinecraftVersionImpl.of("0.0.15a (Multiplayer Test 2)");
    MinecraftVersion C0_0_15A_M3 = MinecraftVersionImpl.of("0.0.15a (Multiplayer Test 3)");
    MinecraftVersion C0_0_15A_M4 = MinecraftVersionImpl.of("0.0.15a (Multiplayer Test 4)");
    MinecraftVersion C0_0_15A_M5 = MinecraftVersionImpl.of("0.0.15a (Multiplayer Test 5)");
    MinecraftVersion C0_0_15A_M6 = MinecraftVersionImpl.of("0.0.15a (Multiplayer Test 6)");
    MinecraftVersion C0_0_15A_M7 = MinecraftVersionImpl.of("0.0.15a (Multiplayer Test 7)");
    MinecraftVersion C0_0_15A_M8 = MinecraftVersionImpl.of("0.0.15a (Multiplayer Test 8)");
    MinecraftVersion C0_0_15A_01 = MinecraftVersionImpl.of("0.0.15a_01");
    MinecraftVersion C0_0_15A_02 = MinecraftVersionImpl.of("0.0.15a_02");
    MinecraftVersion C0_0_15A_03 = MinecraftVersionImpl.of("0.0.15a_03");
    MinecraftVersion C0_0_16A = MinecraftVersionImpl.of("0.0.16a");
    MinecraftVersion C_SERVER_1_0 = MinecraftVersionImpl.of("server 1.0"); // Server for 0.0.16a
    MinecraftVersion C0_0_16A_01 = MinecraftVersionImpl.of("0.0.16a_01");
    MinecraftVersion C_SERVER_1_1 = MinecraftVersionImpl.of("server 1.1"); // Server for 0.0.16a_01
    MinecraftVersion C0_0_16A_02 = MinecraftVersionImpl.of("0.0.16a_02", 3, PRE_PRE_NETTY);
    MinecraftVersion C_SERVER_1_2 =
            MinecraftVersionImpl.of("server 1.2", 3, PRE_PRE_NETTY); // Server for 0.0.16a_02
    MinecraftVersion C0_0_17A = MinecraftVersionImpl.of("0.0.17a", 4, PRE_PRE_NETTY);
    MinecraftVersion C_SERVER_1_3 =
            MinecraftVersionImpl.of("server 1.3", 4, PRE_PRE_NETTY); // Server for 0.0.17a
    MinecraftVersion C0_0_18A = MinecraftVersionImpl.of("0.0.18a", 4, PRE_PRE_NETTY);
    MinecraftVersion C_SERVER_1_4 =
            MinecraftVersionImpl.of("server 1.4", 4, PRE_PRE_NETTY); // Server for 0.0.18a
    MinecraftVersion C0_0_18A_01 = MinecraftVersionImpl.of("0.0.18a_01", 4, PRE_PRE_NETTY);
    MinecraftVersion C_SERVER_1_4_1 =
            MinecraftVersionImpl.of("server 1.4.1", 4, PRE_PRE_NETTY); // Server for 0.0.18a_01
    MinecraftVersion C0_0_18A_02 = MinecraftVersionImpl.of("0.0.18a_02", 4, PRE_PRE_NETTY);
    MinecraftVersion C0_0_19A = MinecraftVersionImpl.of("0.0.19a", 5, PRE_PRE_NETTY);
    MinecraftVersion C_SERVER_1_5 =
            MinecraftVersionImpl.of("server 1.5", 5, PRE_PRE_NETTY); // Server for 0.0.19a
    MinecraftVersion C0_0_19A_02 = MinecraftVersionImpl.of("0.0.19a_02", 5, PRE_PRE_NETTY);
    MinecraftVersion C0_0_19A_01 = MinecraftVersionImpl.of("0.0.19a_01", 5, PRE_PRE_NETTY);
    MinecraftVersion C_SERVER_1_6 =
            MinecraftVersionImpl.of("server 1.6", 5, PRE_PRE_NETTY); // Server for 0.0.19a_01
    MinecraftVersion C0_0_19A_03 = MinecraftVersionImpl.of("0.0.19a_03", 5, PRE_PRE_NETTY);
    MinecraftVersion C0_0_19A_04 = MinecraftVersionImpl.of("0.0.19a_04", 5, PRE_PRE_NETTY);
    MinecraftVersion C0_0_19A_05 = MinecraftVersionImpl.of("0.0.19a_05", 5, PRE_PRE_NETTY);
    MinecraftVersion C0_0_19A_06 = MinecraftVersionImpl.of("0.0.19a_06", 5, PRE_PRE_NETTY);
    MinecraftVersion C_SERVER_1_7 =
            MinecraftVersionImpl.of("server 1.7", 5, PRE_PRE_NETTY); // Server for 0.0.19a_06
    MinecraftVersion C0_0_20A = MinecraftVersionImpl.of("0.0.20a", 6, PRE_PRE_NETTY);
    MinecraftVersion C_SERVER_1_8 =
            MinecraftVersionImpl.of("server 1.8", 6, PRE_PRE_NETTY); // Server for 0.0.20a
    MinecraftVersion C_SERVER_1_8_1 =
            MinecraftVersionImpl.of("server 1.8.1", 6, PRE_PRE_NETTY); // Server for 0.0.20a
    MinecraftVersion C0_0_20A_01 = MinecraftVersionImpl.of("0.0.20a_01", 6, PRE_PRE_NETTY);
    MinecraftVersion C_SERVER_1_8_2 =
            MinecraftVersionImpl.of(
                    "server 1.8.2", 6, PRE_PRE_NETTY); // Server for 0.0.20a_01 - 0.0.23a_01
    MinecraftVersion C0_0_20A_02 = MinecraftVersionImpl.of("0.0.20a_02", 6, PRE_PRE_NETTY);
    MinecraftVersion C0_0_21A = MinecraftVersionImpl.of("0.0.21a", 6, PRE_PRE_NETTY);
    MinecraftVersion C0_0_21A_01 = MinecraftVersionImpl.of("0.0.21a_01", 6, PRE_PRE_NETTY);
    MinecraftVersion C0_0_22A = MinecraftVersionImpl.of("0.0.22a", 6, PRE_PRE_NETTY);
    MinecraftVersion C0_0_22A_01 = MinecraftVersionImpl.of("0.0.22a_01", 6, PRE_PRE_NETTY);
    MinecraftVersion C0_0_22A_02 = MinecraftVersionImpl.of("0.0.22a_02", 6, PRE_PRE_NETTY);
    MinecraftVersion C0_0_22A_03 = MinecraftVersionImpl.of("0.0.22a_03", 6, PRE_PRE_NETTY);
    MinecraftVersion C0_0_22A_04 = MinecraftVersionImpl.of("0.0.22a_04", 6, PRE_PRE_NETTY);
    MinecraftVersion C0_0_22A_05 = MinecraftVersionImpl.of("0.0.22a_05", 6, PRE_PRE_NETTY);
    MinecraftVersion C0_0_23A = MinecraftVersionImpl.of("0.0.23a", 6, PRE_PRE_NETTY);
    MinecraftVersion C0_0_23A_01 = MinecraftVersionImpl.of("0.0.23a_01", 6, PRE_PRE_NETTY);
    MinecraftVersion C0_0_24_D =
            MinecraftVersionImpl.of("0.24 SURVIVAL TEST/Development", 6, PRE_PRE_NETTY);
    MinecraftVersion C0_0_24 = MinecraftVersionImpl.of("0.24_SURVIVAL_TEST", 6, PRE_PRE_NETTY);
    MinecraftVersion C0_0_24_01 =
            MinecraftVersionImpl.of("0.24_SURVIVAL_TEST_01", 6, PRE_PRE_NETTY);
    MinecraftVersion C0_0_24_02 =
            MinecraftVersionImpl.of("0.24_SURVIVAL_TEST_02", 6, PRE_PRE_NETTY);
    MinecraftVersion C0_0_24_03 =
            MinecraftVersionImpl.of("0.24_SURVIVAL_TEST_03", 6, PRE_PRE_NETTY);
    MinecraftVersion C0_0_25 = MinecraftVersionImpl.of("0.25 SURVIVAL_TEST", 6, PRE_PRE_NETTY);
    MinecraftVersion C0_0_25_01 =
            MinecraftVersionImpl.of("0.25_SURVIVAL_TEST_01", 6, PRE_PRE_NETTY);
    MinecraftVersion C0_0_25_02 =
            MinecraftVersionImpl.of("0.25_SURVIVAL_TEST_02", 6, PRE_PRE_NETTY);
    MinecraftVersion C0_0_25_03 =
            MinecraftVersionImpl.of("0.25_SURVIVAL_TEST_03", 6, PRE_PRE_NETTY);
    MinecraftVersion C0_0_25_05 =
            MinecraftVersionImpl.of("0.25_SURVIVAL_TEST_05", 6, PRE_PRE_NETTY);
    MinecraftVersion C0_0_26_D =
            MinecraftVersionImpl.of("0.26_SURVIVAL_TEST/Development", 6, PRE_PRE_NETTY);
    MinecraftVersion C0_0_26 = MinecraftVersionImpl.of("0.26_SURVIVAL_TEST", 6, PRE_PRE_NETTY);
    MinecraftVersion C0_0_27 = MinecraftVersionImpl.of("0.27_SURVIVAL_TEST", 6, PRE_PRE_NETTY);
    MinecraftVersion C0_0_28_D = MinecraftVersionImpl.of("0.28/Development", 7, PRE_PRE_NETTY);
    MinecraftVersion C0_0_28 = MinecraftVersionImpl.of("0.28", 7, PRE_PRE_NETTY);
    MinecraftVersion C_SERVER_1_9 =
            MinecraftVersionImpl.of("server 1.9", 7, PRE_PRE_NETTY); // Server for 0.28
    MinecraftVersion C0_0_28_01 = MinecraftVersionImpl.of("0.28_01", 7, PRE_PRE_NETTY);
    MinecraftVersion C0_0_29 = MinecraftVersionImpl.of("0.29", 7, PRE_PRE_NETTY);
    MinecraftVersion C_SERVER_1_9_1 =
            MinecraftVersionImpl.of("server 1.9.1", 7, PRE_PRE_NETTY); // Server for 0.29
    MinecraftVersion C0_0_29_01 = MinecraftVersionImpl.of("0.29_01", 7, PRE_PRE_NETTY);
    MinecraftVersion C0_0_29_02 = MinecraftVersionImpl.of("0.29_02", 7, PRE_PRE_NETTY);
    MinecraftVersion C0_0_30 = MinecraftVersionImpl.of("0.30", 7, PRE_PRE_NETTY);
    MinecraftVersion C_SERVER_1_10 =
            MinecraftVersionImpl.of("server 1.10", 7, PRE_PRE_NETTY); // Server for 0.30
    MinecraftVersion C_SERVER_1_10_1 =
            MinecraftVersionImpl.of("server 1.10.1", 7, PRE_PRE_NETTY); // Server for 0.30
}
