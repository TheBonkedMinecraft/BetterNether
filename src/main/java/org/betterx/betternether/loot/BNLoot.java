package org.betterx.betternether.loot;

import org.betterx.bclib.util.LootUtil;
import org.betterx.betternether.BetterNether;
import org.betterx.betternether.registry.NetherBlocks;

import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.Deserializers;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;

import com.google.common.collect.Lists;
import com.google.gson.Gson;

import java.util.ArrayList;


public class BNLoot {
    private static final Gson GSON = Deserializers.createLootTableSerializer().create();

    public static void register() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, table, setter) -> {
            if (BuiltInLootTables.RUINED_PORTAL.equals(id) || BuiltInLootTables.NETHER_BRIDGE.equals(id)) {
                table.withPool(LootPool.lootPool().setRolls(UniformGenerator.between(0, 4))
                                       .add(LootItem.lootTableItem(NetherBlocks.BLUE_OBSIDIAN.asItem())
                                                    .setWeight(1)
                                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(
                                                            1.0F,
                                                            2.0F
                                                    ))))
                                       .add(EmptyLootItem.emptyItem()
                                                         .setWeight(9)));
            } else if (BuiltInLootTables.BASTION_BRIDGE.equals(id) || BuiltInLootTables.BASTION_HOGLIN_STABLE.equals(id) || BuiltInLootTables.BASTION_TREASURE.equals(
                    id)) {
                table.withPool(LootPool.lootPool()
                                       .setRolls(UniformGenerator.between(1, 2))
                                       .add(LootItem.lootTableItem(NetherBlocks.BLUE_CRYING_OBSIDIAN.asItem())
                                                    .setWeight(5)
                                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(
                                                            3.0F,
                                                            8.0F
                                                    ))))
                                       .add(LootItem
                                               .lootTableItem(NetherBlocks.BLUE_WEEPING_OBSIDIAN.asItem())
                                               .setWeight(1)
                                               .apply(SetItemCountFunction.setCount(UniformGenerator.between(
                                                       1.0F,
                                                       4.0F
                                               ))))
                                       .add(LootItem.lootTableItem(NetherBlocks.WEEPING_OBSIDIAN.asItem())
                                                    .setWeight(1)
                                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(
                                                            1.0F,
                                                            4.0F
                                                    ))))
                                       .add(EmptyLootItem.emptyItem()
                                                         .setWeight(50)));
            } else if (BuiltInLootTables.BASTION_OTHER.equals(id)) {
                table.withPool(LootPool.lootPool()
                                       .setRolls(UniformGenerator.between(1, 2))
                                       .add(LootItem.lootTableItem(NetherBlocks.BLUE_OBSIDIAN.asItem())
                                                    .setWeight(10)
                                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(
                                                            4.0F,
                                                            6.0F
                                                    ))))
                                       .add(LootItem.lootTableItem(NetherBlocks.BLUE_CRYING_OBSIDIAN.asItem())
                                                    .setWeight(5)
                                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(
                                                            1.0F,
                                                            5.0F
                                                    ))))
                                       .add(LootItem
                                               .lootTableItem(NetherBlocks.BLUE_WEEPING_OBSIDIAN.asItem())
                                               .setWeight(1)
                                               .apply(SetItemCountFunction.setCount(UniformGenerator.between(
                                                       1.0F,
                                                       2.0F
                                               ))))
                                       .add(LootItem.lootTableItem(NetherBlocks.WEEPING_OBSIDIAN.asItem())
                                                    .setWeight(1)
                                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(
                                                            1.0F,
                                                            2.0F
                                                    ))))
                                       .add(EmptyLootItem.emptyItem()
                                                         .setWeight(50)));
            } else if (BuiltInLootTables.PIGLIN_BARTERING.equals(id)) {
                ArrayList<LootPoolEntryContainer> list = Lists.newArrayList();
                list.add(LootItem
                        .lootTableItem(NetherBlocks.BLUE_OBSIDIAN.asItem())
                        .setWeight(40).build());
                list.add(LootItem
                        .lootTableItem(NetherBlocks.BLUE_CRYING_OBSIDIAN.asItem())
                        .setWeight(40)
                        .apply(SetItemCountFunction.setCount(
                                UniformGenerator.between(
                                        1.0F,
                                        3.0F
                                ))).build());
                list.add(LootItem
                        .lootTableItem(NetherBlocks.BLUE_WEEPING_OBSIDIAN.asItem())
                        .setWeight(20)
                        .apply(SetItemCountFunction.setCount(
                                UniformGenerator.between(
                                        1.0F,
                                        2.0F
                                ))).build());
                list.add(LootItem
                        .lootTableItem(NetherBlocks.WEEPING_OBSIDIAN.asItem())
                        .setWeight(20)
                        .apply(SetItemCountFunction.setCount(
                                UniformGenerator.between(
                                        1.0F,
                                        2.0F
                                ))).build());

                if (!LootUtil.addToPool(table, 0, list)) {
                    BetterNether.LOGGER.error("Unable to building bartering table");
                }
            }
        });
    }
}
