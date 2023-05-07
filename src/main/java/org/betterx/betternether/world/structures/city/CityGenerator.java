package org.betterx.betternether.world.structures.city;

import org.betterx.betternether.world.structures.city.palette.CityPalette;
import org.betterx.betternether.world.structures.piece.CityPiece;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Rotation;

import java.util.ArrayList;
import java.util.List;

public class CityGenerator {
    private final List<StructureCityBuilding> centers = new ArrayList<StructureCityBuilding>();
    private final List<StructureCityBuilding> buildings = new ArrayList<StructureCityBuilding>();
    private final List<StructureCityBuilding> roadEnds = new ArrayList<StructureCityBuilding>();
    private final List<StructureCityBuilding> total = new ArrayList<StructureCityBuilding>();
    private final List<BoundingBox2D> bounds = new ArrayList<BoundingBox2D>();
    private final List<BlockPos> ends = new ArrayList<BlockPos>();
    private final List<BlockPos> add = new ArrayList<BlockPos>();
    private final List<BlockPos> rem = new ArrayList<BlockPos>();

    public CityGenerator() {
        addBuildingToList("city_center_01", -10, centers);
        addBuildingToList("city_center_02", -10, centers);
        addBuildingToList("city_center_03", -10, centers);
        addBuildingToList("city_center_04", -10, centers);

        addBuildingToList("city_library_01", buildings);
        addBuildingToList("city_library_02", buildings);

        addBuildingToList("city_tower_01", buildings);
        addBuildingToList("city_tower_02", buildings);
        addBuildingToList("city_tower_03", buildings);
        addBuildingToList("city_tower_04", buildings);

        addBuildingToList("city_building_01", buildings);
        addBuildingToList("city_building_02", buildings);
        addBuildingToList("city_building_03", buildings);
        addBuildingToList("city_building_04", buildings);
        addBuildingToList("city_building_05", buildings);
        addBuildingToList("city_building_06", buildings);
        addBuildingToList("city_building_07", buildings);
        addBuildingToList("city_building_08", buildings);
        addBuildingToList("city_building_09", buildings);
        addBuildingToList("city_building_10", buildings);
        addBuildingToList("city_building_11", buildings);
        addBuildingToList("city_building_12", buildings);
        addBuildingToList("city_building_13", buildings);
        addBuildingToList("city_building_14", buildings);
        addBuildingToList("city_building_15", buildings);

        addBuildingToList("city_enchanter_01", buildings);
        addBuildingToList("city_enchanter_02", buildings);

        addBuildingToList("city_park_01", buildings);
        addBuildingToList("city_park_02", buildings);
        addBuildingToList("city_park_03", buildings);

        addBuildingToList("city_bridge_01", buildings);

        addBuildingToList("ramp_01", buildings);

        addBuildingToList("road_cross_01", buildings);

        addBuildingToList("road_end_01", roadEnds);
        addBuildingToList("road_end_02", -2, roadEnds);

        total.addAll(centers);
        total.addAll(buildings);
        total.addAll(roadEnds);
    }

    private void addBuildingToList(String name, List<StructureCityBuilding> buildings) {
        addBuildingToList(name, 0, buildings);
    }

    private void addBuildingToList(String name, int offsetY, List<StructureCityBuilding> buildings) {
        StructureCityBuilding building = new StructureCityBuilding("city/" + name, offsetY);
        buildings.add(building);
        buildings.add(building.getRotated(Rotation.CLOCKWISE_90));
        buildings.add(building.getRotated(Rotation.CLOCKWISE_180));
        buildings.add(building.getRotated(Rotation.COUNTERCLOCKWISE_90));
    }

    private void placeCenterBuilding(
            BlockPos pos,
            StructureCityBuilding building,
            ArrayList<CityPiece> city,
            RandomSource random,
            CityPalette palette
    ) {
        BoundingBox2D bb = building.getBoungingBox().offset(pos);
        bounds.add(bb);
        city.add(new CityPiece(building, pos.offset(0, building.getYOffset(), 0), random.nextInt(), palette));
        for (int i = 0; i < building.getEndsCount(); i++)
            ends.add(pos.offset(building.getOffsettedPos(i).offset(0, building.getYOffset(), 0)));
    }

    private void attachBuildings(RandomSource random, ArrayList<CityPiece> city, CityPalette palette) {
        for (BlockPos pos : ends) {
            boolean generate = true;
            for (int n = 0; n < 8 && generate; n++) {
                int b = random.nextInt(buildings.size() >> 2) << 2;
                for (int r = 0; r < 4 && generate; r++) {
                    StructureCityBuilding building = buildings.get(b | r);
                    int index = random.nextInt(building.getEndsCount());
                    BlockPos offset = building.getPos(index);
                    BoundingBox2D bb = building.getBoungingBox().offset(pos).offsetNegative(offset);
                    if (noCollisions(bb)) {
                        boolean validHeight = true;
                        BlockPos npos = new BlockPos(bb.x1, pos.getY() - offset.getY() + building.getYOffset(), bb.z1);
                        for (int i = 0; i < building.getEndsCount(); i++)
                            if (i != index)
                                if (npos.getY() + building.getOffsettedPos(i).getY() < 32) {
                                    validHeight = false;
                                    break;
                                }
                        if (validHeight) {
                            bounds.add(bb);
                            rem.add(pos);
                            for (int i = 0; i < building.getEndsCount(); i++)
                                if (i != index)
                                    add.add(npos.offset(building.getOffsettedPos(i)));
                            city.add(new CityPiece(building, npos, random.nextInt(), palette));
                            generate = false;
                        }
                    }
                }
            }
        }
        ends.removeAll(rem);
        ends.addAll(add);
        rem.clear();
        add.clear();
    }

    private void closeRoads(ArrayList<CityPiece> city, RandomSource random, CityPalette palette) {
        for (BlockPos pos : ends) {
            for (int n = 0; n < roadEnds.size(); n++) {
                StructureCityBuilding building = roadEnds.get(n);
                BlockPos offset = building.getPos(0);
                BoundingBox2D bb = building.getBoungingBox().offset(pos).offsetNegative(offset);
                if (noCollisions(bb)) {
                    BlockPos npos = new BlockPos(bb.x1, pos.getY() - offset.getY() + building.getYOffset(), bb.z1);
                    bounds.add(bb);
                    city.add(new CityPiece(building, npos, random.nextInt(), palette));
                    break;
                }
            }
        }
        ends.clear();
        bounds.clear();
        rem.clear();
        add.clear();
    }

    public ArrayList<CityPiece> generate(BlockPos pos, RandomSource random, CityPalette palette) {
        ArrayList<CityPiece> city = new ArrayList<CityPiece>();
        placeCenterBuilding(pos, centers.get(random.nextInt(centers.size())), city, random, palette);

        float rnd = random.nextFloat();
        rnd *= rnd;
        rnd *= rnd;
        int iterations = Math.round(2 + rnd * 2);

        for (int i = 0; i < iterations; i++)
            attachBuildings(random, city, palette);
        closeRoads(city, random, palette);
        return city;
    }

    private boolean noCollisions(BoundingBox2D bb) {
        for (BoundingBox2D b : bounds)
            if (bb.isColliding(b))
                return false;
        return true;
    }

    public List<StructureCityBuilding> getBuildings() {
        return total;
    }
}
