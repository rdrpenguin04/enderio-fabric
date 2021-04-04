package com.lightning.enderio.mixin;

import com.lightning.enderio.util.BlockUpdateCallback;

import net.minecraft.block.Block;
import net.minecraft.block.AbstractBlock.AbstractBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractBlockState.class)
public class BlockUpdateMixin {
    @Inject(method = "neighborUpdate", at = @At("HEAD"))
    private void blockUpdateTrigger(World world, BlockPos pos, Block block, BlockPos posFrom, boolean _notify, CallbackInfo _ci) {
        if(world.isClient) return;

        BlockUpdateCallback.EVENT.invoker().onBlockUpdate(world, pos, block, posFrom);
    }
}
