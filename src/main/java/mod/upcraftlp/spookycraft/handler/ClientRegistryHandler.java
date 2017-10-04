package mod.upcraftlp.spookycraft.handler;

import mod.upcraftlp.spookycraft.Reference;
import mod.upcraftlp.spookycraft.block.fluid.BlockFluidBase;
import mod.upcraftlp.spookycraft.block.fluid.FluidBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author UpcraftLP
 */
@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(modid = Reference.MODID, value = {Side.CLIENT})
public class ClientRegistryHandler {

    private static final ResourceLocation FLUID_LOCATION = new ResourceLocation(Reference.MODID,"fluids");

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onRegisterModels(ModelRegistryEvent event) {
        for(BlockFluidBase fluidBlock : FluidBase.fluidBlocks) {
            ModelLoader.setCustomStateMapper(fluidBlock, new StateMapperBase() {
                @Override
                public ModelResourceLocation getModelResourceLocation(IBlockState state) {
                    return new ModelResourceLocation(FLUID_LOCATION, fluidBlock.getFluid().getName());
                }
            });
        }
    }
}
