package mod.upcraftlp.spookycraft.init;

import mod.upcraftlp.spookycraft.block.fluid.FluidBase;
import mod.upcraftlp.spookycraft.block.fluid.FluidBoneMilk;
import net.minecraftforge.fluids.Fluid;

/**
 * @author UpcraftLP
 */
public class SpookyFluids {

    public static Fluid BONE_MILK;

    public static void init() {
        BONE_MILK = FluidBase.create(FluidBoneMilk.class, "bone_milk", 0xFFFFFFFF, false);
    }
}
