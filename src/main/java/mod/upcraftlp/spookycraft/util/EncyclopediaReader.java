package mod.upcraftlp.spookycraft.util;

import mod.upcraftlp.spookycraft.Main;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.util.Locale;
import java.util.Scanner;

/**
 * @author UpcraftLP
 */
@SideOnly(Side.CLIENT)
public class EncyclopediaReader { //FIXME move to library

    public static NBTTagCompound readJsonToNbt(ResourceLocation location) {
        if(location != null) {
            Scanner sc = null;
            try {
                String path = ("/assets/" + location.getResourceDomain() + "/pages/" + location.getResourcePath()).toLowerCase(Locale.ROOT);
                if(!path.endsWith(".json")) path += ".json";
                sc = new Scanner(new BufferedInputStream(MinecraftServer.class.getResourceAsStream(path)));
                StringBuilder builder = new StringBuilder();
                while(sc.hasNext()) builder.append(sc.next());
                return JsonToNBT.getTagFromJson(builder.toString());
            } catch (Exception e) {
                Main.getLogger().error("Exception reading " + location + ", defaulting to empty NBT compound!");

            }
            finally {
                IOUtils.closeQuietly(sc);
            }
        }
        return new NBTTagCompound();
    }
}
