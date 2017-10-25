package mod.upcraftlp.spookycraft.handler;

import com.google.common.collect.Lists;
import core.upcraftlp.craftdev.api.util.Utils;
import mod.upcraftlp.spookycraft.ModConfig;
import mod.upcraftlp.spookycraft.Reference;
import mod.upcraftlp.spookycraft.entity.monster.*;
import mod.upcraftlp.spookycraft.util.EntityUtils;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.List;

/**
 * @author UpcraftLP
 */
@Mod.EventBusSubscriber(modid = Reference.MODID)
public class ScaryNightHandler {

    private static final List<Class<? extends EntityLiving>> SPAWN_LIST = Lists.newArrayList(
            EntitySkeleton.class,
            EntityZombie.class,
            EntityEnderman.class,

            EntityScareCrow.class,

            EntitySkeletalLlama.class,
            EntitySkeletalOcelot.class,
            EntitySkeletalBat.class,
            EntitySkeletalChicken.class,
            EntitySkeletalCow.class,
            EntitySkeletalParrot.class,
            EntitySkeletalPig.class,
            EntitySkeletalRabbit.class,
            EntitySkeletalSheep.class,
            EntitySkeletalWolf.class
            //TODO different vanilla mobs?
            //TODO custom mobs
    );

    public static final List<ItemStack> ALLOWED_HATS = Lists.newArrayList(
            new ItemStack(Blocks.PUMPKIN)
    );
    static {
        for(int i = 0; i <= 5; i++) { //add mob heads as valid helmets
            if(i == 3) continue; //no player heads allowed
            ALLOWED_HATS.add(new ItemStack(Blocks.SKULL, 1, i));
        }
    }

    @SubscribeEvent
    public static void onPlayerUpdate(TickEvent.PlayerTickEvent event) {
        EntityPlayer player = event.player;
        if(!ModConfig.pumpkinHats || player.isCreative()) return;
        World world = player.world;
        ItemStack stack = player.inventory.armorInventory.get(3);


        if(!world.isRemote && isNightTime(world)) {
            boolean hasHat = false;
            for(int i = 0; !hasHat && i < ALLOWED_HATS.size(); i++) {
                hasHat = ALLOWED_HATS.get(i).isItemEqual(stack);
            }
            int difficultyLevel = world.getDifficulty().getDifficultyId();
            if(difficultyLevel > 0) {
                DifficultyInstance difficulty = world.getDifficultyForLocation(player.getPosition());
                float additionalDifficulty = difficulty.getClampedAdditionalDifficulty();
                if(!hasHat && world.getTotalWorldTime() % (int) (1500.0F / additionalDifficulty) / difficultyLevel == 0 && world.rand.nextDouble() < 0.1D * additionalDifficulty) {
                    EntityUtils.summonEntitiesAroundPos(Utils.getRandomElementFromList(SPAWN_LIST), world, player.getPosition(), 40, 4, 9, false);
                }
            }
        }
    }

    public static boolean isNightTime(World world) {
        long time = world.getWorldTime();
        return time > 13120 && time < 22100;
    }
}
