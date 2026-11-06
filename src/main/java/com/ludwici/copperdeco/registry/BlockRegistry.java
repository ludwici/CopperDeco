package com.ludwici.copperdeco.registry;

import com.ludwici.copperdeco.block.BaseCopperTileBlock;
import com.ludwici.copperdeco.block.WaxedCopperTileBlock;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.WeatheringCopperCollection;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.List;

import static com.ludwici.copperdeco.CopperDeco.MODID;

public class BlockRegistry {
    public static final WeatheringCopperCollection<Block> COPPER_BLOCK_TILE = registerTiles(Blocks.COPPER_BLOCK);
    public static final WeatheringCopperCollection<Block> CHISELED_COPPER_TILE = registerTiles(Blocks.CHISELED_COPPER);
    public static final WeatheringCopperCollection<Block> COPPER_GRATE_TILE = registerTiles(Blocks.COPPER_GRATE);
    public static final WeatheringCopperCollection<Block> CUT_COPPER_TILE = registerTiles(Blocks.CUT_COPPER);

    public static final List<WeatheringCopperCollection<Block>> ALL_TILES = List.of(
            COPPER_BLOCK_TILE, CHISELED_COPPER_TILE, COPPER_GRATE_TILE, CUT_COPPER_TILE
    );

    public static void initialize() {
        ALL_TILES.size();
    }

    private static WeatheringCopperCollection<Block> registerTiles(WeatheringCopperCollection<Block> originals) {
        return new WeatheringCopperCollection<>(
                WeatheringCopperCollection.zipMap(originals.weathering(), WeatheringCopperCollection.STATES, BlockRegistry::registerWeathering),
                WeatheringCopperCollection.zipMap(originals.waxed(), WeatheringCopperCollection.STATES, (original, state) -> registerWaxed(original))
        );
    }

    private static Block registerWeathering(Block original, WeatheringCopper.WeatherState state) {
        String name = getIdentifier(original);
        return registerBlock(name, new BaseCopperTileBlock(state, properties(original, name)));
    }

    private static Block registerWaxed(Block original) {
        String name = getIdentifier(original);
        return registerBlock(name, new WaxedCopperTileBlock(properties(original, name)));
    }

    private static Block registerBlock(String name, Block block) {
        ResourceKey<Block> blockKey = ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MODID, name));
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MODID, name));
        Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
        Registry.register(BuiltInRegistries.ITEM, itemKey, new BlockItem(block, new Item.Properties().setId(itemKey).useBlockDescriptionPrefix()));
        return block;
    }

    private static BlockBehaviour.Properties properties(Block original, String name) {
        return BlockBehaviour.Properties.ofFullCopy(original).forceSolidOn()
                .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MODID, name)));
    }

    private static String getIdentifier(Block original) {
        return BuiltInRegistries.BLOCK.getKey(original).getPath() + "_tile";
    }
}
