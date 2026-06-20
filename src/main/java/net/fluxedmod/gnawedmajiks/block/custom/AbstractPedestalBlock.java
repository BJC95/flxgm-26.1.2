package net.fluxedmod.gnawedmajiks.block.custom;

import com.mojang.serialization.MapCodec;
import net.fluxedmod.gnawedmajiks.block.entity.custom.PriPedestalBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.transfer.item.ItemResource;
import org.jspecify.annotations.Nullable;

public abstract class AbstractPedestalBlock extends BaseEntityBlock {
    public AbstractPedestalBlock(Properties properties) {
        super(properties);
    }

    // BLOCK ENTITY

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return null;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos worldPosition, BlockState blockState) {
        return new PriPedestalBlockEntity(worldPosition, blockState);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player,
                                       ItemStack toolStack, boolean willHarvest, FluidState fluid) {
        if(level.getBlockEntity(pos) instanceof PriPedestalBlockEntity pedestalBlockEntity) {
            pedestalBlockEntity.drops();
            level.updateNeighbourForOutputSignal(pos, this);
        }

        return super.onDestroyedByPlayer(state, level, pos, player, toolStack, willHarvest, fluid);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos,
                                          Player player, InteractionHand hand, BlockHitResult hitResult) {
        if(level.getBlockEntity(pos) instanceof PriPedestalBlockEntity pedestalBlockEntity) {
            boolean isPedestalEmpty = pedestalBlockEntity.inventory.getResource(0).isEmpty();

            // INPUT
            if(isPedestalEmpty && !itemStack.isEmpty()) {
                pedestalBlockEntity.inventory.set(0, ItemResource.of(itemStack),1);
                itemStack.consume(1, player);
                level.playSound(player, pos, SoundEvents.END_PORTAL_FRAME_FILL, SoundSource.BLOCKS, 0.5f, 1.5f);
                particleBurst(pedestalBlockEntity, ParticleTypes.ENCHANT);
            }
            //OUTPUT
            else if (!isPedestalEmpty) {
                ItemStack stackOnPedestal = pedestalBlockEntity.inventory.getResource(0).toStack();
                pedestalBlockEntity.clearContents();

                if(!player.getInventory().add(stackOnPedestal)) {
                    player.drop(stackOnPedestal, false);
                }

                level.playSound(player, pos, SoundEvents.AMETHYST_CLUSTER_BREAK, SoundSource.BLOCKS, 1f, 0.75f);
                particleBurst(pedestalBlockEntity, ParticleTypes.ELECTRIC_SPARK);
            }

        }

        return InteractionResult.SUCCESS;
    }

    protected void particleBurst(PriPedestalBlockEntity pedestalBlockEntity, ParticleOptions particleOptions) {
        double randX;
        double randY;
        double randZ;
        BlockPos pos = pedestalBlockEntity.getBlockPos();
        for (int i = 0; i < 15; i++) {
            randX = (Math.random() - 0.5);
            randY = (Math.random() - 0.5);
            randZ = (Math.random() - 0.5);
            pedestalBlockEntity.getLevel().addParticle(particleOptions,
                    pos.getX()+0.5f, pos.getY()+1.5f,pos.getZ()+0.5f,
                    randX, randY, randZ);
        }
    }
}
