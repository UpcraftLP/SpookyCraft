package mod.upcraftlp.spookycraft.handler;

import mod.upcraftlp.spookycraft.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

public enum SoundHandler {

	scarecrow_hurt, scarecrow_ambient, scarecrow_death;

	private final SoundEvent sounds;
	public static final RegisterManager REGISTRY_MANAGER = new RegisterManager();

	SoundHandler() {
		ResourceLocation soundLocation = new ResourceLocation(Reference.MODID, this.name());
		this.sounds = new SoundEvent(soundLocation);
		this.sounds.setRegistryName(soundLocation);
	}

	public SoundEvent sound() {
		return this.sounds;
	}

	public static final class RegisterManager {

		@SubscribeEvent
		public void onRegister(RegistryEvent.Register<SoundEvent> event) {
			IForgeRegistry<SoundEvent> register = event.getRegistry();
			for (SoundHandler sound : SoundHandler.values())
				register.register(sound.sounds);
		}

		private RegisterManager() {
		}

	}
}
