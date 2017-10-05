package mod.upcraftlp.spookycraft.entity.monster;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILlamaFollowCaravan;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAIRunAroundLikeCrazy;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLlamaSpit;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntitySkeletalLlama extends EntitySkeletal implements IRangedAttackMob{
	private static final DataParameter<Integer> DATA_STRENGTH_ID = EntityDataManager.<Integer>createKey(EntitySkeletalLlama.class, DataSerializers.VARINT);
    private boolean didSpit;
    @Nullable
    private EntitySkeletalLlama caravanHead;
    @Nullable
    private EntitySkeletalLlama caravanTail;

    public EntitySkeletalLlama(World worldIn)
    {
        super(worldIn);
        this.setSize(0.9F, 1.87F);
    }

    private void setStrength(int strengthIn)
    {
        this.dataManager.set(DATA_STRENGTH_ID, Integer.valueOf(Math.max(1, Math.min(5, strengthIn))));
    }

    private void setRandomStrength()
    {
        int i = this.rand.nextFloat() < 0.04F ? 5 : 3;
        this.setStrength(1 + this.rand.nextInt(i));
    }

    public int getStrength()
    {
        return ((Integer)this.dataManager.get(DATA_STRENGTH_ID)).intValue();
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setInteger("Strength", this.getStrength());

    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        this.setStrength(compound.getInteger("Strength"));
        super.readEntityFromNBT(compound);
       
    }
@Override
    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        
        this.tasks.addTask(3, new EntityAIAttackRanged(this, 1.25D, 40, 20.0F));
        this.tasks.addTask(3, new EntityAIPanic(this, 1.2D));
      
        this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 0.7D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntitySkeletalLlama.AIHurtByTarget(this));
        this.targetTasks.addTask(2, new EntitySkeletalLlama.AIDefendTarget(this));
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(40.0D);
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(DATA_STRENGTH_ID, Integer.valueOf(0));
       
    }

   

    /**
     * Returns the Y offset from the entity's position for any entity riding this one.
     */
    public double getMountedYOffset()
    {
        return (double)this.height * 0.67D;
    }

    /**
     * returns true if all the conditions for steering the entity are met. For pigs, this is true if it is being ridden
     * by a player and the player is holding a carrot-on-a-stick
     */
    public boolean canBeSteered()
    {
        return false;
    }

   
    /**
     * Called only once on an entity when first time spawned, via egg, mob spawner, natural spawning etc, but not called
     * when entity is reloaded from nbt. Mainly used for initializing attributes and inventory
     */
    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
        livingdata = super.onInitialSpawn(difficulty, livingdata);
        this.setRandomStrength();
        int i;

        
        return livingdata;
    }

   

    protected SoundEvent getAngrySound()
    {
        return SoundEvents.ENTITY_LLAMA_ANGRY;
    }

    protected SoundEvent getAmbientSound()
    {
        return SoundEvents.ENTITY_LLAMA_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundEvents.ENTITY_LLAMA_HURT;
    }

    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_LLAMA_DEATH;
    }

    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        this.playSound(SoundEvents.ENTITY_LLAMA_STEP, 0.15F, 1.0F);
    }

    

    public void makeMad()
    {
        SoundEvent soundevent = this.getAngrySound();

        if (soundevent != null)
        {
            this.playSound(soundevent, this.getSoundVolume(), this.getSoundPitch());
        }
    }

   

    public int getInventoryColumns()
    {
        return this.getStrength();
    }

   
    public int getMaxTemper()
    {
        return 30;
    }

  

    private void spit(EntityLivingBase target)
    {
        EntityLlamaSpit entityllamaspit = new EntityLlamaSpit(this.world);
        double d0 = target.posX - this.posX;
        double d1 = target.getEntityBoundingBox().minY + (double)(target.height / 3.0F) - entityllamaspit.posY;
        double d2 = target.posZ - this.posZ;
        float f = MathHelper.sqrt(d0 * d0 + d2 * d2) * 0.2F;
        entityllamaspit.shoot(d0, d1 + (double)f, d2, 1.5F, 10.0F);
        this.world.playSound((EntityPlayer)null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_LLAMA_SPIT, this.getSoundCategory(), 1.0F, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F);
        this.world.spawnEntity(entityllamaspit);
        this.didSpit = true;
    }

    private void setDidSpit(boolean didSpitIn)
    {
        this.didSpit = didSpitIn;
    }

    public void fall(float distance, float damageMultiplier)
    {
        int i = MathHelper.ceil((distance * 0.5F - 3.0F) * damageMultiplier);

        if (i > 0)
        {
            if (distance >= 6.0F)
            {
                this.attackEntityFrom(DamageSource.FALL, (float)i);

                if (this.isBeingRidden())
                {
                    for (Entity entity : this.getRecursivePassengers())
                    {
                        entity.attackEntityFrom(DamageSource.FALL, (float)i);
                    }
                }
            }

            IBlockState iblockstate = this.world.getBlockState(new BlockPos(this.posX, this.posY - 0.2D - (double)this.prevRotationYaw, this.posZ));
            Block block = iblockstate.getBlock();

            if (iblockstate.getMaterial() != Material.AIR && !this.isSilent())
            {
                SoundType soundtype = block.getSoundType();
                this.world.playSound((EntityPlayer)null, this.posX, this.posY, this.posZ, soundtype.getStepSound(), this.getSoundCategory(), soundtype.getVolume() * 0.5F, soundtype.getPitch() * 0.75F);
            }
        }
    }

    public void leaveCaravan()
    {
        if (this.caravanHead != null)
        {
            this.caravanHead.caravanTail = null;
        }

        this.caravanHead = null;
    }

    public void joinCaravan(EntitySkeletalLlama caravanHeadIn)
    {
        this.caravanHead = caravanHeadIn;
        this.caravanHead.caravanTail = this;
    }

    public boolean hasCaravanTrail()
    {
        return this.caravanTail != null;
    }

    public boolean inCaravan()
    {
        return this.caravanHead != null;
    }

   

    /**
     * Attack the specified entity using a ranged attack.
     */
    public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor)
    {
        this.spit(target);
    }

    public void setSwingingArms(boolean swingingArms)
    {
    }

    static class AIDefendTarget extends EntityAINearestAttackableTarget<EntityWolf>
        {
            public AIDefendTarget(EntitySkeletalLlama llama)
            {
                super(llama, EntityWolf.class, 16, false, true, (Predicate)null);
            }

            /**
             * Returns whether the EntityAIBase should begin execution.
             */
            public boolean shouldExecute()
            {
                if (super.shouldExecute() && this.targetEntity != null && !((EntityWolf)this.targetEntity).isTamed())
                {
                    return true;
                }
                else
                {
                    this.taskOwner.setAttackTarget((EntityLivingBase)null);
                    return false;
                }
            }

            protected double getTargetDistance()
            {
                return super.getTargetDistance() * 0.25D;
            }
        }

    static class AIHurtByTarget extends EntityAIHurtByTarget
        {
            public AIHurtByTarget(EntitySkeletalLlama llama)
            {
                super(llama, false);
            }

            /**
             * Returns whether an in-progress EntityAIBase should continue executing
             */
            public boolean shouldContinueExecuting()
            {
                if (this.taskOwner instanceof EntitySkeletalLlama)
                {
                    EntitySkeletalLlama entityllama = (EntitySkeletalLlama)this.taskOwner;

                    if (entityllama.didSpit)
                    {
                        entityllama.setDidSpit(false);
                        return false;
                    }
                }

                return super.shouldContinueExecuting();
            }
        }

    static class GroupData implements IEntityLivingData
        {
            public int variant;

            private GroupData(int variantIn)
            {
                this.variant = variantIn;
            }
        }
}
