package cn.gtemc.datapacks.extension.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import org.jetbrains.annotations.NotNull;

public class TestFeature extends Feature<NoneFeatureConfiguration> {

    public TestFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(@NotNull FeaturePlaceContext<NoneFeatureConfiguration> context) {
        BlockPos origin = context.origin();
        WorldGenLevel level = context.level();
        RandomSource random = context.random();
        int baseRadius = 2 + random.nextInt(2); // 调整这里控制半径
        for (int x = -baseRadius - 1; x <= baseRadius + 1; x++) {
            for (int y = -baseRadius; y <= baseRadius; y++) {
                for (int z = -baseRadius - 1; z <= baseRadius + 1; z++) {
                    BlockPos pos = origin.offset(x, y, z);
                    double distance = Math.sqrt(x * x + y * y + z * z);
                    double noise = (random.nextDouble() - 0.5) * 0.6;
                    double adjustedRadius = baseRadius + noise;
                    if (distance <= Math.max(adjustedRadius, baseRadius * 0.8)) {
                        if (level.getBlockState(pos).canBeReplaced()) {
                            if (random.nextFloat() < 0.7f) {
                                level.setBlock(pos, Blocks.MOSSY_COBBLESTONE.defaultBlockState(), 3);
                            } else if (random.nextFloat() < 0.5f) {
                                level.setBlock(pos, Blocks.MOSSY_STONE_BRICKS.defaultBlockState(), 3);
                            } else {
                                level.setBlock(pos, Blocks.COBBLESTONE.defaultBlockState(), 3);
                            }
                        }
                    }
                }
            }
        }
        addSurfaceDecorations(level, origin, baseRadius + 1, random);
        return true;
    }

    private void addSurfaceDecorations(WorldGenLevel level, BlockPos origin, int radius, RandomSource random) {
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                for (int y = radius; y >= -radius; y--) {
                    BlockPos checkPos = origin.offset(x, y, z);
                    BlockPos abovePos = checkPos.above();
                    if (!level.getBlockState(checkPos).isAir() && level.getBlockState(abovePos).canBeReplaced()) {
                        if (canSupportDecoration(level.getBlockState(checkPos).getBlock()) && random.nextFloat() < 0.15f) {
                            if (level.getBlockState(checkPos).is(Blocks.GRASS_BLOCK) || level.getBlockState(checkPos).is(Blocks.DIRT)) {
                                if (random.nextFloat() < 0.6f) {
                                    level.setBlock(abovePos, Blocks.SHORT_GRASS.defaultBlockState(), 3);
                                } else if (random.nextFloat() < 0.3f) {
                                    level.setBlock(abovePos, Blocks.DANDELION.defaultBlockState(), 3);
                                }
                            } else {
                                if (random.nextFloat() < 0.7f) {
                                    level.setBlock(abovePos, Blocks.MOSS_CARPET.defaultBlockState(), 3);
                                } else {
                                    level.setBlock(abovePos, Blocks.MOSS_BLOCK.defaultBlockState(), 3);
                                }
                            }
                        }
                        break;
                    }
                }
            }
        }
    }

    private boolean canSupportDecoration(net.minecraft.world.level.block.Block block) {
        return block == Blocks.GRASS_BLOCK ||
                block == Blocks.DIRT ||
                block == Blocks.MOSSY_COBBLESTONE ||
                block == Blocks.COBBLESTONE ||
                block == Blocks.MOSSY_STONE_BRICKS ||
                block == Blocks.MOSS_BLOCK;
    }
}