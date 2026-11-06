package com.ludwici.copperdeco.registry;

import com.ludwici.copperdeco.block.BaseCopperTileBlock;
import com.ludwici.copperdeco.block.WaxedCopperTileBlock;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.WeatheringCopperCollection;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.function.Supplier;

import static com.ludwici.copperdeco.CopperAgeDecoration.ITEMS;
import static com.ludwici.copperdeco.CopperAgeDecoration.MODID;

public class BlockRegistry {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);

    public static final WeatheringCopperCollection<DeferredBlock<Block>> COPPER_BLOCK_TILE = registerTiles(Blocks.COPPER_BLOCK);
    public static final WeatheringCopperCollection<DeferredBlock<Block>> CHISELED_COPPER_TILE = registerTiles(Blocks.CHISELED_COPPER);
    public static final WeatheringCopperCollection<DeferredBlock<Block>> COPPER_GRATE_TILE = registerTiles(Blocks.COPPER_GRATE);
    public static final WeatheringCopperCollection<DeferredBlock<Block>> CUT_COPPER_TILE = registerTiles(Blocks.CUT_COPPER);

    public static final List<WeatheringCopperCollection<DeferredBlock<Block>>> ALL_TILES = List.of(
            COPPER_BLOCK_TILE, CHISELED_COPPER_TILE, COPPER_GRATE_TILE, CUT_COPPER_TILE
    );

    private static WeatheringCopperCollection<DeferredBlock<Block>> registerTiles(WeatheringCopperCollection<Block> originals) {
        return new WeatheringCopperCollection<>(
                WeatheringCopperCollection.zipMap(originals.weathering(), WeatheringCopperCollection.STATES, BlockRegistry::registerWeathering),
                WeatheringCopperCollection.zipMap(originals.waxed(), WeatheringCopperCollection.STATES, (original, state) -> registerWaxed(original))
        );
    }

    private static DeferredBlock<Block> registerWeathering(Block original, WeatheringCopper.WeatherState state) {
        String name = getIdentifier(original);
        return registerBlock(name, () -> new BaseCopperTileBlock(state, properties(original, name)));
    }

    private static DeferredBlock<Block> registerWaxed(Block original) {
        String name = getIdentifier(original);
        return registerBlock(name, () -> new WaxedCopperTileBlock(properties(original, name)));
    }

    private static DeferredBlock<Block> registerBlock(String name, Supplier<? extends Block> factory) {
        DeferredBlock<Block> block = BLOCKS.register(name, factory);
        ITEMS.registerSimpleBlockItem(block);
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
