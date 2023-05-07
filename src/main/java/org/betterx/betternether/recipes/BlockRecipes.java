package org.betterx.betternether.recipes;

import org.betterx.bclib.config.Configs;
import org.betterx.bclib.recipes.BCLRecipeBuilder;
import org.betterx.betternether.BetterNether;
import org.betterx.betternether.registry.NetherBlocks;
import org.betterx.betternether.registry.NetherItems;

import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

public class BlockRecipes {
    public static void register() {
        BCLRecipeBuilder.crafting(BetterNether.makeID("activator_rail"), Items.ACTIVATOR_RAIL)
                        .checkConfig(Configs.RECIPE_CONFIG)
                        .setShape("XSX", "X#X", "XSX")
                        .addMaterial('#', Items.REDSTONE_TORCH)
                        .addMaterial('S', Items.STICK)
                        .addMaterial('X', NetherItems.CINCINNASITE_INGOT)
                        .setGroup("nether_activator_rail")
                        .setOutputCount(6)
                        .build();

        BCLRecipeBuilder.crafting(BetterNether.makeID("black_apple_seed"), NetherBlocks.BLACK_APPLE_SEED)
                        .checkConfig(Configs.RECIPE_CONFIG)
                        .setShape("#")
                        .addMaterial('#', NetherItems.BLACK_APPLE)
                        .setGroup("nether_black_apple_seed")
                        .setOutputCount(4)
                        .build();

        BCLRecipeBuilder.crafting(BetterNether.makeID("bn_bone_block"), NetherBlocks.BONE_BLOCK)
                        .checkConfig(Configs.RECIPE_CONFIG)
                        .setShape("##", "##")
                        .addMaterial('#', Items.BONE_BLOCK)
                        .setGroup("nether_bn_bone_block")
                        .setOutputCount(4)
                        .build();
        BCLRecipeBuilder.crafting(BetterNether.makeID("bone_cin_door"), NetherBlocks.BONE_CINCINNASITE_DOOR)
                        .checkConfig(Configs.RECIPE_CONFIG)
                        .setShape("AB", "BB", "BA")
                        .addMaterial('A', NetherBlocks.CINCINNASITE_FORGED)
                        .addMaterial('B', NetherBlocks.BONE_BLOCK)
                        .setGroup("nether_bone_cin_door")
                        .setOutputCount(3)
                        .build();

        BCLRecipeBuilder.crafting(BetterNether.makeID("bone_tile"), NetherBlocks.BONE_TILE)
                        .checkConfig(Configs.RECIPE_CONFIG)
                        .setShape("#", "#")
                        .addMaterial('#', NetherBlocks.BONE_SLAB)
                        .setGroup("nether_bone_tile")
                        .build();
        BCLRecipeBuilder.crafting(BetterNether.makeID("brick_pot"), NetherBlocks.BRICK_POT)
                        .checkConfig(Configs.RECIPE_CONFIG)
                        .setShape("#N#", " # ")
                        .addMaterial('#', Items.NETHER_BRICK)
                        .addMaterial('N', Items.SOUL_SAND)
                        .setGroup("nether_brick_pot")
                        .build();


        BCLRecipeBuilder.crafting(BetterNether.makeID("cincinnasite_bars"), NetherBlocks.CINCINNASITE_BARS)
                        .checkConfig(Configs.RECIPE_CONFIG)
                        .setShape("###", "###")
                        .addMaterial('#', NetherItems.CINCINNASITE_INGOT)
                        .setGroup("nether_cincinnasite_bars")
                        .setOutputCount(16)
                        .build();
        BCLRecipeBuilder.crafting(BetterNether.makeID("cincinnasite_block"), NetherBlocks.CINCINNASITE_BLOCK)
                        .checkConfig(Configs.RECIPE_CONFIG)
                        .setShape("##", "##")
                        .addMaterial('#', NetherItems.CINCINNASITE)
                        .setGroup("nether_cincinnasite_block")
                        .build();
        BCLRecipeBuilder.crafting(
                                BetterNether.makeID("cincinnasite_brick_plate"),
                                NetherBlocks.CINCINNASITE_BRICK_PLATE
                        )
                        .checkConfig(Configs.RECIPE_CONFIG)
                        .setShape(" # ", "BBB", " # ")
                        .addMaterial('#', NetherBlocks.CINCINNASITE_FORGED)
                        .addMaterial('B', Items.NETHER_BRICK)
                        .setGroup("nether_cincinnasite_brick_plate")
                        .setOutputCount(5)
                        .build();
        BCLRecipeBuilder.crafting(BetterNether.makeID("cincinnasite_bricks"), NetherBlocks.CINCINNASITE_BRICKS)
                        .checkConfig(Configs.RECIPE_CONFIG)
                        .setShape("#B", "B#")
                        .addMaterial('#', NetherBlocks.CINCINNASITE_FORGED)
                        .addMaterial('B', Items.NETHER_BRICK)
                        .setGroup("nether_cincinnasite_bricks")
                        .setOutputCount(4)
                        .build();
        BCLRecipeBuilder.crafting(
                                BetterNether.makeID("cincinnasite_bricks_pillar"),
                                NetherBlocks.CINCINNASITE_BRICKS_PILLAR
                        )
                        .checkConfig(Configs.RECIPE_CONFIG)
                        .setShape("#", "#")
                        .addMaterial('#', NetherBlocks.CINCINNASITE_BRICKS)
                        .setGroup("nether_cincinnasite_bricks_pillar")
                        .setOutputCount(2)
                        .build();
        BCLRecipeBuilder.crafting(BetterNether.makeID("cincinnasite_button"), NetherBlocks.CINCINNASITE_BUTTON)
                        .checkConfig(Configs.RECIPE_CONFIG)
                        .setShape("#")
                        .addMaterial('#', NetherItems.CINCINNASITE_INGOT)
                        .setGroup("nether_cincinnasite_button")
                        .build();
        BCLRecipeBuilder.crafting(BetterNether.makeID("cincinnasite_carved"), NetherBlocks.CINCINNASITE_CARVED)
                        .checkConfig(Configs.RECIPE_CONFIG)
                        .setShape("##", "##")
                        .addMaterial('#', NetherBlocks.CINCINNASITE_FORGED)
                        .setGroup("nether_cincinnasite_carved")
                        .setOutputCount(4)
                        .build();
        BCLRecipeBuilder.crafting(BetterNether.makeID("cincinnasite_forge"), NetherBlocks.CINCINNASITE_FORGE)
                        .checkConfig(Configs.RECIPE_CONFIG)
                        .setShape("B#B", "# #", "B#B")
                        .addMaterial('#', NetherBlocks.CINCINNASITE_FORGED)
                        .addMaterial('B', Items.NETHER_BRICKS)
                        .setGroup("nether_cincinnasite_forge")
                        .build();
        BCLRecipeBuilder.crafting(
                                BetterNether.makeID("cincinnasite_forged_from_ingot"),
                                NetherBlocks.CINCINNASITE_FORGED
                        )
                        .checkConfig(Configs.RECIPE_CONFIG)
                        .setShape("##", "##")
                        .addMaterial('#', NetherItems.CINCINNASITE_INGOT)
                        .setGroup("nether_cincinnasite_forged_from_ingot")
                        .build();
        BCLRecipeBuilder.crafting(BetterNether.makeID("cincinnasite_frame"), NetherBlocks.CINCINNASITE_FRAME)
                        .checkConfig(Configs.RECIPE_CONFIG)
                        .setShape("# #", "   ", "# #")
                        .addMaterial('#', NetherItems.CINCINNASITE_INGOT)
                        .setGroup("nether_cincinnasite_frame")
                        .setOutputCount(16)
                        .build();

        BCLRecipeBuilder.crafting(BetterNether.makeID("cincinnasite_lantern"), NetherBlocks.CINCINNASITE_LANTERN)
                        .checkConfig(Configs.RECIPE_CONFIG)
                        .setShape(" # ", "#G#", " # ")
                        .addMaterial('#', NetherItems.CINCINNASITE_INGOT)
                        .addMaterial('G', Items.GLOWSTONE)
                        .setGroup("nether_cincinnasite_lantern")
                        .build();
        BCLRecipeBuilder.crafting(
                                BetterNether.makeID("cincinnasite_lantern_small"),
                                NetherBlocks.CINCINNASITE_LANTERN_SMALL
                        )
                        .checkConfig(Configs.RECIPE_CONFIG)
                        .setShape("I", "L")
                        .addMaterial('I', NetherItems.CINCINNASITE_INGOT)
                        .addMaterial('L', NetherBlocks.CINCINNASITE_LANTERN)
                        .setGroup("nether_cincinnasite_lantern_small")
                        .setOutputCount(4)
                        .build();
        BCLRecipeBuilder.crafting(BetterNether.makeID("cincinnasite_pedestal"), NetherBlocks.CINCINNASITE_PEDESTAL)
                        .checkConfig(Configs.RECIPE_CONFIG)
                        .setShape("##", "##", "##")
                        .addMaterial('#', NetherBlocks.CINCINNASITE_FORGED)
                        .setGroup("nether_cincinnasite_pedestal")
                        .setOutputCount(2)
                        .build();
        BCLRecipeBuilder.crafting(BetterNether.makeID("cincinnasite_pillar"), NetherBlocks.CINCINNASITE_PILLAR)
                        .checkConfig(Configs.RECIPE_CONFIG)
                        .setShape("#", "#")
                        .addMaterial('#', NetherBlocks.CINCINNASITE_FORGED)
                        .setGroup("nether_cincinnasite_pillar")
                        .setOutputCount(2)
                        .build();
        BCLRecipeBuilder.crafting(BetterNether.makeID("cincinnasite_pot"), NetherBlocks.CINCINNASITE_POT)
                        .checkConfig(Configs.RECIPE_CONFIG)
                        .setShape("#N#", " # ")
                        .addMaterial('#', NetherItems.CINCINNASITE_INGOT)
                        .addMaterial('N', Items.SOUL_SAND)
                        .setGroup("nether_cincinnasite_pot")
                        .build();
        BCLRecipeBuilder.crafting(BetterNether.makeID("cincinnasite_tile_large"), NetherBlocks.CINCINNASITE_TILE_LARGE)
                        .checkConfig(Configs.RECIPE_CONFIG)
                        .setShape("#", "#")
                        .addMaterial('#', NetherBlocks.CINCINNASITE_SLAB)
                        .setGroup("nether_cincinnasite_tile_large")
                        .build();
        BCLRecipeBuilder.crafting(BetterNether.makeID("cincinnasite_tile_small"), NetherBlocks.CINCINNASITE_TILE_SMALL)
                        .checkConfig(Configs.RECIPE_CONFIG)
                        .setShape("##", "##")
                        .addMaterial('#', NetherBlocks.CINCINNASITE_TILE_LARGE)
                        .setGroup("nether_cincinnasite_tile_small")
                        .setOutputCount(4)
                        .build();


        BCLRecipeBuilder.crafting(BetterNether.makeID("nether_brewing_stand"), NetherBlocks.NETHER_BREWING_STAND)
                        .checkConfig(Configs.RECIPE_CONFIG)
                        .setShape(" I ", " S ", "###")
                        .addMaterial('I', NetherItems.CINCINNASITE_INGOT)
                        .addMaterial('S', Items.BLAZE_ROD)
                        .addMaterial('#', Items.NETHER_BRICKS)
                        .setGroup("nether_nether_brewing_stand")
                        .build();
        BCLRecipeBuilder.crafting(BetterNether.makeID("nether_ruby_block"), NetherBlocks.NETHER_RUBY_BLOCK)
                        .checkConfig(Configs.RECIPE_CONFIG)
                        .setShape("###", "###", "###")
                        .addMaterial('#', NetherItems.NETHER_RUBY)
                        .setGroup("nether_nether_ruby_block")
                        .build();

        BCLRecipeBuilder.crafting(BetterNether.makeID("nether_tile_large"), NetherBlocks.NETHER_BRICK_TILE_LARGE)
                        .checkConfig(Configs.RECIPE_CONFIG)
                        .setShape("##", "##")
                        .addMaterial('#', Items.NETHER_BRICK_SLAB)
                        .setGroup("nether_nether_tile_large")
                        .setOutputCount(2)
                        .build();
        BCLRecipeBuilder.crafting(BetterNether.makeID("nether_tile_small"), NetherBlocks.NETHER_BRICK_TILE_SMALL)
                        .checkConfig(Configs.RECIPE_CONFIG)
                        .setShape("##", "##")
                        .addMaterial('#', NetherBlocks.NETHER_BRICK_TILE_LARGE)
                        .setGroup("nether_nether_tile_small")
                        .setOutputCount(4)
                        .build();

        BCLRecipeBuilder.crafting(BetterNether.makeID("quartz_glass_framed"), NetherBlocks.QUARTZ_GLASS_FRAMED)
                        .checkConfig(Configs.RECIPE_CONFIG)
                        .setShape("G#G", "# #", "G#G")
                        .addMaterial('#', NetherItems.CINCINNASITE_INGOT)
                        .addMaterial('G', NetherBlocks.QUARTZ_GLASS)
                        .setGroup("nether_quartz_glass_framed")
                        .setOutputCount(8)
                        .build();
        BCLRecipeBuilder.crafting(
                                BetterNether.makeID("quartz_glass_framed_pane"),
                                NetherBlocks.QUARTZ_GLASS_FRAMED_PANE
                        )
                        .checkConfig(Configs.RECIPE_CONFIG)
                        .setShape("###", "###")
                        .addMaterial('#', NetherBlocks.QUARTZ_GLASS_FRAMED)
                        .setGroup("nether_quartz_glass_framed_pane")
                        .setOutputCount(16)
                        .build();
        BCLRecipeBuilder.crafting(BetterNether.makeID("quartz_glass_pane"), NetherBlocks.QUARTZ_GLASS_PANE)
                        .checkConfig(Configs.RECIPE_CONFIG)
                        .setShape("###", "###")
                        .addMaterial('#', NetherBlocks.QUARTZ_GLASS)
                        .setGroup("nether_quartz_glass_pane")
                        .setOutputCount(16)
                        .build();


        BCLRecipeBuilder.crafting(BetterNether.makeID("wall_moss"), NetherBlocks.WALL_MOSS)
                        .checkConfig(Configs.RECIPE_CONFIG)
                        .setShape("#")
                        .addMaterial('#', NetherBlocks.NETHER_GRASS)
                        .setGroup("nether_wall_moss")
                        .build();

        BCLRecipeBuilder.crafting(
                                BetterNether.makeID("whispering_gourd_lantern"),
                                NetherBlocks.WHISPERING_GOURD_LANTERN
                        )
                        .checkConfig(Configs.RECIPE_CONFIG)
                        .setShape("#", "T")
                        .addMaterial('#', NetherBlocks.WHISPERING_GOURD)
                        .addMaterial('T', Items.TORCH)
                        .setGroup("nether_whispering_gourd_lantern")
                        .build();


        BCLRecipeBuilder.crafting(BetterNether.makeID("blue_obsidian_bricks"), NetherBlocks.BLUE_OBSIDIAN_BRICKS)
                        .checkConfig(Configs.RECIPE_CONFIG)
                        .setShape("##", "##")
                        .addMaterial('#', NetherBlocks.BLUE_OBSIDIAN_TILE)
                        .setGroup("nether_blue_obsidian_bricks")
                        .setOutputCount(4)
                        .build();
        BCLRecipeBuilder.crafting(
                                BetterNether.makeID("blue_obsidian_glass_pane"),
                                NetherBlocks.BLUE_OBSIDIAN_GLASS_PANE
                        )
                        .checkConfig(Configs.RECIPE_CONFIG)
                        .setShape("###", "###")
                        .addMaterial('#', NetherBlocks.BLUE_OBSIDIAN_GLASS)
                        .setGroup("nether_blue_obsidian_glass_pane")
                        .setOutputCount(16)
                        .build();
        BCLRecipeBuilder.crafting(BetterNether.makeID("blue_obsidian_rod_tiles"), NetherBlocks.BLUE_OBSIDIAN_ROD_TILES)
                        .checkConfig(Configs.RECIPE_CONFIG)
                        .setShape(" ##", "## ")
                        .addMaterial('#', NetherBlocks.BLUE_OBSIDIAN_TILE)
                        .setGroup("nether_blue_obsidian_rod_tiles")
                        .setOutputCount(4)
                        .build();
        BCLRecipeBuilder.crafting(BetterNether.makeID("blue_obsidian_tile"), NetherBlocks.BLUE_OBSIDIAN_TILE)
                        .checkConfig(Configs.RECIPE_CONFIG)
                        .setShape("##", "##")
                        .addMaterial('#', NetherBlocks.BLUE_OBSIDIAN)
                        .setGroup("nether_blue_obsidian_tile")
                        .setOutputCount(4)
                        .build();
        BCLRecipeBuilder.crafting(
                                BetterNether.makeID("blue_obsidian_tile_small"),
                                NetherBlocks.BLUE_OBSIDIAN_TILE_SMALL
                        )
                        .checkConfig(Configs.RECIPE_CONFIG)
                        .setShape("##", "##")
                        .addMaterial('#', NetherBlocks.BLUE_OBSIDIAN_BRICKS)
                        .setGroup("nether_blue_obsidian_tile_small")
                        .setOutputCount(4)
                        .build();
        BCLRecipeBuilder.crafting(BetterNether.makeID("cincinnasite_anvil"), NetherBlocks.CINCINNASITE_ANVIL)
                        .checkConfig(Configs.RECIPE_CONFIG)
                        .setShape("###", " # ", "BBB")
                        .addMaterial('#', NetherBlocks.CINCINNASITE_FORGED)
                        .addMaterial('B', Items.NETHER_BRICKS)
                        .setGroup("nether_cincinnasite_anvil")
                        .build();
        BCLRecipeBuilder.crafting(BetterNether.makeID("obsidian_bricks"), NetherBlocks.OBSIDIAN_BRICKS)
                        .checkConfig(Configs.RECIPE_CONFIG)
                        .setShape("##", "##")
                        .addMaterial('#', NetherBlocks.OBSIDIAN_TILE)
                        .setGroup("nether_obsidian_bricks")
                        .setOutputCount(4)
                        .build();
        BCLRecipeBuilder.crafting(BetterNether.makeID("obsidian_glass_pane"), NetherBlocks.OBSIDIAN_GLASS_PANE)
                        .checkConfig(Configs.RECIPE_CONFIG)
                        .setShape("###", "###")
                        .addMaterial('#', NetherBlocks.OBSIDIAN_GLASS)
                        .setGroup("nether_obsidian_glass_pane")
                        .setOutputCount(16)
                        .build();
        BCLRecipeBuilder.crafting(BetterNether.makeID("obsidian_rod_tiles"), NetherBlocks.OBSIDIAN_ROD_TILES)
                        .checkConfig(Configs.RECIPE_CONFIG)
                        .setShape(" ##", "## ")
                        .addMaterial('#', NetherBlocks.OBSIDIAN_TILE)
                        .setGroup("nether_obsidian_rod_tiles")
                        .setOutputCount(4)
                        .build();
        BCLRecipeBuilder.crafting(BetterNether.makeID("obsidian_tile"), NetherBlocks.OBSIDIAN_TILE)
                        .checkConfig(Configs.RECIPE_CONFIG)
                        .setShape("##", "##")
                        .addMaterial('#', Items.OBSIDIAN)
                        .setGroup("nether_obsidian_tile")
                        .setOutputCount(4)
                        .build();
        BCLRecipeBuilder.crafting(BetterNether.makeID("obsidian_tile_small"), NetherBlocks.OBSIDIAN_TILE_SMALL)
                        .checkConfig(Configs.RECIPE_CONFIG)
                        .setShape("##", "##")
                        .addMaterial('#', NetherBlocks.OBSIDIAN_BRICKS)
                        .setGroup("nether_obsidian_tile_small")
                        .setOutputCount(4)
                        .build();

        BCLRecipeBuilder.crafting(BetterNether.makeID("farmland"), NetherBlocks.FARMLAND)
                        .checkConfig(Configs.RECIPE_CONFIG)
                        .setShape("#S#", "#N#", "#H#")
                        .addMaterial('#', NetherBlocks.MAT_STALAGNATE.getPlanks())
                        .addMaterial('H', NetherBlocks.MAT_STALAGNATE.getSlab())
                        .addMaterial('N', Items.NETHERRACK)
                        .addMaterial('S', Items.SOUL_SAND)
                        .setGroup("nether_farmland")
                        .setOutputCount(4)
                        .build();

        BCLRecipeBuilder.crafting(BetterNether.makeID("bone_reed_door"), NetherBlocks.BONE_REED_DOOR)
                        .checkConfig(Configs.RECIPE_CONFIG)
                        .setShape("AB", "BB", "BA")
                        .addMaterial('A', NetherBlocks.MAT_REED.getPlanks())
                        .addMaterial('B', NetherBlocks.BONE_BLOCK)
                        .setGroup("nether_bone_reed_door")
                        .setOutputCount(3)
                        .build();

        BCLRecipeBuilder.crafting(BetterNether.makeID("chest_of_drawers"), NetherBlocks.CHEST_OF_DRAWERS)
                        .checkConfig(Configs.RECIPE_CONFIG)
                        .setShape("C#C", "# #", "C#C")
                        .addMaterial('C', NetherBlocks.CINCINNASITE_FORGED)
                        .addMaterial('#', NetherBlocks.MAT_REED.getPlanks())
                        .setGroup("nether_chest_of_drawers")
                        .build();

        registerStoneCutting();
        registerBlasting();
        registerSmelting();
        registerSmithing();
    }

    private static void registerSmithing() {
        BCLRecipeBuilder
                .smithing(BetterNether.makeID("netherite_fire_bowl"), NetherBlocks.NETHERITE_FIRE_BOWL)
                .checkConfig(Configs.RECIPE_CONFIG)
                .setBase(NetherBlocks.CINCINNASITE_FIRE_BOWL)
                .setAddition(Items.NETHERITE_INGOT)
                .setGroup("nether_netherite_fire_bowl")
                .build();

        BCLRecipeBuilder
                .smithing(BetterNether.makeID("netherite_fire_bowl_soul"), NetherBlocks.NETHERITE_FIRE_BOWL_SOUL)
                .checkConfig(Configs.RECIPE_CONFIG)
                .setBase(NetherBlocks.CINCINNASITE_FIRE_BOWL_SOUL)
                .setAddition(Items.NETHERITE_INGOT)
                .setGroup("nether_netherite_fire_bowl_soul")
                .build();
    }

    private static void registerSmelting() {
        BCLRecipeBuilder.smelting(BetterNether.makeID("blue_obsidian_glass"), NetherBlocks.BLUE_OBSIDIAN_GLASS)
                        .checkConfig(Configs.RECIPE_CONFIG)
                        .setInput(NetherBlocks.BLUE_OBSIDIAN)
                        .setGroup("nether_blue_obsidian_glass")
                        .setCookingTime(200)
                        .build();
        BCLRecipeBuilder.smelting(BetterNether.makeID("cincinnasite_forged"), NetherBlocks.CINCINNASITE_FORGED)
                        .checkConfig(Configs.RECIPE_CONFIG)
                        .setInput(NetherBlocks.CINCINNASITE_BLOCK)
                        .setGroup("nether_cincinnasite_forged")
                        .setCookingTime(200)
                        .build();
        BCLRecipeBuilder.smelting(BetterNether.makeID("obsidian_glass"), NetherBlocks.OBSIDIAN_GLASS)
                        .checkConfig(Configs.RECIPE_CONFIG)
                        .setInput(Blocks.OBSIDIAN)
                        .setGroup("nether_obsidian_glass")
                        .setCookingTime(200)
                        .build();
        BCLRecipeBuilder.smelting(BetterNether.makeID("quartz_glass"), NetherBlocks.QUARTZ_GLASS)
                        .checkConfig(Configs.RECIPE_CONFIG)
                        .setInput(Items.QUARTZ)
                        .setGroup("nether_quartz_glass")
                        .setCookingTime(200)
                        .build();
        BCLRecipeBuilder.smelting(BetterNether.makeID("soul_sandstone"), NetherBlocks.SOUL_SANDSTONE_SMOOTH)
                        .checkConfig(Configs.RECIPE_CONFIG)
                        .setInput(NetherBlocks.SOUL_SANDSTONE)
                        .setGroup("nether_soul_sandstone")
                        .setCookingTime(200)
                        .setExperience(0.1f)
                        .build();
    }

    private static void registerBlasting() {
        BCLRecipeBuilder
                .blasting(BetterNether.makeID("cincinnasite_forged_blasting"), NetherBlocks.CINCINNASITE_FORGED)
                .checkConfig(Configs.RECIPE_CONFIG)
                .setInput(NetherBlocks.CINCINNASITE_BLOCK)
                .setGroup("nether_cincinnasite_forged_blasting")
                .setCookingTime(100)
                .build();
    }

    private static void registerStoneCutting() {
        BCLRecipeBuilder
                .stonecutting(
                        BetterNether.makeID("blue_obsidian_bricks_from_tile_stonecutter"),
                        NetherBlocks.BLUE_OBSIDIAN_BRICKS
                )
                .checkConfig(Configs.RECIPE_CONFIG)
                .setInput(NetherBlocks.BLUE_OBSIDIAN_TILE)
                .setGroup("nether_blue_obsidian_bricks_from_tile_stonecutter")
                .build();

        BCLRecipeBuilder
                .stonecutting(
                        BetterNether.makeID("blue_obsidian_bricks_slab_from_bricks_stonecutter"),
                        NetherBlocks.BLUE_OBSIDIAN_BRICKS_SLAB
                )
                .checkConfig(Configs.RECIPE_CONFIG)
                .setInput(NetherBlocks.BLUE_OBSIDIAN_BRICKS)
                .setGroup("nether_blue_obsidian_bricks_slab_from_bricks_stonecutter")
                .setOutputCount(2)
                .build();

        BCLRecipeBuilder
                .stonecutting(
                        BetterNether.makeID("blue_obsidian_bricks_slab_stonecutter"),
                        NetherBlocks.BLUE_OBSIDIAN_BRICKS_SLAB
                )
                .checkConfig(Configs.RECIPE_CONFIG)
                .setInput(NetherBlocks.BLUE_OBSIDIAN)
                .setGroup("nether_blue_obsidian_bricks_slab_stonecutter")
                .setOutputCount(2)
                .build();

        BCLRecipeBuilder
                .stonecutting(
                        BetterNether.makeID("blue_obsidian_bricks_stairs_from_bricks_stonecutter"),
                        NetherBlocks.BLUE_OBSIDIAN_BRICKS_STAIRS
                )
                .checkConfig(Configs.RECIPE_CONFIG)
                .setInput(NetherBlocks.BLUE_OBSIDIAN_BRICKS)
                .setGroup("nether_blue_obsidian_bricks_stairs_from_bricks_stonecutter")
                .build();

        BCLRecipeBuilder
                .stonecutting(
                        BetterNether.makeID("blue_obsidian_bricks_stairs_stonecutter"),
                        NetherBlocks.BLUE_OBSIDIAN_BRICKS_STAIRS
                )
                .checkConfig(Configs.RECIPE_CONFIG)
                .setInput(NetherBlocks.BLUE_OBSIDIAN)
                .setGroup("nether_blue_obsidian_bricks_stairs_stonecutter")
                .build();

        BCLRecipeBuilder
                .stonecutting(
                        BetterNether.makeID("blue_obsidian_bricks_stonecutter"),
                        NetherBlocks.BLUE_OBSIDIAN_BRICKS
                )
                .checkConfig(Configs.RECIPE_CONFIG)
                .setInput(NetherBlocks.BLUE_OBSIDIAN)
                .setGroup("nether_blue_obsidian_bricks_stonecutter")
                .build();

        BCLRecipeBuilder
                .stonecutting(
                        BetterNether.makeID("blue_obsidian_rod_tiles_stonecutter"),
                        NetherBlocks.BLUE_OBSIDIAN_ROD_TILES
                )
                .checkConfig(Configs.RECIPE_CONFIG)
                .setInput(NetherBlocks.BLUE_OBSIDIAN)
                .setGroup("nether_blue_obsidian_rod_tiles_stonecutter")
                .build();

        BCLRecipeBuilder
                .stonecutting(
                        BetterNether.makeID("blue_obsidian_tile_slab_from_tile_stonecutter"),
                        NetherBlocks.BLUE_OBSIDIAN_TILE_SLAB
                )
                .checkConfig(Configs.RECIPE_CONFIG)
                .setInput(NetherBlocks.BLUE_OBSIDIAN_TILE)
                .setGroup("nether_blue_obsidian_tile_slab_from_tile_stonecutter")
                .setOutputCount(2)
                .build();

        BCLRecipeBuilder
                .stonecutting(
                        BetterNether.makeID("blue_obsidian_tile_slab_stonecutter"),
                        NetherBlocks.BLUE_OBSIDIAN_TILE_SLAB
                )
                .checkConfig(Configs.RECIPE_CONFIG)
                .setInput(NetherBlocks.BLUE_OBSIDIAN)
                .setGroup("nether_blue_obsidian_tile_slab_stonecutter")
                .setOutputCount(2)
                .build();

        BCLRecipeBuilder
                .stonecutting(
                        BetterNether.makeID("blue_obsidian_tile_small_from_bricks_stonecutter"),
                        NetherBlocks.BLUE_OBSIDIAN_TILE_SMALL
                )
                .checkConfig(Configs.RECIPE_CONFIG)
                .setInput(NetherBlocks.BLUE_OBSIDIAN_BRICKS)
                .setGroup("nether_blue_obsidian_tile_small_from_bricks_stonecutter")
                .build();

        BCLRecipeBuilder
                .stonecutting(
                        BetterNether.makeID("blue_obsidian_tile_small_stonecutter"),
                        NetherBlocks.BLUE_OBSIDIAN_TILE_SMALL
                )
                .checkConfig(Configs.RECIPE_CONFIG)
                .setInput(NetherBlocks.BLUE_OBSIDIAN)
                .setGroup("nether_blue_obsidian_tile_small_stonecutter")
                .build();

        BCLRecipeBuilder
                .stonecutting(
                        BetterNether.makeID("blue_obsidian_tile_stairs_from_tile_stonecutter"),
                        NetherBlocks.BLUE_OBSIDIAN_TILE_STAIRS
                )
                .checkConfig(Configs.RECIPE_CONFIG)
                .setInput(NetherBlocks.BLUE_OBSIDIAN_TILE)
                .setGroup("nether_blue_obsidian_tile_stairs_from_tile_stonecutter")
                .build();

        BCLRecipeBuilder
                .stonecutting(
                        BetterNether.makeID("blue_obsidian_tile_stairs_stonecutter"),
                        NetherBlocks.BLUE_OBSIDIAN_TILE_STAIRS
                )
                .checkConfig(Configs.RECIPE_CONFIG)
                .setInput(NetherBlocks.BLUE_OBSIDIAN)
                .setGroup("nether_blue_obsidian_tile_stairs_stonecutter")
                .build();

        BCLRecipeBuilder
                .stonecutting(BetterNether.makeID("blue_obsidian_tile_stonecutter"), NetherBlocks.BLUE_OBSIDIAN_TILE)
                .checkConfig(Configs.RECIPE_CONFIG)
                .setInput(NetherBlocks.BLUE_OBSIDIAN)
                .setGroup("nether_blue_obsidian_tile_stonecutter")
                .build();

        BCLRecipeBuilder
                .stonecutting(
                        BetterNether.makeID("obsidian_bricks_from_tile_stonecutter"),
                        NetherBlocks.OBSIDIAN_BRICKS
                )
                .checkConfig(Configs.RECIPE_CONFIG)
                .setInput(NetherBlocks.OBSIDIAN_TILE)
                .setGroup("nether_obsidian_bricks_from_tile_stonecutter")
                .build();

        BCLRecipeBuilder
                .stonecutting(
                        BetterNether.makeID("obsidian_bricks_slab_from_bricks_stonecutter"),
                        NetherBlocks.OBSIDIAN_BRICKS_SLAB
                )
                .checkConfig(Configs.RECIPE_CONFIG)
                .setInput(NetherBlocks.OBSIDIAN_BRICKS)
                .setGroup("nether_obsidian_bricks_slab_from_bricks_stonecutter")
                .setOutputCount(2)
                .build();

        BCLRecipeBuilder
                .stonecutting(
                        BetterNether.makeID("obsidian_bricks_slab_stonecutter"),
                        NetherBlocks.OBSIDIAN_BRICKS_SLAB
                )
                .checkConfig(Configs.RECIPE_CONFIG)
                .setInput(Blocks.OBSIDIAN)
                .setGroup("nether_obsidian_bricks_slab_stonecutter")
                .setOutputCount(2)
                .build();

        BCLRecipeBuilder
                .stonecutting(
                        BetterNether.makeID("obsidian_bricks_stairs_from_bricks_stonecutter"),
                        NetherBlocks.OBSIDIAN_BRICKS_STAIRS
                )
                .checkConfig(Configs.RECIPE_CONFIG)
                .setInput(NetherBlocks.OBSIDIAN_BRICKS)
                .setGroup("nether_obsidian_bricks_stairs_from_bricks_stonecutter")
                .build();

        BCLRecipeBuilder
                .stonecutting(
                        BetterNether.makeID("obsidian_bricks_stairs_stonecutter"),
                        NetherBlocks.OBSIDIAN_BRICKS_STAIRS
                )
                .checkConfig(Configs.RECIPE_CONFIG)
                .setInput(Blocks.OBSIDIAN)
                .setGroup("nether_obsidian_bricks_stairs_stonecutter")
                .build();

        BCLRecipeBuilder
                .stonecutting(BetterNether.makeID("obsidian_bricks_stonecutter"), NetherBlocks.OBSIDIAN_BRICKS)
                .checkConfig(Configs.RECIPE_CONFIG)
                .setInput(Blocks.OBSIDIAN)
                .setGroup("nether_obsidian_bricks_stonecutter")
                .build();

        BCLRecipeBuilder
                .stonecutting(BetterNether.makeID("obsidian_rod_tiles_stonecutting"), NetherBlocks.OBSIDIAN_ROD_TILES)
                .checkConfig(Configs.RECIPE_CONFIG)
                .setInput(Blocks.OBSIDIAN)
                .setGroup("nether_obsidian_rod_tiles_stonecutting")
                .build();

        BCLRecipeBuilder
                .stonecutting(
                        BetterNether.makeID("obsidian_tile_slab_from_tile_stonecutter"),
                        NetherBlocks.OBSIDIAN_TILE_SLAB
                )
                .checkConfig(Configs.RECIPE_CONFIG)
                .setInput(NetherBlocks.OBSIDIAN_TILE)
                .setGroup("nether_obsidian_tile_slab_from_tile_stonecutter")
                .setOutputCount(2)
                .build();

        BCLRecipeBuilder
                .stonecutting(BetterNether.makeID("obsidian_tile_slab_stonecutter"), NetherBlocks.OBSIDIAN_TILE_SLAB)
                .checkConfig(Configs.RECIPE_CONFIG)
                .setInput(Blocks.OBSIDIAN)
                .setGroup("nether_obsidian_tile_slab_stonecutter")
                .setOutputCount(2)
                .build();

        BCLRecipeBuilder
                .stonecutting(
                        BetterNether.makeID("obsidian_tile_small_from_bricks_stonecutter"),
                        NetherBlocks.OBSIDIAN_TILE_SMALL
                )
                .checkConfig(Configs.RECIPE_CONFIG)
                .setInput(NetherBlocks.OBSIDIAN_BRICKS)
                .setGroup("nether_obsidian_tile_small_from_bricks_stonecutter")
                .build();

        BCLRecipeBuilder
                .stonecutting(BetterNether.makeID("obsidian_tile_small_stonecutter"), NetherBlocks.OBSIDIAN_TILE_SMALL)
                .checkConfig(Configs.RECIPE_CONFIG)
                .setInput(Blocks.OBSIDIAN)
                .setGroup("nether_obsidian_tile_small_stonecutter")
                .build();

        BCLRecipeBuilder
                .stonecutting(
                        BetterNether.makeID("obsidian_tile_stairs_from_tile_stonecutter"),
                        NetherBlocks.OBSIDIAN_TILE_STAIRS
                )
                .checkConfig(Configs.RECIPE_CONFIG)
                .setInput(NetherBlocks.OBSIDIAN_TILE)
                .setGroup("nether_obsidian_tile_stairs_from_tile_stonecutter")
                .build();

        BCLRecipeBuilder
                .stonecutting(
                        BetterNether.makeID("obsidian_tile_stairs_stonecutter"),
                        NetherBlocks.OBSIDIAN_TILE_STAIRS
                )
                .checkConfig(Configs.RECIPE_CONFIG)
                .setInput(Blocks.OBSIDIAN)
                .setGroup("nether_obsidian_tile_stairs_stonecutter")
                .build();

        BCLRecipeBuilder
                .stonecutting(BetterNether.makeID("obsidian_tile_stonecutter"), NetherBlocks.OBSIDIAN_TILE)
                .checkConfig(Configs.RECIPE_CONFIG)
                .setInput(Blocks.OBSIDIAN)
                .setGroup("nether_obsidian_tile_stonecutter")
                .build();

    }
}
