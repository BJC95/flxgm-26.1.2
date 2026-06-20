package net.fluxedmod.gnawedmajiks.block.entity.custom;

import net.fluxedmod.gnawedmajiks.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class TriPedestalBlockEntity extends AbstractPedestalBlockEntity {
    public TriPedestalBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(ModBlockEntities.TRI_PEDESTAL_BE.get(), worldPosition, blockState);
    }
}