package mod.upcraftlp.spookycraft.handler;

import com.google.common.collect.Lists;
import core.upcraftlp.craftdev.api.util.Utils;
import mod.upcraftlp.spookycraft.Reference;
import mod.upcraftlp.spookycraft.entity.monster.EntityScareCrow;
import mod.upcraftlp.spookycraft.util.EntityUtils;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
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
            EntityScareCrow.class
            //TODO different vanilla mobs?
            //TODO custom mobs
    );

    public static final List<ItemStack> ALLOWED_HATS = Lists.newArrayList(
            new ItemStack(Blocks.PUMPKIN)
    );
    static {
        for(int i = 0; i <= 5; i++) {
            if(i == 3) continue; //no player heads allowed
            ALLOWED_HATS.add(new ItemStack(Blocks.SKULL, 1, i));
        }
    }

    @SubscribeEvent
    public static void onPlayerUpdate(TickEvent.PlayerTickEvent event) {
        EntityPlayer player = event.player;
        if(player.isCreative()) return;
        World world = player.world;
        ItemStack stack = player.inventory.armorInventory.get(3);


        if(!world.isRemote && isNightTime(world)) {
            boolean hasHat = false;
            for(int i = 0; !hasHat && i < ALLOWED_HATS.size(); i++) {
                hasHat = ALLOWED_HATS.get(i).isItemEqual(stack);
            }
            if(!hasHat && world.getTotalWorldTime() % (170) == 0 && world.rand.nextDouble() < 0.1D) {
                EntityUtils.summonEntitiesAroundPos(Utils.getRandomElementFromList(SPAWN_LIST), world, player.getPosition(), 40, 4, 9, false);
            }
        }
    }

    public static boolean isNightTime(World world) {
        long time = world.getWorldTime();
        return time > 13120 && time < 22100;
    }
}
