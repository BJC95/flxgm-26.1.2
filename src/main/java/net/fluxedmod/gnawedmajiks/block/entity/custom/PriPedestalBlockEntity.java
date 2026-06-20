package net.fluxedmod.gnawedmajiks.block.entity.custom;

import net.fluxedmod.gnawedmajiks.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class PriPedestalBlockEntity extends AbstractPedestalBlockEntity {
    public PriPedestalBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(ModBlockEntities.PRI_PEDESTAL_BE.get(), worldPosition, blockState);
    }
}