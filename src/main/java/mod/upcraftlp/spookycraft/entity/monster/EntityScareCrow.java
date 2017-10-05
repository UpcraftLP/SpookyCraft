package mod.upcraftlp.spookycraft.entity.monster;

import mod.upcraftlp.spookycraft.Reference;
import mod.upcraftlp.spookycraft.handler.SoundHandler;
import net.minecraft.block.Block;
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
		this.tasks.addTask(4, new EntityAIAttackMelee(this, 3.0, false));
		this.tasks.addTask(5, new EntityAIWanderAvoidWater(this, 0.8D));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(6, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
		this.targetTasks.addTask(2, new EntityScareCrow.AIScarecrowTarget(this, EntityPlayer.class));
		this.targetTasks.addTask(3, new EntityScareCrow.AIScarecrowTarget(this, EntityIronGolem.class));
	}

	static class AIScarecrowTarget<T extends EntityLivingBase> extends EntityAINearestAttackableTarget<T> {
		public AIScarecrowTarget(EntityScareCrow crow, Class<T> classTarget) {
			super(crow, classTarget, true);
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
		return SoundHandler.scarecrow_ambient.sound();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundHandler.scarecrow_hurt.sound();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundHandler.scarecrow_death.sound();
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn) {
		this.playSound(SoundEvents.ENTITY_COW_STEP, 0.15F, 1.0F);
	}
	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);
		if (!this.world.isRemote) {
		this.entityDropItem(new ItemStack(Items.WHEAT, rand.nextInt(8)), 0.0F);
		}
		
	}
}
