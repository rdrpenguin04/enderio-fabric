package com.lightning.enderio;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EIOItems {
	private static final Item INFINITY_DUST_ITEM = new Item(new Item.Settings().group(EIOTabs.EIOMATERIAL_GROUP));
	
	public static void registerAll() {
		Registry.register(Registry.ITEM, new Identifier("enderio", "infinity_dust_item"), INFINITY_DUST_ITEM);
	}
}
