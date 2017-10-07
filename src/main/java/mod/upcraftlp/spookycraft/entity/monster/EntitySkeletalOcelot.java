package mod.upcraftlp.spookycraft.entity.monster;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIOcelotAttack;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntitySkeletalOcelot extends EntitySkeletal {

	public EntitySkeletalOcelot(World worldIn) {
		super(worldIn);
		this.setSize(0.6F, 0.7F);
	}

	@Override
	protected void initEntityAI() {
		super.initEntityAI();
		this.tasks.addTask(8, new EntityAIOcelotAttack(this));
	}

	public void updateAITasks() {
		if (this.getMoveHelper().isUpdating()) {
			double d0 = this.getMoveHelper().getSpeed();

			if (d0 == 0.6D) {
				this.setSneaking(true);
				this.setSprinting(false);
			} else if (d0 == 1.33D) {
				this.setSneaking(false);
				this.setSprinting(true);
			} else {
				this.setSneaking(false);
				this.setSprinting(false);
			}
		} else {
			this.setSneaking(false);
			this.setSprinting(false);
		}
	}



	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30000001192092896D);
	}

	public void fall(float distance, float damageMultiplier) {
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);

	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);

	}

	/**
	 * Returns the volume for the sounds this mob makes.
	 */
	protected float getSoundVolume() {
		return 0.4F;
	}

	public boolean attackEntityAsMob(Entity entityIn) {
		return entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 3.0F);
	}

	/**
	 * Called when the entity is attacked.
	 */
	public boolean attackEntityFrom(DamageSource source, float amount) {
		return this.isEntityInvulnerable(source) || super.attackEntityFrom(source, amount);
	}

	/**
	 * Checks if the entity's current position is a valid location to spawn this
	 * entity.
	 */
	public boolean getCanSpawnHere() {
		return this.world.rand.nextInt(3) != 0;
	}

	/**
	 * Checks that the entity is not colliding with any blocks / liquids
	 */
	public boolean isNotColliding() {
		if (this.world.checkNoEntityCollision(this.getEntityBoundingBox(), this)
				&& this.world.getCollisionBoxes(this, this.getEntityBoundingBox()).isEmpty()
				&& !this.world.containsAnyLiquid(this.getEntityBoundingBox())) {
			BlockPos blockpos = new BlockPos(this.posX, this.getEntityBoundingBox().minY, this.posZ);

			if (blockpos.getY() < this.world.getSeaLevel()) {
				return false;
			}

			IBlockState iblockstate = this.world.getBlockState(blockpos.down());
			Block block = iblockstate.getBlock();

			return block == Blocks.GRASS || block.isLeaves(iblockstate, this.world, blockpos.down());
		}

		return false;
	}
}
