package mod.upcraftlp.spookycraft.entity.monster;

import javax.annotation.Nullable;

import mod.upcraftlp.spookycraft.init.SpookyItems;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntitySkeletalPig extends EntitySkeletal {
	private static final DataParameter<Boolean> SADDLED = EntityDataManager.<Boolean>createKey(EntityPig.class,
			DataSerializers.BOOLEAN);
	private static final DataParameter<Integer> BOOST_TIME = EntityDataManager.<Integer>createKey(EntityPig.class,
			DataSerializers.VARINT);
	private boolean boosting;
	private int boostTime;
	private int totalBoostTime;

	public EntitySkeletalPig(World worldIn) {
		super(worldIn);
		this.experienceValue = 10;
		this.setSize(0.9F, 1.4F);

	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4);

	}

	@Nullable
	public Entity getControllingPassenger() {
		return this.getPassengers().isEmpty() ? null : (Entity) this.getPassengers().get(0);
	}

	/**
	 * returns true if all the conditions for steering the entity are met. For
	 * pigs, this is true if it is being ridden by a player and the player is
	 * holding a carrot-on-a-stick
	 */
	public boolean canBeSteered() {
		Entity entity = this.getControllingPassenger();

		if (!(entity instanceof EntityPlayer)) {
			return false;
		} else {
			EntityPlayer entityplayer = (EntityPlayer) entity;
			return entityplayer.getHeldItemMainhand().getItem() == Items.CARROT_ON_A_STICK
					|| entityplayer.getHeldItemOffhand().getItem() == Items.CARROT_ON_A_STICK;
		}
	}

	public void notifyDataManagerChange(DataParameter<?> key) {
		if (BOOST_TIME.equals(key) && this.world.isRemote) {
			this.boosting = true;
			this.boostTime = 0;
			this.totalBoostTime = ((Integer) this.dataManager.get(BOOST_TIME)).intValue();
		}

		super.notifyDataManagerChange(key);
	}

	protected void entityInit() {
		super.entityInit();
		this.dataManager.register(SADDLED, Boolean.valueOf(false));
		this.dataManager.register(BOOST_TIME, Integer.valueOf(0));
	}

	

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setBoolean("Saddle", this.getSaddled());
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		this.setSaddled(compound.getBoolean("Saddle"));
	}

	

	protected void playStepSound(BlockPos pos, Block blockIn) {
		this.playSound(SoundEvents.ENTITY_PIG_STEP, 0.15F, 1.0F);
	}

	public boolean processInteract(EntityPlayer player, EnumHand hand) {
		if (!super.processInteract(player, hand)) {
			ItemStack itemstack = player.getHeldItem(hand);

			if (itemstack.getItem() == Items.NAME_TAG) {
				itemstack.interactWithEntity(player, this, hand);
				return true;
			} else if (this.getSaddled() && !this.isBeingRidden()) {
				if (!this.world.isRemote) {
					player.startRiding(this);
				}

				return true;
			} else if (itemstack.getItem() == Items.SADDLE) {
				itemstack.interactWithEntity(player, this, hand);
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}

	/**
	 * Called when the mob's health reaches 0.
	 */
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);

		if (!this.world.isRemote) {
			if (this.getSaddled()) {
				this.dropItem(Items.SADDLE, 1);
			}
		}
	}

	
	/**
	 * Returns true if the pig is saddled.
	 */
	public boolean getSaddled() {
		return ((Boolean) this.dataManager.get(SADDLED)).booleanValue();
	}

	/**
	 * Set or remove the saddle of the pig.
	 */
	public void setSaddled(boolean saddled) {
		if (saddled) {
			this.dataManager.set(SADDLED, Boolean.valueOf(true));
		} else {
			this.dataManager.set(SADDLED, Boolean.valueOf(false));
		}
	}

	/**
	 * Called when a lightning bolt hits the entity.
	 */
	public void onStruckByLightning(EntityLightningBolt lightningBolt) {
		if (!this.world.isRemote && !this.isDead) {
			EntityWitherSkeleton entitySkeleton = new EntityWitherSkeleton(this.world);
			entitySkeleton.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.GOLDEN_SWORD));
			entitySkeleton.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(Blocks.PUMPKIN));
			entitySkeleton.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, new ItemStack(SpookyItems.CANDY_BAG));
			entitySkeleton.addPotionEffect(new PotionEffect(MobEffects.GLOWING, 250, 255));
			entitySkeleton.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
			entitySkeleton.setNoAI(this.isAIDisabled());

			if (this.hasCustomName()) {
				entitySkeleton.setCustomNameTag(this.getCustomNameTag());
				entitySkeleton.setAlwaysRenderNameTag(this.getAlwaysRenderNameTag());
			}if (!this.hasCustomName()) {
				entitySkeleton.setCustomNameTag("Skeli Warrior");
				entitySkeleton.setAlwaysRenderNameTag(this.getAlwaysRenderNameTag());
			}

			this.world.spawnEntity(entitySkeleton);
			this.setDead();
		}
	}

	public void travel(float strafe, float vertical, float forward) {
		Entity entity = this.getPassengers().isEmpty() ? null : (Entity) this.getPassengers().get(0);

		if (this.isBeingRidden() && this.canBeSteered()) {
			this.rotationYaw = entity.rotationYaw;
			this.prevRotationYaw = this.rotationYaw;
			this.rotationPitch = entity.rotationPitch * 0.5F;
			this.setRotation(this.rotationYaw, this.rotationPitch);
			this.renderYawOffset = this.rotationYaw;
			this.rotationYawHead = this.rotationYaw;
			this.stepHeight = 1.0F;
			this.jumpMovementFactor = this.getAIMoveSpeed() * 0.1F;

			if (this.boosting && this.boostTime++ > this.totalBoostTime) {
				this.boosting = false;
			}

			if (this.canPassengerSteer()) {
				float f = (float) this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue()
						* 0.225F;

				if (this.boosting) {
					f += f * 1.15F
							* MathHelper.sin((float) this.boostTime / (float) this.totalBoostTime * (float) Math.PI);
				}

				this.setAIMoveSpeed(f);
				super.travel(0.0F, 0.0F, 1.0F);
			} else {
				this.motionX = 0.0D;
				this.motionY = 0.0D;
				this.motionZ = 0.0D;
			}

			this.prevLimbSwingAmount = this.limbSwingAmount;
			double d1 = this.posX - this.prevPosX;
			double d0 = this.posZ - this.prevPosZ;
			float f1 = MathHelper.sqrt(d1 * d1 + d0 * d0) * 4.0F;

			if (f1 > 1.0F) {
				f1 = 1.0F;
			}

			this.limbSwingAmount += (f1 - this.limbSwingAmount) * 0.4F;
			this.limbSwing += this.limbSwingAmount;
		} else {
			this.stepHeight = 0.5F;
			this.jumpMovementFactor = 0.02F;
			super.travel(strafe, vertical, forward);
		}
	}

	public boolean boost() {
		if (this.boosting) {
			return false;
		} else {
			this.boosting = true;
			this.boostTime = 0;
			this.totalBoostTime = this.getRNG().nextInt(841) + 140;
			this.getDataManager().set(BOOST_TIME, Integer.valueOf(this.totalBoostTime));
			return true;
		}
	}

	public EntityPig createChild(EntityAgeable ageable) {
		return new EntityPig(this.world);
	}

}
