package mod.upcraftlp.spookycraft.init;

import core.upcraftlp.craftdev.api.util.ModHelper;
import mod.upcraftlp.spookycraft.Reference;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.lang.reflect.Field;
import java.util.Locale;

/**
 * @author UpcraftLP
 */
@GameRegistry.ObjectHolder(Reference.MODID)
public class SpookySounds {

    //sounds are created by initializing a field for them
    public static final SoundEvent SCARECROW_HURT = null;
    public static final SoundEvent SCARECROW_AMBIENT = null;
    public static final SoundEvent SCARECROW_DEATH = null;

	@Mod.EventBusSubscriber
	private static class Registry {

        @SubscribeEvent
        public static void onRegisterSounds(RegistryEvent.Register<SoundEvent> event) {
            for(Field f : SpookySounds.class.getFields()) {
                register(f.getName().toLowerCase(Locale.ROOT));
            }
        }


		private static SoundEvent register(String soundName) {
			return ModHelper.registerSound(soundName, Reference.MODID);
		}

	}
}
