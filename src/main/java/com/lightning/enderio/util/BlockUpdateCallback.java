package com.lightning.enderio.util;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

import net.minecraft.block.Block;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface BlockUpdateCallback {
    Event<BlockUpdateCallback> EVENT = EventFactory.createArrayBacked(BlockUpdateCallback.class,
        (listeners) -> (world, pos, block, posFrom) -> {
            for(BlockUpdateCallback l : listeners) {
                ActionResult result = l.onBlockUpdate(world, pos, block, posFrom);
                if(result != ActionResult.PASS) return result;
            }
            return ActionResult.PASS;
        }
    );

    ActionResult onBlockUpdate(World world, BlockPos pos, Block block, BlockPos posFrom);
}
