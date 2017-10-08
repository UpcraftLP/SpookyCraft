package mod.upcraftlp.spookycraft.handler;

import com.google.common.collect.Lists;
import mod.upcraftlp.spookycraft.Reference;
import mod.upcraftlp.spookycraft.init.SpookyItems;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * @author UpcraftLP
 */
@Mod.EventBusSubscriber
public class RecipeUnlockHandler {

    public static void init() {
        chocolate_bar_recipe = CraftingManager.getRecipe(new ResourceLocation(Reference.MODID, "chocolate_bar"));
        encyclopedia_recipe = CraftingManager.getRecipe(new ResourceLocation(Reference.MODID, "encyclopedia"));
    }

    private static IRecipe chocolate_bar_recipe;
    private static IRecipe encyclopedia_recipe;

    @SubscribeEvent
    public static void onPickupItem(EntityItemPickupEvent event) {
        if(event.getEntityPlayer() instanceof EntityPlayerMP) {
            EntityPlayerMP player = (EntityPlayerMP) event.getEntityPlayer();
            ItemStack stack = event.getItem().getItem();
            if(!player.getRecipeBook().isUnlocked(chocolate_bar_recipe)) {
                if(stack.getItem() == Items.DYE && EnumDyeColor.byDyeDamage(stack.getMetadata()) == EnumDyeColor.BROWN) player.unlockRecipes(Lists.newArrayList(chocolate_bar_recipe));
            }
            if(!player.getRecipeBook().isUnlocked(encyclopedia_recipe)) {
                if(stack.getItem() == SpookyItems.CANDY_CORN) player.unlockRecipes(Lists.newArrayList(encyclopedia_recipe));
            }
        }
    }
}
