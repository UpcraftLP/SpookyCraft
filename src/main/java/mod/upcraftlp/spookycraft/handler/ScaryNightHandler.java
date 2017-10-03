package mod.upcraftlp.spookycraft.handler;

import mod.upcraftlp.spookycraft.Reference;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

/**
 * @author UpcraftLP
 */
@Mod.EventBusSubscriber(modid = Reference.MODID)
public class ScaryNightHandler {

    @SubscribeEvent
    public static void onPlayerUpdate(TickEvent.PlayerTickEvent event) {
        World world = event.player.world;
        ItemStack stack = event.player.inventory.armorInventory.get(3);
        if(!world.isRemote && isNightTime(world) && stack.getItem() == Item.getItemFromBlock(Blocks.PUMPKIN)) {
            if(world.getTotalWorldTime() % (20 *10) == 0) {
                //TODO spawn mob horde
            }
        }
    }

    public static boolean isNightTime(World world) {
        long time = world.getWorldTime();
        return time > 13120 && time < 22100;
    }
}
