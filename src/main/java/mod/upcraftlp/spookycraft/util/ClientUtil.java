package mod.upcraftlp.spookycraft.util;

import mod.upcraftlp.spookycraft.Main;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.net.URI;

/**
 * @author UpcraftLP
 */
@SideOnly(Side.CLIENT)
public class ClientUtil { //TODO move to library

    @SuppressWarnings("unchecked")
    public static void openLink(String url) {
        try {
            URI uri = new URI(url);
            Class oclass = Class.forName("java.awt.Desktop");
            boolean flag = (Boolean) oclass.getMethod("isDesktopSupported").invoke(null);
            if (!flag) throw new Exception("no desktop supported!");
            Object object = oclass.getMethod("getDesktop").invoke(null);
            oclass.getMethod("browse", URI.class).invoke(object, uri);
        } catch (Throwable throwable1) {
            Throwable throwable = throwable1.getCause();
            Main.getLogger().error("Couldn\'t open link: {}", new Object[]{throwable == null ? (url != null && !url.isEmpty()) ? url : "<UNKNOWN>" : throwable.getMessage()});
        }
    }
}
