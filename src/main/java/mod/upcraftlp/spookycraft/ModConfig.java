package mod.upcraftlp.spookycraft;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static mod.upcraftlp.spookycraft.Reference.*;

/**
 * @author UpcraftLP
 */
@Config(modid = MODID, name = "craftdevmods/" + MODNAME) //--> /config/craftdevmods/SpookyCraft.cfg
@Config.LangKey("config." + MODID + ".title")
public class ModConfig {

    @Config.Comment("false to disable the update notification")
    public static boolean enableUpdateNotification = true;

    @Config.Comment("en/disable the need of wearing a pumpkin")
    public static boolean pumpkinHats = true;

    @Config.Comment("en/disable spooky sounds on block break")
    public static boolean soundsOnBlockBreak = true;

    @Config.RequiresMcRestart
    @Config.Comment("Configure the spawn chances for each mob individually. The higher the values, the higher the chance of spawning each mob. 0 to disable.")
    public static Spawns mobSpawns = new Spawns();

    public static class Spawns {

        public int scareCrow = 5;

        public int bat = 20;
        public int chicken = 90;
        public int cow = 80;
        public int parrot = 40;
        public int pig = 60;
        public int wolf = 45;
        public int rabbit = 35;
        public int ocelot = 25;
        public int sheep = 65;
        public int llama = 50;

    }

    @Mod.EventBusSubscriber
    public static class Handler {

        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
            if(event.getModID().equals(Reference.MODID)) {
                ConfigManager.sync(Reference.MODID, Config.Type.INSTANCE);
            }
        }
    }
    
}
