package mod.upcraftlp.spookycraft.init;

import mod.upcraftlp.spookycraft.Reference;
import mod.upcraftlp.spookycraft.block.BlockHauntedPumpkin;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * @author UpcraftLP
 */
@GameRegistry.ObjectHolder(Reference.MODID)
public class SpookyBlocks {

    public static final Block HAUNTED_PUMPKIN = new BlockHauntedPumpkin();
}
