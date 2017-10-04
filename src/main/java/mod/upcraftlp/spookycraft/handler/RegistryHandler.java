package mod.upcraftlp.spookycraft.handler;

import core.upcraftlp.craftdev.api.util.RegistryUtils;
import mod.upcraftlp.spookycraft.Reference;
import mod.upcraftlp.spookycraft.block.fluid.BlockFluidBase;
import mod.upcraftlp.spookycraft.block.fluid.FluidBase;
import mod.upcraftlp.spookycraft.init.SpookyBlocks;
import mod.upcraftlp.spookycraft.init.SpookyFluids;
import mod.upcraftlp.spookycraft.init.SpookyItems;
import mod.upcraftlp.spookycraft.init.SpookyTabs;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * @author UpcraftLP
 */
@Mod.EventBusSubscriber(modid = Reference.MODID)
public class RegistryHandler {

    @SubscribeEvent
    public static void onRegisterBlocks(RegistryEvent.Register<Block> event) {
        RegistryUtils.createRegistryEntries(Block.class, event, SpookyBlocks.class, Reference.MODID, SpookyTabs.SPOOKY_TAB);
        event.getRegistry().register(SpookyBlocks.Special.HAUNTED_PUMPKIN);
        FluidRegistry.addBucketForFluid(SpookyFluids.BONE_MILK);
        for(FluidBase fluid : FluidBase.fluids) { //only register those fluids that haven't already been created by other mods
            BlockFluidBase fluidBlock = new BlockFluidBase(fluid);
            event.getRegistry().register(fluidBlock);
            FluidBase.fluidBlocks.add(fluidBlock);
        }
    }

    @SubscribeEvent
    public static void onRegisterItems(RegistryEvent.Register<Item> event) {
        RegistryUtils.createRegistryEntries(Item.class, event, SpookyItems.class, Reference.MODID, SpookyTabs.SPOOKY_TAB);
    }

}
