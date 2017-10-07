package mod.upcraftlp.spookycraft.init;

import mod.upcraftlp.spookycraft.Reference;
import mod.upcraftlp.spookycraft.item.ItemBottleBoneMilk;
import mod.upcraftlp.spookycraft.item.ItemEncyclopedia;
import mod.upcraftlp.spookycraft.item.ItemSpecialCandy;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * @author UpcraftLP
 */
@GameRegistry.ObjectHolder(Reference.MODID)
public class SpookyItems {

    public static final Item ENCYCLOPEDIA = new ItemEncyclopedia();
    public static final Item GLASS_STRING = new core.upcraftlp.craftdev.api.item.Item("glass_string");
    public static final Item CANDY_BAG = new ItemSpecialCandy("candy_bag", 10, 8.0f ,false); //only obtainable by striking a Skeleton pig with lightning, will give some nice buffs
    public static final Item CANDY_CORN = new core.upcraftlp.craftdev.api.item.ItemFood("candy_corn", 1, 2.0f ,false);
    public static final Item CHOCOLATE_BAR = new core.upcraftlp.craftdev.api.item.ItemFood("chocolate_bar", 4, 4.0f ,false);
    public static final Item BONE_MILK_BOTTLE = new ItemBottleBoneMilk();
}
