package com.ludwici.copperdeco.registry;

import com.ludwici.carpetscore.api.block.CarpetVariantBlock;
import com.ludwici.copperdeco.block.BaseCopperTileBlock;
import com.ludwici.copperdeco.block.WaxedCopperTileBlock;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static com.ludwici.copperdeco.CopperAgeDecoration.ITEMS;
import static com.ludwici.copperdeco.CopperAgeDecoration.MODID;

public class BlockRegistry {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);

    public static final DeferredBlock<Block> COPPER_BLOCK_TILE = registerOxiBlock(Blocks.COPPER_BLOCK);
    public static final DeferredBlock<Block> CHISELED_COPPER_BLOCK_TILE = registerOxiBlock(Blocks.CHISELED_COPPER);
    public static final DeferredBlock<Block> COPPER_GRATE_TILE = registerOxiGrate(Blocks.COPPER_GRATE);
    public static final DeferredBlock<Block> CUT_COPPER_TILE = registerOxiBlock(Blocks.CUT_COPPER);

    public static final DeferredBlock<Block> EXPOSED_COPPER_TILE = registerOxiBlock(Blocks.EXPOSED_COPPER);
    public static final DeferredBlock<Block> EXPOSED_CHISELED_COPPER_TILE = registerOxiBlock(Blocks.EXPOSED_CHISELED_COPPER);
    public static final DeferredBlock<Block> EXPOSED_COPPER_GRATE_TILE = registerOxiGrate(Blocks.EXPOSED_COPPER_GRATE);
    public static final DeferredBlock<Block> EXPOSED_CUT_COPPER_TILE = registerOxiBlock(Blocks.EXPOSED_CUT_COPPER);

    public static final DeferredBlock<Block> WEATHERED_COPPER_TILE = registerOxiBlock(Blocks.WEATHERED_COPPER);
    public static final DeferredBlock<Block> WEATHERED_CHISELED_COPPER_TILE = registerOxiBlock(Blocks.WEATHERED_CHISELED_COPPER);
    public static final DeferredBlock<Block> WEATHERED_COPPER_GRATE_TILE = registerOxiGrate(Blocks.WEATHERED_COPPER_GRATE);
    public static final DeferredBlock<Block> WEATHERED_CUT_COPPER_TILE = registerOxiBlock(Blocks.WEATHERED_CUT_COPPER);

    public static final DeferredBlock<Block> OXIDIZED_COPPER_TILE = registerOxiBlock(Blocks.OXIDIZED_COPPER);
    public static final DeferredBlock<Block> OXIDIZED_CHISELED_COPPER_TILE = registerOxiBlock(Blocks.OXIDIZED_CHISELED_COPPER);
    public static final DeferredBlock<Block> OXIDIZED_COPPER_GRATE_TILE = registerOxiGrate(Blocks.OXIDIZED_COPPER_GRATE);
    public static final DeferredBlock<Block> OXIDIZED_CUT_COPPER_TILE = registerOxiBlock(Blocks.OXIDIZED_CUT_COPPER);

    public static final DeferredBlock<Block> WAXED_COPPER_BLOCK_TILE = registerBlock(Blocks.WAXED_COPPER_BLOCK);
    public static final DeferredBlock<Block> WAXED_EXPOSED_COPPER_TILE = registerBlock(Blocks.WAXED_EXPOSED_COPPER);
    public static final DeferredBlock<Block> WAXED_WEATHERED_COPPER_TILE = registerBlock(Blocks.WAXED_WEATHERED_COPPER);
    public static final DeferredBlock<Block> WAXED_OXIDIZED_COPPER_TILE = registerBlock(Blocks.WAXED_OXIDIZED_COPPER);

    public static final DeferredBlock<Block> WAXED_CHISELED_COPPER_TILE = registerBlock(Blocks.WAXED_CHISELED_COPPER);
    public static final DeferredBlock<Block> WAXED_EXPOSED_CHISELED_COPPER_TILE = registerBlock(Blocks.WAXED_EXPOSED_CHISELED_COPPER);
    public static final DeferredBlock<Block> WAXED_WEATHERED_CHISELED_COPPER_TILE = registerBlock(Blocks.WAXED_WEATHERED_CHISELED_COPPER);
    public static final DeferredBlock<Block> WAXED_OXIDIZED_CHISELED_COPPER_TILE = registerBlock(Blocks.WAXED_OXIDIZED_CHISELED_COPPER);

    public static final DeferredBlock<Block> WAXED_COPPER_GRATE_TILE = registerBlock(Blocks.WAXED_COPPER_GRATE);
    public static final DeferredBlock<Block> WAXED_EXPOSED_COPPER_GRATE_TILE = registerBlock(Blocks.WAXED_EXPOSED_COPPER_GRATE);
    public static final DeferredBlock<Block> WAXED_WEATHERED_COPPER_GRATE_TILE = registerBlock(Blocks.WAXED_WEATHERED_COPPER_GRATE);
    public static final DeferredBlock<Block> WAXED_OXIDIZED_COPPER_GRATE_TILE = registerBlock(Blocks.WAXED_OXIDIZED_COPPER_GRATE);

    public static final DeferredBlock<Block> WAXED_CUT_COPPER_TILE = registerBlock(Blocks.WAXED_CUT_COPPER);
    public static final DeferredBlock<Block> WAXED_EXPOSED_CUT_COPPER_TILE = registerBlock(Blocks.WAXED_EXPOSED_CUT_COPPER);
    public static final DeferredBlock<Block> WAXED_WEATHERED_CUT_COPPER_TILE = registerBlock(Blocks.WAXED_WEATHERED_CUT_COPPER);
    public static final DeferredBlock<Block> WAXED_OXIDIZED_CUT_COPPER_TILE = registerBlock(Blocks.WAXED_OXIDIZED_CUT_COPPER);

    private static <B extends Block> DeferredBlock<Block> registerSimpleBlock(Block orig, Supplier<B> sup) {
        String identifier = BuiltInRegistries.BLOCK.getKey(orig).getPath() + "_tile";
        DeferredBlock<Block> ret = BLOCKS.register(identifier, sup);
        ITEMS.registerSimpleBlockItem(ret);
        return ret;
    }

    private static DeferredBlock<Block> registerBlock(Block orig) {
        return registerSimpleBlock(orig, waxed(orig));
    }

    private static DeferredBlock<Block> registerOxiBlock(Block orig) {
        return registerSimpleBlock(orig, oxiCube(orig));
    }

    private static DeferredBlock<Block> registerOxiGrate(Block orig) {
        return registerSimpleBlock(orig, oxiGrate(orig));
    }

    private static <B extends Block> Supplier<BaseCopperTileBlock> oxiCube(B orig) {;
        return () -> new BaseCopperTileBlock(((WeatheringCopperFullBlock)(orig)).getAge(), BlockBehaviour.Properties.ofFullCopy(orig).forceSolidOn().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MODID, getIdentifier(orig)))));
    }

    private static <B extends Block> Supplier<BaseCopperTileBlock> oxiGrate(B orig) {
        return () -> new BaseCopperTileBlock(((WeatheringCopperGrateBlock)(orig)).getAge(), BlockBehaviour.Properties.ofFullCopy(orig).forceSolidOn().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MODID, getIdentifier(orig)))));
    }

    private static <B extends Block> Supplier<CarpetVariantBlock> variants(B orig) {
        return () -> new CarpetVariantBlock(BlockBehaviour.Properties.ofFullCopy(orig).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MODID, getIdentifier(orig)))));
    }

    private static <B extends Block> Supplier<WaxedCopperTileBlock> waxed(B orig) {
        return () -> new WaxedCopperTileBlock(BlockBehaviour.Properties.ofFullCopy(orig).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MODID, getIdentifier(orig)))));
    }

    private static String getIdentifier(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block).getPath() + "_tile";
    }
}
