package mod.upcraftlp.spookycraft.proxy;

import core.upcraftlp.craftdev.api.net.NetworkHandler;
import core.upcraftlp.craftdev.api.util.UpdateChecker;
import mod.upcraftlp.spookycraft.ModConfig;
import mod.upcraftlp.spookycraft.Reference;
import mod.upcraftlp.spookycraft.block.fluid.FluidBase;
import mod.upcraftlp.spookycraft.entity.ModEntities;
import mod.upcraftlp.spookycraft.net.PacketOpenDocs;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.relauncher.Side;

/**
 * @author UpcraftLP
 */
public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        NetworkHandler.registerPacket(PacketOpenDocs.class, PacketOpenDocs.class, Side.CLIENT);
        if(ModConfig.enableUpdateNotification) UpdateChecker.registerMod(Reference.MODID);
    }

    public void init(FMLInitializationEvent event) {
    	ModEntities.init();
    }

    public void postInit(FMLPostInitializationEvent event) {

        //cleanup
        FluidBase.fluidBlocks = null;
        FluidBase.fluids = null;
    }

    public void serverStarting(FMLServerStartingEvent event) {

    }
}
