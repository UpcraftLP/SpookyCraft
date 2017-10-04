package mod.upcraftlp.spookycraft.util;

import com.google.common.collect.Lists;
import mod.upcraftlp.spookycraft.Main;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Random;

/**
 * @author UpcraftLP
 */
public class EntityUtils {

    private static final Random random = new Random();
    private static final IAttribute SPAWN_REINFORCEMENTS_CHANCE = (new RangedAttribute((IAttribute)null, "zombie.spawnReinforcements", 0.0D, 0.0D, 1.0D)).setDescription("Spawn Reinforcements Chance");

    /**
     * summon a horde of a specific {@link EntityLiving}
     * @param entityClass the entity class to be summoned
     * @param world a world.
     * @param sourcePos the position to look around for possible spawn locations
     * @param radius the max radius to look for spawn positions
     * @param minAmount
     * @param maxAmount
     * @param ignoreLightValue
     * @param <T>
     * @return
     */
    public static <T extends EntityLiving> List<T> summonEntitiesAroundPos(Class<T> entityClass, World world, BlockPos sourcePos, int radius, int minAmount, int maxAmount, boolean ignoreLightValue) {
        List<T> entityList = Lists.newArrayList();
        try {
            Constructor<T> c = entityClass.getConstructor(World.class);
            int minRadius = 2 + (int) (radius * 0.1F);
            if(minRadius >= radius) minRadius = (int) (radius * 0.1F);
            for(int count = 0; count < MathHelper.getInt(random, minAmount, maxAmount); count++) {
                T entity = c.newInstance(world);
                for(int l = 0; l < 50; ++l) {
                    int i1 = sourcePos.getX() + MathHelper.getInt(random, minRadius, radius) * MathHelper.getInt(random, -1, 1);
                    int j1 = sourcePos.getY() + MathHelper.getInt(random, minRadius, radius) * MathHelper.getInt(random, -1, 1);
                    int k1 = sourcePos.getZ() + MathHelper.getInt(random, minRadius, radius) * MathHelper.getInt(random, -1, 1);
                    if(world.getBlockState(new BlockPos(i1, j1 - 1, k1)).isSideSolid(world, new BlockPos(i1, j1 - 1, k1), net.minecraft.util.EnumFacing.UP) && (ignoreLightValue || world.getLightFromNeighbors(new BlockPos(i1, j1, k1)) < 10)) {
                        entity.setPosition(i1, j1, k1);
                        if(!world.isAnyPlayerWithinRangeAt(i1, j1, k1, 7.0D) && world.checkNoEntityCollision(entity.getEntityBoundingBox(), entity) && world.getCollisionBoxes(entity, entity.getEntityBoundingBox()).isEmpty() && !world.containsAnyLiquid(entity.getEntityBoundingBox())) {
                            world.spawnEntity(entity);
                            entity.onInitialSpawn(world.getDifficultyForLocation(entity.getPosition()), (IEntityLivingData)null);
                            entity.getEntityAttribute(SPAWN_REINFORCEMENTS_CHANCE).applyModifier(new AttributeModifier("Zombie reinforcement callee charge", -0.05000000074505806D, 0));
                            entityList.add(entity);
                            break;
                        }
                    }
                }
            }
        } catch (NoSuchMethodException e) {
            Main.getLogger().warn("No constructor with parameter <WORLD> found for " + entityClass.getSimpleName() + ", ignoring spawn!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entityList;
    }
}
