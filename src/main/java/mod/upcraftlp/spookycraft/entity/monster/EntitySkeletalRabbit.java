package mod.upcraftlp.spookycraft.entity.monster;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCarrot;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIMoveToBlock;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityJumpHelper;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDesert;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntitySkeletalRabbit extends EntitySkeletal {
	private int jumpTicks;
	private int jumpDuration;
	private boolean wasOnGround;
	private int currentMoveTypeDuration;
	private int carrotTicks;

	public EntitySkeletalRabbit(World worldIn) {
		super(worldIn);
		this.setSize(0.4F, 0.5F);
		this.jumpHelper = new EntitySkeletalRabbit.RabbitJumpHelper(this);
		this.moveHelper = new EntitySkeletalRabbit.RabbitMoveHelper(this);
		this.setMovementSpeed(0.0D);
	}

	@Override
	protected void initEntityAI() {
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
		this.tasks.addTask(4, new EntityAIAttackMelee(this, 3.0, false));
		this.tasks.addTask(5, new EntityAIWanderAvoidWater(this, 0.6D));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(6, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
		this.targetTasks.addTask(2, new EntitySkeletal.AIskeletalTarget(this, EntityPlayer.class));
		this.targetTasks.addTask(3, new EntitySkeletal.AIskeletalTarget(this, EntityIronGolem.class));
		this.tasks.addTask(4, new EntitySkeletalRabbit.AIEvilAttack(this));
		this.tasks.addTask(1, new EntitySkeletalRabbit.AIPanic(this, 2.2D));
		this.tasks.addTask(5, new EntitySkeletalRabbit.AIRaidFarm(this));
	}

	protected float getJumpUpwardsMotion() {
		if (!this.collidedHorizontally
				&& (!this.moveHelper.isUpdating() || this.moveHelper.getY() <= this.posY + 0.5D)) {
			Path path = this.navigator.getPath();

			if (path != null && path.getCurrentPathIndex() < path.getCurrentPathLength()) {
				Vec3d vec3d = path.getPosition(this);

				if (vec3d.y > this.posY + 0.5D) {
					return 0.5F;
				}
			}

			return this.moveHelper.getSpeed() <= 0.6D ? 0.2F : 0.3F;
		} else {
			return 0.5F;
		}
	}

	/**
	 * Causes this entity to do an upwards motion (jumping).
	 */
	protected void jump() {
		super.jump();
		double d0 = this.moveHelper.getSpeed();

		if (d0 > 0.0D) {
			double d1 = this.motionX * this.motionX + this.motionZ * this.motionZ;

			if (d1 < 0.010000000000000002D) {
				this.moveRelative(0.0F, 0.0F, 1.0F, 0.1F);
			}
		}

		if (!this.world.isRemote) {
			this.world.setEntityState(this, (byte) 1);
		}
	}

	@SideOnly(Side.CLIENT)
	public float setJumpCompletion(float p_175521_1_) {
		return this.jumpDuration == 0 ? 0.0F : ((float) this.jumpTicks + p_175521_1_) / (float) this.jumpDuration;
	}

	public void setMovementSpeed(double newSpeed) {
		this.getNavigator().setSpeed(newSpeed);
		this.moveHelper.setMoveTo(this.moveHelper.getX(), this.moveHelper.getY(), this.moveHelper.getZ(), newSpeed);
	}

	public void setJumping(boolean jumping) {
		super.setJumping(jumping);

		if (jumping) {
			this.playSound(this.getJumpSound(), this.getSoundVolume(),
					((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) * 0.8F);
		}
	}

	public void startJumping() {
		this.setJumping(true);
		this.jumpDuration = 10;
		this.jumpTicks = 0;
	}

	public void updateAITasks() {
		if (this.currentMoveTypeDuration > 0) {
			--this.currentMoveTypeDuration;
		}

		if (this.carrotTicks > 0) {
			this.carrotTicks -= this.rand.nextInt(3);

			if (this.carrotTicks < 0) {
				this.carrotTicks = 0;
			}
		}

		if (this.onGround) {
			if (!this.wasOnGround) {
				this.setJumping(false);
				this.checkLandingDelay();
			}

			EntityLivingBase entitylivingbase = this.getAttackTarget();

			if (entitylivingbase != null && this.getDistanceSq(entitylivingbase) < 16.0D) {
				this.calculateRotationYaw(entitylivingbase.posX, entitylivingbase.posZ);
				this.moveHelper.setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ,
						this.moveHelper.getSpeed());
				this.startJumping();
				this.wasOnGround = true;

			}

			EntitySkeletalRabbit.RabbitJumpHelper entityrabbit$rabbitjumphelper = (EntitySkeletalRabbit.RabbitJumpHelper) this.jumpHelper;

			if (!entityrabbit$rabbitjumphelper.getIsJumping()) {
				if (this.moveHelper.isUpdating() && this.currentMoveTypeDuration == 0) {
					Path path = this.navigator.getPath();
					Vec3d vec3d = new Vec3d(this.moveHelper.getX(), this.moveHelper.getY(), this.moveHelper.getZ());

					if (path != null && path.getCurrentPathIndex() < path.getCurrentPathLength()) {
						vec3d = path.getPosition(this);
					}

					this.calculateRotationYaw(vec3d.x, vec3d.z);
					this.startJumping();
				}
			} else if (!entityrabbit$rabbitjumphelper.canJump()) {
				this.enableJumpControl();
			}
		}

		this.wasOnGround = this.onGround;
	}

	/**
	 * Attempts to create sprinting particles if the entity is sprinting and not
	 * in water.
	 */
	public void spawnRunningParticles() {
	}

	private void calculateRotationYaw(double x, double z) {
		this.rotationYaw = (float) (MathHelper.atan2(z - this.posZ, x - this.posX) * (180D / Math.PI)) - 90.0F;
	}

	private void enableJumpControl() {
		((EntitySkeletalRabbit.RabbitJumpHelper) this.jumpHelper).setCanJump(true);
	}

	private void disableJumpControl() {
		((EntitySkeletalRabbit.RabbitJumpHelper) this.jumpHelper).setCanJump(false);
	}

	private void updateMoveTypeDuration() {
		if (this.moveHelper.getSpeed() < 2.2D) {
			this.currentMoveTypeDuration = 10;
		} else {
			this.currentMoveTypeDuration = 1;
		}
	}

	private void checkLandingDelay() {
		this.updateMoveTypeDuration();
		this.disableJumpControl();
	}

	/**
	 * Called frequently so the entity can update its state every tick as
	 * required. For example, zombies and skeletons use this to react to
	 * sunlight and start to burn.
	 */
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (this.jumpTicks != this.jumpDuration) {
			++this.jumpTicks;
		} else if (this.jumpDuration != 0) {
			this.jumpTicks = 0;
			this.jumpDuration = 0;
			this.setJumping(false);
		}
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();

		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(3.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30000001192092896D);
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);

		compound.setInteger("MoreCarrotTicks", this.carrotTicks);
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);

		this.carrotTicks = compound.getInteger("MoreCarrotTicks");
	}

	protected SoundEvent getJumpSound() {
		return SoundEvents.ENTITY_RABBIT_JUMP;
	}

	public boolean attackEntityAsMob(Entity entityIn) {

		this.playSound(SoundEvents.ENTITY_RABBIT_ATTACK, 1.0F,
				(this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
		return entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 8.0F);

	}

	/**
	 * Called when the entity is attacked.
	 */
	public boolean attackEntityFrom(DamageSource source, float amount) {
		return this.isEntityInvulnerable(source) ? false : super.attackEntityFrom(source, amount);
	}

	private int getRandomRabbitType() {
		Biome biome = this.world.getBiome(new BlockPos(this));
		int i = this.rand.nextInt(100);

		if (biome.isSnowyBiome()) {
			return i < 80 ? 1 : 3;
		} else if (biome instanceof BiomeDesert) {
			return 4;
		} else {
			return i < 50 ? 0 : (i < 90 ? 5 : 2);
		}
	}

	/**
	 * Returns true if
	 * {@link net.minecraft.entity.passive.EntitySkeletalRabbit#carrotTicks
	 * carrotTicks} has reached zero
	 */
	private boolean isCarrotEaten() {
		return this.carrotTicks == 0;
	}

	protected void createEatingParticles() {
		BlockCarrot blockcarrot = (BlockCarrot) Blocks.CARROTS;
		IBlockState iblockstate = blockcarrot.withAge(blockcarrot.getMaxAge());
		this.world.spawnParticle(EnumParticleTypes.BLOCK_DUST,
				this.posX + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width,
				this.posY + 0.5D + (double) (this.rand.nextFloat() * this.height),
				this.posZ + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, 0.0D, 0.0D,
				0.0D, Block.getStateId(iblockstate));
		this.carrotTicks = 40;
	}

	/**
	 * Handler for {@link World#setEntityState}
	 */
	@SideOnly(Side.CLIENT)
	public void handleStatusUpdate(byte id) {
		if (id == 1) {
			this.createRunningParticles();
			this.jumpDuration = 10;
			this.jumpTicks = 0;
		} else {
			super.handleStatusUpdate(id);
		}
	}

	static class AIEvilAttack extends EntityAIAttackMelee {
		public AIEvilAttack(EntitySkeletalRabbit rabbit) {
			super(rabbit, 1.4D, true);
		}

		protected double getAttackReachSqr(EntityLivingBase attackTarget) {
			return (double) (4.0F + attackTarget.width);
		}
	}

	static class AIPanic extends EntityAIPanic {
		private final EntitySkeletalRabbit rabbit;

		public AIPanic(EntitySkeletalRabbit rabbit, double speedIn) {
			super(rabbit, speedIn);
			this.rabbit = rabbit;
		}

		/**
		 * Keep ticking a continuous task that has already been started
		 */
		public void updateTask() {
			super.updateTask();
			this.rabbit.setMovementSpeed(this.speed);
		}
	}

	static class AIRaidFarm extends EntityAIMoveToBlock {
		private final EntitySkeletalRabbit rabbit;
		private boolean wantsToRaid;
		private boolean canRaid;

		public AIRaidFarm(EntitySkeletalRabbit rabbitIn) {
			super(rabbitIn, 0.699999988079071D, 16);
			this.rabbit = rabbitIn;
		}

		/**
		 * Returns whether the EntityAIBase should begin execution.
		 */
		public boolean shouldExecute() {
			if (this.runDelay <= 0) {
				if (!this.rabbit.world.getGameRules().getBoolean("mobGriefing")) {
					return false;
				}

				this.canRaid = false;
				this.wantsToRaid = this.rabbit.isCarrotEaten();
				this.wantsToRaid = true;
			}

			return super.shouldExecute();
		}

		/**
		 * Returns whether an in-progress EntityAIBase should continue executing
		 */
		public boolean shouldContinueExecuting() {
			return this.canRaid && super.shouldContinueExecuting();
		}

		/**
		 * Keep ticking a continuous task that has already been started
		 */
		public void updateTask() {
			super.updateTask();
			this.rabbit.getLookHelper().setLookPosition((double) this.destinationBlock.getX() + 0.5D,
					(double) (this.destinationBlock.getY() + 1), (double) this.destinationBlock.getZ() + 0.5D, 10.0F,
					(float) this.rabbit.getVerticalFaceSpeed());

			if (this.getIsAboveDestination()) {
				World world = this.rabbit.world;
				BlockPos blockpos = this.destinationBlock.up();
				IBlockState iblockstate = world.getBlockState(blockpos);
				Block block = iblockstate.getBlock();

				if (this.canRaid && block instanceof BlockCarrot) {
					Integer integer = (Integer) iblockstate.getValue(BlockCarrot.AGE);

					if (integer.intValue() == 0) {
						world.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 2);
						world.destroyBlock(blockpos, true);
					} else {
						world.setBlockState(blockpos,
								iblockstate.withProperty(BlockCarrot.AGE, Integer.valueOf(integer.intValue() - 1)), 2);
						world.playEvent(2001, blockpos, Block.getStateId(iblockstate));
					}

					this.rabbit.createEatingParticles();
				}

				this.canRaid = false;
				this.runDelay = 10;
			}
		}

		/**
		 * Return true to set given position as destination
		 */
		protected boolean shouldMoveTo(World worldIn, BlockPos pos) {
			Block block = worldIn.getBlockState(pos).getBlock();

			if (block == Blocks.FARMLAND && this.wantsToRaid && !this.canRaid) {
				pos = pos.up();
				IBlockState iblockstate = worldIn.getBlockState(pos);
				block = iblockstate.getBlock();

				if (block instanceof BlockCarrot && ((BlockCarrot) block).isMaxAge(iblockstate)) {
					this.canRaid = true;
					return true;
				}
			}

			return false;
		}
	}

	public class RabbitJumpHelper extends EntityJumpHelper {
		private final EntitySkeletalRabbit rabbit;
		private boolean canJump;

		public RabbitJumpHelper(EntitySkeletalRabbit rabbit) {
			super(rabbit);
			this.rabbit = rabbit;
		}

		public boolean getIsJumping() {
			return this.isJumping;
		}

		public boolean canJump() {
			return this.canJump;
		}

		public void setCanJump(boolean canJumpIn) {
			this.canJump = canJumpIn;
		}

		/**
		 * Called to actually make the entity jump if isJumping is true.
		 */
		public void doJump() {
			if (this.isJumping) {
				this.rabbit.startJumping();
				this.isJumping = false;
			}
		}
	}

	static class RabbitMoveHelper extends EntityMoveHelper {
		private final EntitySkeletalRabbit rabbit;
		private double nextJumpSpeed;

		public RabbitMoveHelper(EntitySkeletalRabbit rabbit) {
			super(rabbit);
			this.rabbit = rabbit;
		}

		public void onUpdateMoveHelper() {
			if (this.rabbit.onGround && !this.rabbit.isJumping
					&& !((EntitySkeletalRabbit.RabbitJumpHelper) this.rabbit.jumpHelper).getIsJumping()) {
				this.rabbit.setMovementSpeed(0.0D);
			} else if (this.isUpdating()) {
				this.rabbit.setMovementSpeed(this.nextJumpSpeed);
			}

			super.onUpdateMoveHelper();
		}

		/**
		 * Sets the speed and location to move to
		 */
		public void setMoveTo(double x, double y, double z, double speedIn) {
			if (this.rabbit.isInWater()) {
				speedIn = 1.5D;
			}

			super.setMoveTo(x, y, z, speedIn);

			if (speedIn > 0.0D) {
				this.nextJumpSpeed = speedIn;
			}
		}
	}

	
}
