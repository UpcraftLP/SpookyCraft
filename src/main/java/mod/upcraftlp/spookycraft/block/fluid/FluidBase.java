package mod.upcraftlp.spookycraft.block.fluid;

import com.google.common.collect.Lists;
import mod.upcraftlp.spookycraft.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import java.awt.*;
import java.util.List;

/**
 * (c)2017 UpcraftLP
 */
public class FluidBase extends Fluid {

    public static List<FluidBase> fluids = Lists.newArrayList();
    public static List<BlockFluidBase> fluidBlocks = Lists.newArrayList();

    private final int color;
    private final boolean isHot;

    /**
     * Constructor is {@code private}, use {@link FluidBase#create(String, int, boolean)} instead!
     */
    private FluidBase(String name, int color, boolean isHot) {
        super(name, new ResourceLocation(Reference.MODID, "blocks/fluidbase_still"), new ResourceLocation(Reference.MODID, "blocks/fluidbase_flow"));
        this.color = color;
        this.isHot = isHot;
        if(isHot) {
            this.setLuminosity(15);
            this.setDensity(2000);
            this.setViscosity(2000);
            this.setTemperature(600);
        }
        this.setUnlocalizedName(Reference.MODID + "." + name);
    }

    public boolean isHot() {
        return this.isHot;
    }

    @Override
    public int getColor() {
        return this.color;
    }

    public static Fluid create(String name, byte red, byte green, byte blue, boolean isHot) {
        return create(name, new Color(red, green, blue).getRGB(), isHot);
    }

    public static Fluid create(String name, int color, boolean isHot) {
        FluidBase fluid = new FluidBase(name, new Color(color).getRGB(), isHot);
        boolean useOwnFluid = FluidRegistry.registerFluid(fluid);
        if(useOwnFluid) fluids.add(fluid);
        return useOwnFluid ? fluid : FluidRegistry.getFluid(name);
    }
}
