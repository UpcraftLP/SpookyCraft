package mod.upcraftlp.spookycraft.init;

import core.upcraftlp.craftdev.api.util.ModHelper;
import mod.upcraftlp.spookycraft.Reference;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.commons.codec.language.Soundex;

/**
 * @author UpcraftLP
 */
@GameRegistry.ObjectHolder(Reference.MODID)
public class SpookySounds {

    public static final SoundEvent SCARECROW_HURT = null;
    public static final SoundEvent SCARECROW_AMBIENT = null;
    public static final SoundEvent SCARECROW_DEATH = null;

    @Mod.EventBusSubscriber
    private static class Registry {

        @SubscribeEvent
        public static void onRegisterSounds(RegistryEvent.Register<SoundEvent> event) { //register all sounds here
            register("scarecrow_hurt");
            register("scarecrow_ambient");
            register("scarecrow_death");
        }

        private static SoundEvent register(String soundName) {
            return ModHelper.registerSound(soundName, Reference.MODID);
        }
    }
}
