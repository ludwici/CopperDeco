package com.ludwici.copperdeco;

import com.ludwici.copperdeco.registry.BlockRegistry;
import net.fabricmc.api.ModInitializer;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CopperDeco implements ModInitializer {
	public static final String MODID = "copperdeco";

	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	@Override
	public void onInitialize() {
		BlockRegistry.initialize();
		Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, BlockRegistry.CUSTOM_CREATIVE_TAB_KEY, BlockRegistry.CUSTOM_CREATIVE_TAB);
	}
}