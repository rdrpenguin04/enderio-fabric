package com.lightning.enderio.misc;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import com.lightning.enderio.EIOItems;
import com.lightning.enderio.util.BlockUpdateCallback;

import net.minecraft.block.Blocks;
import net.minecraft.block.Block;
import net.minecraft.block.FireBlock;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class InfinitySpawning {
    private static final Random RANDOM = new Random();
    private static final Map<Integer, Long> fires = new HashMap<>();
    
    public static class BedrockLightListener implements BlockUpdateCallback {
        @Override
        public ActionResult onBlockUpdate(World world, BlockPos pos, Block block, BlockPos posFrom) {
            if(!pos.equals(posFrom.down())) return ActionResult.PASS;
            final boolean isFire = world.getBlockState(posFrom).getBlock() instanceof FireBlock;
            final int posIdx = posFrom.hashCode() + world.getDimension().hashCode()*31;
            final long worldTime = world.getTime();
            if(isFire && world.getBlockState(pos).getBlock().is(Blocks.BEDROCK)) {
                if(fires.size() > 100) {
                    // This is supposed to remove extra fires. But... we don't need that, do we?
                }
                fires.putIfAbsent(posIdx, worldTime + 260);
                System.out.println("BEDROCK ON FIRE!");
            } else if(fires.containsKey(posIdx)) {
                if(world.isAir(posFrom) && world.getBlockState(pos).getBlock().is(Blocks.BEDROCK) && worldTime > fires.get(posIdx)) {
                    spawnInfinityPowder((ServerWorld) world, pos);
                }
            }
            
            return ActionResult.PASS;
        }
    }
    
    public static void spawnInfinityPowder(ServerWorld world, BlockPos pos) {
        if(RANDOM.nextFloat() >= 0.5f) {
            float dx = RANDOM.nextFloat() * 0.5f + 0.25f; // 0.25 - 0.75
            float dy = RANDOM.nextFloat() * 0.5f + 0.25f; // 0.25 - 0.75
            float dz = RANDOM.nextFloat() * 0.5f + 0.25f; // 0.25 - 0.75
            ItemEntity item = new ItemEntity(world, pos.getX() + dx, pos.getY() + dy, pos.getZ() + dz, new ItemStack(EIOItems.INFINITY_DUST_ITEM));
            item.setToDefaultPickupDelay();
            item.damage(DamageSource.IN_FIRE, -100);
            item.setOnFireFor(10);
            world.spawnEntity(item);
            world.playSound(null, pos, SoundEvents.ENTITY_FIREWORK_ROCKET_LARGE_BLAST, SoundCategory.BLOCKS, 1.0f, RANDOM.nextFloat() * 0.4f + 0.8f);
        }
    }
}
