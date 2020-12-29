package com.lightning.enderio;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class EIOTabs {
	public static final ItemGroup EIOMATERIAL_GROUP = FabricItemGroupBuilder.build(
			new Identifier("enderio-fabric", "material"),
			() -> new ItemStack(Blocks.COBBLESTONE));
}
