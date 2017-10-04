package mod.upcraftlp.spookycraft.entity.monster;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class EntitySkeletalCow extends EntitySkeletal {

	public EntitySkeletalCow(World worldIn) {
		super(worldIn);
		this.experienceValue = 10;
		this.setSize(0.9F, 1.4F);

	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);

	}

	public boolean processInteract(EntityPlayer player, EnumHand hand) {
		ItemStack itemstack = player.getHeldItem(hand);
// TODO Make a new bucket type "Bio Milk" 
		if (itemstack.getItem() == Items.BUCKET && !player.capabilities.isCreativeMode && !this.isChild()) {
			player.playSound(SoundEvents.ENTITY_COW_MILK, 1.0F, 1.0F);
			itemstack.shrink(1);

			if (itemstack.isEmpty()) {
				player.setHeldItem(hand, new ItemStack(Items.MILK_BUCKET));
			} else if (!player.inventory.addItemStackToInventory(new ItemStack(Items.MILK_BUCKET))) {
				player.dropItem(new ItemStack(Items.MILK_BUCKET), false);
			}

			return true;
		} else {
			return super.processInteract(player, hand);
		}
	}

	public float getEyeHeight() {
		return this.isChild() ? this.height : 1.3F;
	}

	protected float getSoundVolume() {
		return 0.4F;
	}
}
