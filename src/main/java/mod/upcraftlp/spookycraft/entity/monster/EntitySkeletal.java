package mod.upcraftlp.spookycraft.entity.monster;

import mod.upcraftlp.spookycraft.Reference;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntitySkeletal extends EntityCreature {

	public static final ResourceLocation LOOT_SKELETAL = new ResourceLocation(Reference.MODID, "entities/skeletal");

	public EntitySkeletal(World worldIn) {
		super(worldIn);

	}

	@Override
	protected void initEntityAI() {
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
		this.tasks.addTask(4, new EntityAIAttackMelee(this, 3.0, false));
		this.tasks.addTask(5, new EntityAIWanderAvoidWater(this, 0.8D));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(6, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
		this.targetTasks.addTask(2, new EntitySkeletal.AIskeletalTarget(this, EntityPlayer.class));
		this.targetTasks.addTask(3, new EntitySkeletal.AIskeletalTarget(this, EntityIronGolem.class));
	}

	static class AIskeletalTarget<T extends EntityLivingBase> extends EntityAINearestAttackableTarget<T> {
		public AIskeletalTarget(EntitySkeletal skeletal, Class<T> classTarget) {
			super(skeletal, classTarget, true);
		}

		/**
		 * Returns whether the EntityAIBase should begin execution.
		 */
		public boolean shouldExecute() {
			float f = this.taskOwner.getBrightness();
			return f >= 0.5F ? false : super.shouldExecute();
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
}
