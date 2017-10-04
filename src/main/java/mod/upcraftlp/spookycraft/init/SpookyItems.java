package mod.upcraftlp.spookycraft.init;

import mod.upcraftlp.spookycraft.Reference;
import mod.upcraftlp.spookycraft.item.ItemEncyclopedia;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * @author UpcraftLP
 */
@GameRegistry.ObjectHolder(Reference.MODID)
public class SpookyItems {

    public static final Item ENCYCLOPEDIA = new ItemEncyclopedia();
}
