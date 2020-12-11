package com.lightning.enderio.misc;

import com.lightning.enderio.util.BlockUpdateCallback;

import net.minecraft.block.Blocks;
import net.minecraft.block.Block;
import net.minecraft.block.FireBlock;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class InfinitySpawning {
    public static class BedrockLightListener implements BlockUpdateCallback {
        @Override
        public ActionResult onBlockUpdate(World world, BlockPos pos, Block block, BlockPos posFrom) {
        	final boolean isFire = world.getBlockState(posFrom).getBlock() instanceof FireBlock;
        	final int posIdx = posFrom.hashCode() + world.getDimension().hashCode()*31;
        	final long worldTime = world.getTime();
        	if(isFire && world.getBlockState(pos).getBlock().is(Blocks.BEDROCK)) {
        		System.out.println("BEDROCK ON FIRE!");
        	}
        	
            return ActionResult.PASS;
        }
    }
}
