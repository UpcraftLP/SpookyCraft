package mod.upcraftlp.spookycraft.item;

import core.upcraftlp.craftdev.api.item.ItemFood;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSpecialCandy extends ItemFood {
	public ItemSpecialCandy() {
		super("candy_bag", 10, 8.0f ,false);
		this.setFull3D();
		this.setAlwaysEdible();
	}

	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
		if (!worldIn.isRemote) {
			player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 400, 1));
			player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 6000, 0));
			player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 6000, 0));
			player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 6000, 3));
			player.addPotionEffect(new PotionEffect(MobEffects.SATURATION, 6000, 3));
		}
	}
}
