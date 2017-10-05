package mod.upcraftlp.spookycraft.block.fluid;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * @author UpcraftLP
 */
public class BlockFluidBoneMilk extends BlockFluidBase {

    public BlockFluidBoneMilk(FluidBase fluid) {
        super(fluid);
    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        super.onEntityCollidedWithBlock(worldIn, pos, state, entityIn);
        //TODO apply effects
    }
}
