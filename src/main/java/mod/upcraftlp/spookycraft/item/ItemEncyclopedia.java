package mod.upcraftlp.spookycraft.item;

import core.upcraftlp.craftdev.api.item.Item;
import core.upcraftlp.craftdev.api.net.NetworkHandler;
import mod.upcraftlp.spookycraft.net.PacketOpenDocs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

/**
 * @author UpcraftLP
 */
public class ItemEncyclopedia extends Item {

    public ItemEncyclopedia() {
        super("encyclopedia");
        this.setMaxStackSize(1);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if(!worldIn.isRemote && playerIn instanceof EntityPlayerMP) NetworkHandler.INSTANCE.sendTo(new PacketOpenDocs(null), (EntityPlayerMP) playerIn); //TODO get current page from settings
        return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }
}
