package mod.upcraftlp.spookycraft.block.fluid;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.potion.PotionEffect;
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
        if(worldIn.isRemote) return;
        if(entityIn instanceof EntityLivingBase) {
            EntityLivingBase entityLiving = (EntityLivingBase) entityIn;
            entityLiving.curePotionEffects(new ItemStack(Items.MILK_BUCKET));
            entityLiving.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 600, 2)); //30 seconds
            entityLiving.addPotionEffect(new PotionEffect(MobEffects.LEVITATION, 100, 7)); //10 seconds
            entityIn.addVelocity(0.0D, 2.0D, 0.0D);
            if(entityIn instanceof EntityPlayerMP) {
                EntityPlayerMP playerMP = (EntityPlayerMP) entityIn;
                playerMP.connection.sendPacket(new SPacketEntityVelocity(playerMP));
            }
        }
    }
}
