package mod.upcraftlp.spookycraft.entity.monster;

import mod.upcraftlp.spookycraft.Reference;
import mod.upcraftlp.spookycraft.init.SpookyItems;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntitySkeletal extends EntityMob {

	public static final ResourceLocation LOOT_SKELETAL = new ResourceLocation(Reference.MODID, "entities/skeletal");

	public EntitySkeletal(World worldIn) {
		super(worldIn);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
	}

	@Override
	protected void initEntityAI() {
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
		this.tasks.addTask(4, new EntityAIAttackMelee(this, 1.5D, false));
		this.tasks.addTask(5, new EntityAIWanderAvoidWater(this, 0.8D));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(6, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(2, new EntitySkeletal.AIskeletalTarget<>(this, EntityPlayer.class));
		this.targetTasks.addTask(3, new EntitySkeletal.AIskeletalTarget<>(this, EntityIronGolem.class));
	}

	public boolean isAngry() {
		return this.getBrightness() > 0.5D;
	}

	static class AIskeletalTarget<T extends EntityLivingBase> extends EntityAINearestAttackableTarget<T> {
		public AIskeletalTarget(EntitySkeletal skeletal, Class<T> classTarget) {
			super(skeletal, classTarget, true);
		}

		/**
		 * Returns whether the EntityAIBase should begin execution.
		 */
		public boolean shouldExecute() {
			return this.taskOwner.getBrightness() < 0.5F && super.shouldExecute();
		}
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_SKELETON_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.ENTITY_SKELETON_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_SKELETON_DEATH;
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn) {
		this.playSound(SoundEvents.ENTITY_SKELETON_STEP, 0.15F, 1.0F);
	}

	@Override
	public boolean hitByEntity(Entity entityIn)
	{
		if (!this.world.isRemote) {
			if(rand.nextDouble() < 1.0D) this.entityDropItem(new ItemStack(SpookyItems.CANDY_CORN, 1 + rand.nextInt(3)), 0.2F);
		}
		return false;
	}

	@Override
	public void onDeath(DamageSource cause) {
		if (!this.world.isRemote) {
			this.entityDropItem(new ItemStack(Items.BONE, rand.nextInt(5)), 0.2F);
		}
		super.onDeath(cause);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
	}
}
