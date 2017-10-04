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
    public static final Item GLASS_STRING = new core.upcraftlp.craftdev.api.item.Item("glass_string");
    public static final Item CANDY = new core.upcraftlp.craftdev.api.item.Item("candy");
    public static final Item CHOCOLATE_BAR = new core.upcraftlp.craftdev.api.item.Item("chocolate_bar");
}
