package mod.upcraftlp.spookycraft;

import net.minecraftforge.common.config.Config;

import static mod.upcraftlp.spookycraft.Reference.*;

/**
 * @author UpcraftLP
 */
@Config(modid = MODID, name = MODNAME)
@Config.LangKey("config." + MODID + ".title")
public class ModConfig {

    @Config.Comment("false to disable the update notification")
    public static boolean enableUpdateNotification = true;

}
