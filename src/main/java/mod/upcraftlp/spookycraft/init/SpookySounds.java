package mod.upcraftlp.spookycraft.init;

import core.upcraftlp.craftdev.api.util.ModHelper;
import mod.upcraftlp.spookycraft.Reference;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * @author UpcraftLP
 */
@GameRegistry.ObjectHolder(Reference.MODID)
public class SpookySounds {

    public static final SoundEvent SCARECROW_HURT = register("scarecrow_hurt");
    public static final SoundEvent SCARECROW_AMBIENT = register("scarecrow_ambient");
    public static final SoundEvent SCARECROW_DEATH = register("scarecrow_death");

    private static SoundEvent register(String soundName) {
        return ModHelper.registerSound(soundName, Reference.MODID);
    }
}
