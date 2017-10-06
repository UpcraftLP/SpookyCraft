package mod.upcraftlp.spookycraft.entity.monster;

import com.google.common.collect.Lists;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntitySkeletalSheep extends EntitySkeletal implements net.minecraftforge.common.IShearable {
	/**
	 * Used to control movement as well as wool regrowth. Set to 40 on
	 * handleHealthUpdate and counts down with each tick.
	 */
	private int sheepTimer;

	public EntitySkeletalSheep(World worldIn) {
		super(worldIn);
		this.setSize(0.9F, 1.3F);
	}

	/**
	 * Called frequently so the entity can update its state every tick as
	 * required. For example, zombies and skeletons use this to react to
	 * sunlight and start to burn.
	 */
	public void onLivingUpdate() {
		if (this.world.isRemote) {
			this.sheepTimer = Math.max(0, this.sheepTimer - 1);
		}

		super.onLivingUpdate();
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
	}

	/**
	 * Handler for {@link World#setEntityState}
	 */
	@SideOnly(Side.CLIENT)
	public void handleStatusUpdate(byte id) {
		if (id == 10) {
			this.sheepTimer = 40;
		} else {
			super.handleStatusUpdate(id);
		}
	}

	@SideOnly(Side.CLIENT)
	public float getHeadRotationPointY(float p_70894_1_) {
		if (this.sheepTimer <= 0) {
			return 0.0F;
		} else if (this.sheepTimer >= 4 && this.sheepTimer <= 36) {
			return 1.0F;
		} else {
			return this.sheepTimer < 4 ? ((float) this.sheepTimer - p_70894_1_) / 4.0F
					: -((float) (this.sheepTimer - 40) - p_70894_1_) / 4.0F;
		}
	}

	@SideOnly(Side.CLIENT)
	public float getHeadRotationAngleX(float p_70890_1_) {
		if (this.sheepTimer > 4 && this.sheepTimer <= 36) {
			float f = ((float) (this.sheepTimer - 4) - p_70890_1_) / 32.0F;
			return ((float) Math.PI / 5F) + ((float) Math.PI * 7F / 100F) * MathHelper.sin(f * 28.7F);
		} else {
			return this.sheepTimer > 0 ? ((float) Math.PI / 5F) : this.rotationPitch * 0.017453292F;
		}
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setBoolean("Sheared", this.getSheared());
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		this.setSheared(compound.getBoolean("Sheared"));
	}

	public boolean getSheared() {
		return true;
	}

	/**
	 * make a sheep sheared if set to true
	 */
	public void setSheared(boolean sheared) {

	}

	@Override
	public boolean isShearable(ItemStack item, net.minecraft.world.IBlockAccess world, BlockPos pos) {
		return true;
	}

	@Override
	public java.util.List<ItemStack> onSheared(ItemStack item, net.minecraft.world.IBlockAccess world, BlockPos pos,
			int fortune) {
		this.setSheared(true);
		int i = 1 + this.rand.nextInt(3);

		java.util.List<ItemStack> ret = Lists.newArrayList();
		for (int j = 0; j < i; ++j)
			ret.add(new ItemStack(Items.BONE));

		this.playSound(SoundEvents.ENTITY_SHEEP_SHEAR, 1.0F, 1.0F);
		return ret;
	}

	public float getEyeHeight() {
		return 0.95F * this.height;
	}

}
