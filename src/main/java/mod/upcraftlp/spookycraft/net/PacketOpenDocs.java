package mod.upcraftlp.spookycraft.net;

import io.netty.buffer.ByteBuf;
import mod.upcraftlp.spookycraft.client.gui.GuiEncyclopedia;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author UpcraftLP
 */
public class PacketOpenDocs implements IMessage, IMessageHandler<PacketOpenDocs, IMessage> {

    public ResourceLocation page = new ResourceLocation("missingno"); //See TextureMap.LOCATION_MISSING_TEXTURE

    public PacketOpenDocs(ResourceLocation page) {
        if(page != null) this.page = page;
    }

    @SuppressWarnings("unused")
    public PacketOpenDocs() {
        //NO-OP, needed for the network registry
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.page = new ResourceLocation(ByteBufUtils.readUTF8String(buf));
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, this.page.toString());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IMessage onMessage(PacketOpenDocs message, MessageContext ctx) {
        Minecraft.getMinecraft().addScheduledTask(() -> Minecraft.getMinecraft().displayGuiScreen(new GuiEncyclopedia(message.page)));
        return null;
    }
}
