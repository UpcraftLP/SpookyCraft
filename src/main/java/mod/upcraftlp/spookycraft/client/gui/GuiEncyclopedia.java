package mod.upcraftlp.spookycraft.client.gui;

import com.google.common.collect.Lists;
import jline.internal.Nullable;
import mod.upcraftlp.spookycraft.Reference;
import mod.upcraftlp.spookycraft.util.ClientUtil;
import mod.upcraftlp.spookycraft.util.EncyclopediaReader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.I18n;
import net.minecraft.command.CommandBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraftforge.common.util.Constants;

import java.awt.*;
import java.io.IOException;
import java.util.List;

/**
 * @author UpcraftLP
 */
public class GuiEncyclopedia extends GuiScreen {

    private static final Minecraft mc = Minecraft.getMinecraft();
    private static final ResourceLocation BOOK_GUI_TEXTURES = new ResourceLocation("textures/gui/book.png");
    private static final ResourceLocation INDEX_PAGE = getPageFor("index");
    private static final int pageWidth = 192;
    private static final int pageHeight = 192;
    private static final int entryHeight = 18;
    private static final int entryWidth = 120;

    private final List<Entry> entries = Lists.newArrayList();
    private String title;
    private boolean hasText;

    private static ResourceLocation getPageFor(String page) {
        return new ResourceLocation(Reference.MODID, page);
    }

    private NBTTagCompound pageNBT;

    public GuiEncyclopedia(@Nullable ResourceLocation page) {
        if(page == null || page.equals(TextureMap.LOCATION_MISSING_TEXTURE)) { //TODO open last page, only open index if it's the first time opening the book.
            page = INDEX_PAGE;
        }
        this.pageNBT = EncyclopediaReader.readJsonToNbt(page);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void initGui() {
        this.entries.clear();
        if(this.pageNBT.hasKey("elements", Constants.NBT.TAG_LIST)) {
            NBTTagList page = this.pageNBT.getTagList("elements", Constants.NBT.TAG_COMPOUND);
            for(int index = 0; index < page.tagCount(); index++) {
                entries.add(new Entry(page.getCompoundTagAt(index)));
            }
        }
        else this.hasText = pageNBT.getBoolean("text");
        if(this.pageNBT.getBoolean("displayTitle")) {
            this.title = I18n.format("page." + this.pageNBT.getString("name") + ".title");
        }
        this.addButton(new GuiButton(0, (this.width + pageWidth) / 2 - 50, (this.height + pageHeight) / 2 - 30, 70, 20, I18n.format("button.back.name")));
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if(button.id == 0) {
            mc.displayGuiScreen(new GuiEncyclopedia(INDEX_PAGE));
            return;
        }
        super.actionPerformed(button);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        mc.getTextureManager().bindTexture(BOOK_GUI_TEXTURES);
        int x = (this.width - pageWidth) / 2;
        int y = 2;
        this.drawTexturedModalRect(x, y, 0, 0, pageWidth, pageHeight); //background

        //TODO draw page by json definition
        drawCenteredString(mc.fontRenderer, this.title, this.width / 2, y + 15, Color.RED.getRGB());

        //(0,0) = top-left corner of page
        x += 33;
        y += 30;

        if(!this.entries.isEmpty()) {
            for(int index = 0; index < entries.size(); index++) {
                Entry entry = entries.get(index);
                entry.drawEntry(index, x, y + index * (entryHeight + 1), entryWidth, entryHeight, mouseX, mouseY, false, mc.getRenderPartialTicks());
            }
        }
        else if(this.hasText) {
            fontRenderer.drawSplitString(I18n.format("page." + this.pageNBT.getString("name") + ".text"), x, y, entryWidth, Color.BLACK.getRGB());
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
        for(int index = 0; index < entries.size(); index++) {
            Entry entry = entries.get(index);
            if(entry.hoverText != null && entry.isMouseOver(x, y + index * (entryHeight + 1), entryWidth, entryHeight, mouseX, mouseY)) {
                drawHoveringText(entry.hoverText, mouseX, mouseY);
                break;
            }
        }
    }

    @Override
    public void drawDefaultBackground() {
        super.drawDefaultBackground();
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        int x = (this.width - pageWidth) / 2 + 33;
        int y = 32;
        for (int i = 0; i < this.entries.size(); i++) {
            Entry e = this.entries.get(i);
            if(e.isMouseOver(x, y + entryHeight * i, entryWidth, entryHeight, mouseX, mouseY)) {
                e.mousePressed(i, mouseX, mouseY, mouseButton, mouseX, mouseY);
                break;
            }
        }
    }

    private static class Entry implements GuiListExtended.IGuiListEntry {

        private String caption;
        private ClickEvent clickEvent;
        private final String hoverText;
        private final ItemStack icon;

        Entry(NBTTagCompound entryNBT) {
            String elementBaseText = "entry." + entryNBT.getString("name");
            caption = I18n.format( elementBaseText + ".caption");
            if(entryNBT.hasKey("link")) {
                this.clickEvent = new ClickEvent(entryNBT.getBoolean("isLink") ? ClickEvent.Action.OPEN_URL : ClickEvent.Action.CHANGE_PAGE, entryNBT.getString("link"));
            }
            this.hoverText = entryNBT.getBoolean("hover") ? I18n.format(elementBaseText + ".hover") : null;
            if(entryNBT.hasKey("icon")) {
                ItemStack stack = null;
                try {
                    Item item = CommandBase.getItemByText(null, entryNBT.getString("icon"));
                    stack = new ItemStack(item);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                icon = stack != null ? stack : ItemStack.EMPTY;
            }
            else icon = ItemStack.EMPTY;
        }

        boolean isMouseOver(int x, int y, int width, int height, int mouseX, int mouseY) {
            return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
        }

        @Override
        public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected, float partialTicks) {
            drawRect(x, y - 1, x + listWidth, y, Color.WHITE.getRGB());
            drawRect(x, y, x + listWidth, y + slotHeight, this.isMouseOver(x, y, listWidth, slotHeight, mouseX, mouseY) ? Color.BLACK.getRGB() : Color.GRAY.getRGB());
            mc.fontRenderer.drawString(caption, x + 18, y + (slotHeight - mc.fontRenderer.FONT_HEIGHT) / 2, Color.WHITE.getRGB());
            if(!this.icon.isEmpty()) {
                GlStateManager.pushMatrix();
                RenderHelper.enableGUIStandardItemLighting();
                mc.getRenderItem().renderItemAndEffectIntoGUI(this.icon, x, y + 1);
                GlStateManager.popMatrix();
            }
        }

        @Override
        public boolean mousePressed(int slotIndex, int mouseX, int mouseY, int mouseEvent, int relativeX, int relativeY) {
            mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
            if(this.clickEvent.getAction() == ClickEvent.Action.OPEN_URL) ClientUtil.openLink(this.clickEvent.getValue());
            else mc.displayGuiScreen(new GuiEncyclopedia(new ResourceLocation(this.clickEvent.getValue())));
            return true;
        }

        @Override
        public void updatePosition(int slotIndex, int x, int y, float partialTicks) {
            //NO-OP
        }

        @Override
        public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY) {
            //NO-OP
        }

    }

}
