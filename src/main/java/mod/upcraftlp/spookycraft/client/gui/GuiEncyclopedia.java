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
import net.minecraft.util.text.TextFormatting;
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
    private static final int entryWidth = 121;
    private static ResourceLocation last = INDEX_PAGE;
    private ResourceLocation currentPage;

    private final List<Entry> entries = Lists.newArrayList();
    private String title;
    private boolean hasText;
    private ResourceLocation image;
    private int[] imageData = new int[2];

    private static ResourceLocation getPageFor(String page) {
        return new ResourceLocation(Reference.MODID, page);
    }

    private NBTTagCompound pageNBT;

    public GuiEncyclopedia(@Nullable ResourceLocation page) {
        if(page == null || page.equals(TextureMap.LOCATION_MISSING_TEXTURE)) { //TODO open last page, only open index if it's the first time opening the book.
            page = INDEX_PAGE;
        }
        this.pageNBT = EncyclopediaReader.readJsonToNbt(page);
        currentPage = page;
    }

    @Override
    public void onGuiClosed() {
        last = this.currentPage;
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
        else {
            this.hasText = pageNBT.getBoolean("text");
            if(pageNBT.hasKey("image", Constants.NBT.TAG_STRING)) {
                this.image = ClientUtil.loadTexture(new ResourceLocation(pageNBT.getString("image")), imageData);
            }
        }
        if(this.pageNBT.getBoolean("displayTitle")) {
            this.title = I18n.format("page." + this.pageNBT.getString("name") + ".title");
        }
        this.addButton(new GuiButton(0, (this.width + pageWidth) / 2 - 50, (this.height + pageHeight) / 2 - 30, 70, 20, ""));
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if(button.id == 0) {
            if(this.currentPage == INDEX_PAGE) mc.displayGuiScreen(null); //close screen
            else if(GuiScreen.isShiftKeyDown() || last == null) mc.displayGuiScreen(new GuiEncyclopedia(INDEX_PAGE));
            else mc.displayGuiScreen(new GuiEncyclopedia(last));
        }
        super.actionPerformed(button);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.buttonList.get(0).displayString = this.currentPage == INDEX_PAGE ? TextFormatting.RED + I18n.format("button.close.name") : isShiftKeyDown() || last == null ? TextFormatting.ITALIC.toString() + TextFormatting.AQUA.toString() + I18n.format("button.index.name") : I18n.format("button.back.name");
        mc.getTextureManager().bindTexture(BOOK_GUI_TEXTURES);
        int x = (this.width - pageWidth) / 2;
        int y = 2;
        this.drawTexturedModalRect(x, y, 0, 0, pageWidth, pageHeight); //background

        //TODO draw page by json definition
        drawCenteredString(mc.fontRenderer, this.title, this.width / 2, y + 15, Color.RED.getRGB());

        //(0,0) = top-left corner of page
        x += 32;
        y += 30;

        if(!this.entries.isEmpty()) {
            for(int index = 0; index < entries.size(); index++) {
                Entry entry = entries.get(index);
                entry.drawEntry(index, x, y + index * (entryHeight + 1), entryWidth, entryHeight, mouseX, mouseY, false, mc.getRenderPartialTicks());
            }
            y += this.entries.size() * (entryHeight + 1) + 2;
        }
        if(this.image != null && this.image != TextureMap.LOCATION_MISSING_TEXTURE) {
            float width = this.pageNBT.getInteger("imageSize");
            if(width <= 0) width = entryWidth;
            float height = imageData[1] * (width / imageData[0]);
            GlStateManager.pushMatrix();
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            mc.renderEngine.bindTexture(this.image);
            drawScaledCustomSizeModalRect(x, y, 0, 0, imageData[0], imageData[1], (int) width, (int) height, imageData[0], imageData[1]);
            GlStateManager.popMatrix();
            y+= height + 2;
        }
        x += 1;
        if(this.hasText) {
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
        int x = (this.width - pageWidth) / 2 + 32;
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
