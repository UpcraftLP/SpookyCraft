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

    public static class Special { //FIXME workaround for craftdevcore issue, will be fixed in core v2.3.0
        public static final Block HAUNTED_PUMPKIN = new BlockHauntedPumpkin();
    }
}
