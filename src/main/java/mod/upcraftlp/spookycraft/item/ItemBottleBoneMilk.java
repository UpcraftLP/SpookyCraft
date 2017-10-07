package mod.upcraftlp.spookycraft.item;

import core.upcraftlp.craftdev.api.item.Item;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

/**
 * @author UpcraftLP
 */
public class ItemBottleBoneMilk extends Item {

    public ItemBottleBoneMilk() {
        super("bone_milk_bottle");
        this.setMaxStackSize(16);
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 32;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.DRINK;
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        if(entityLiving instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entityLiving;
            player.curePotionEffects(new ItemStack(Items.MILK_BUCKET));
            player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 1800, 1));
            player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 600, 5));
            player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 600, 3));
            //TODO adjust effects?
        }
        return stack;
    }
}
