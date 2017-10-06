package mod.upcraftlp.spookycraft.entity.monster;

import mod.upcraftlp.spookycraft.Reference;
import mod.upcraftlp.spookycraft.init.SpookySounds;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityScareCrow extends EntityMob {

	public EntityScareCrow(World worldIn) {
		super(worldIn);
		this.setSize(0.6F, 1.99F);
	}

	public static final ResourceLocation LOOT_CROW = new ResourceLocation(Reference.MODID, "entities/scarecrow");

	@Override
	protected void initEntityAI() {
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
		this.tasks.addTask(4, new EntityAIAttackMelee(this, 1.2D, false));
		this.tasks.addTask(5, new EntityAIWanderAvoidWater(this, 0.8D));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(6, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(2, new EntityScareCrow.AIScarecrowTarget<>(this, EntityPlayer.class));
		this.targetTasks.addTask(3, new EntityScareCrow.AIScarecrowTarget<>(this, EntityIronGolem.class));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();

	}

	static class AIScarecrowTarget<T extends EntityLivingBase> extends EntityAINearestAttackableTarget<T> {
		public AIScarecrowTarget(EntityScareCrow crow, Class<T> classTarget) {
			super(crow, classTarget, true);
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
		return SpookySounds.SCARECROW_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SpookySounds.SCARECROW_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SpookySounds.SCARECROW_DEATH;
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn) {
		this.playSound(SoundEvents.ENTITY_COW_STEP, 0.15F, 1.0F);
	}

	@Override
	public void onDeath(DamageSource cause) {
		if (!this.world.isRemote) {
			this.entityDropItem(new ItemStack(Items.WHEAT, rand.nextInt(8)), 0.4F);
		}
		super.onDeath(cause);
	}
}
