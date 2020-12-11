package com.lightning.enderio;

import com.lightning.enderio.misc.InfinitySpawning;
import com.lightning.enderio.util.BlockUpdateCallback;

import net.fabricmc.api.ModInitializer;

public class Mod implements ModInitializer {
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		BlockUpdateCallback.EVENT.register(new InfinitySpawning.BedrockLightListener());
	}
}
