package mod.upcraftlp.spookycraft.block.fluid;

/**
 * @author UpcraftLP
 */
public class FluidBoneMilk extends FluidBase {

    /**
     * Constructor is {@code private}, use {@link FluidBase#create(Class, String, int, boolean)} instead!
     */
    protected FluidBoneMilk(String name, int color, boolean isHot) {
        super(name, color, isHot);
    }

    @Override
    public BlockFluidBase createBlock() {
        return new BlockFluidBoneMilk(this);
    }

}
