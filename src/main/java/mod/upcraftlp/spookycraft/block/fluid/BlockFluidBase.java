package mod.upcraftlp.spookycraft.block.fluid;

import mod.upcraftlp.spookycraft.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;

import javax.annotation.Nonnull;

/**
 * (c)2017 UpcraftLP
 */
public class BlockFluidBase extends BlockFluidClassic {

    private final int color;

    public BlockFluidBase(FluidBase fluid) {
        super(fluid, fluid.isHot() ? Material.LAVA : Material.WATER);
        this.setRegistryName("fluid_" + fluid.getName());
        this.setUnlocalizedName("fluid." + Reference.MODID + "." + fluid.getName());
        this.color = fluid.getColor();
    }

    public int getColor() {
        return this.color;
    }

    @Override
    public void neighborChanged(@Nonnull IBlockState state, @Nonnull World world, @Nonnull BlockPos pos, @Nonnull Block neighborBlock, @Nonnull BlockPos neighbourPos) {
        super.neighborChanged(state, world, pos, neighborBlock, neighbourPos);
    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        if(this.blockMaterial == Material.LAVA) entityIn.setFire(5);
        super.onEntityCollidedWithBlock(worldIn, pos, state, entityIn);
    }


}
