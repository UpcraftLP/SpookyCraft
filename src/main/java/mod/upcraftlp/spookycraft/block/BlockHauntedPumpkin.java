package mod.upcraftlp.spookycraft.block;

import com.google.common.collect.Lists;
import core.upcraftlp.craftdev.api.block.Block;
import core.upcraftlp.craftdev.api.util.Utils;
import core.upcraftlp.craftdev.api.world.WorldHelper;
import mod.upcraftlp.spookycraft.handler.ScaryNightHandler;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

/**
 * @author UpcraftLP
 */
public class BlockHauntedPumpkin extends Block {

    private static final List<SoundEvent> SOUNDS = Lists.newArrayList(
            SoundEvents.ENTITY_ELDER_GUARDIAN_CURSE,
            SoundEvents.AMBIENT_CAVE,
            SoundEvents.ENTITY_SKELETON_AMBIENT,
            SoundEvents.ENTITY_ARROW_HIT,
            SoundEvents.ENTITY_ZOMBIE_INFECT,
            SoundEvents.ENTITY_ZOMBIE_ATTACK_DOOR_WOOD,
            SoundEvents.ENTITY_ZOMBIE_BREAK_DOOR_WOOD,
            SoundEvents.ENTITY_ZOMBIE_ATTACK_IRON_DOOR
    );
    private static final IProperty<EnumFacing> FACING = BlockHorizontal.FACING;

    public BlockHauntedPumpkin() {
        super("haunted_pumpkin", Material.GOURD, false);
        this.setTickRandomly(true);
        this.setLightLevel(0.7F);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING);
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }

    @SuppressWarnings("deprecation")
    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getHorizontalIndex();
    }

    @Override
    public int tickRate(World worldIn) {
        return 17;
    }

    @Override
    public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random random) {
        super.randomTick(worldIn, pos, state, random);
        if(!worldIn.isRemote && ScaryNightHandler.isNightTime(worldIn)) {
            worldIn.playSound(null, pos.getX() + 0.5D + random.nextInt(10) - 5, pos.getY(), pos.getZ() + 0.5D + random.nextInt(10) - 5, Utils.getRandomElementFromList(SOUNDS), SoundCategory.AMBIENT, 20.0f, random.nextFloat() * 0.6F + 0.4F);
            WorldHelper.spawnParticles(worldIn, EnumParticleTypes.END_ROD, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, random.nextInt(30), 1.0D, 0.6D, 1.0D, 0.05D);
        }
    }

    /**
     * derived from {@link net.minecraft.block.BlockFurnace#randomDisplayTick(IBlockState, World, BlockPos, Random)}
     */
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("incomplete-switch")
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        if (ScaryNightHandler.isNightTime(worldIn))
        {
            EnumFacing enumfacing = stateIn.getValue(FACING);
            double d0 = (double)pos.getX() + 0.5D;
            double d1 = (double)pos.getY() + rand.nextDouble() * 6.0D / 16.0D;
            double d2 = (double)pos.getZ() + 0.5D;
            double d3 = 0.52D;
            double d4 = rand.nextDouble() * 0.6D - 0.3D;

            if (rand.nextDouble() < 0.1D)
            {
                worldIn.playSound((double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
            }

            switch (enumfacing)
            {
                case WEST:
                    worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 - 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
                    worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 - 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
                    break;
                case EAST:
                    worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
                    worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
                    break;
                case NORTH:
                    worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 - 0.52D, 0.0D, 0.0D, 0.0D);
                    worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1, d2 - 0.52D, 0.0D, 0.0D, 0.0D);
                    break;
                case SOUTH:
                    worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 + 0.52D, 0.0D, 0.0D, 0.0D);
                    worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1, d2 + 0.52D, 0.0D, 0.0D, 0.0D);
            }
        }
    }

}
