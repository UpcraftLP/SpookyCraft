package mod.upcraftlp.spookycraft;

import com.google.common.collect.Lists;
import net.minecraft.util.text.TextFormatting;

import java.time.Year;
import java.util.List;

public class Reference {

    //VERSION
    public static final String MCVERSIONS = "[1.12, 1.13)";
    public static final String VERSION = "@VERSION@";

    //MISC INFORMATION
    public static final List<String> authors = Lists.newArrayList(
            "UpcraftLP",
            "EPIIC_THUNDERCAT",
            "Subaraki"
    );
    public static final String MOD_DESCRIPTION = "Happy Halloween 2017";
    public static final String CREDITS = TextFormatting.GOLD + "(C)" + "2017-" + Year.now().getValue() + " UpcraftLP, EPIIC_THUNDERCAT";

    //META Information
    public static final String MODNAME = "SpookyCraft";
    public static final String MODID = "spookycraft";
    public static final String DEPENDENCIES = "required-after:forge@[14.23.0.2489,);required-after:craftdevcore@[2.2.6,)";
    public static final String UPDATE_JSON = "https://gist.githubusercontent.com/UpcraftLP/c59fa5fa48bf4b424c519936b2d9e037/raw";
    public static final String WEBSITE = "https://minecraft.curseforge.com/projects/spookycraft";

    public static final String CLIENTPROXY = "mod.upcraftlp.spookycraft.proxy.ClientProxy";
    public static final String SERVERPROXY = "mod.upcraftlp.spookycraft.proxy.ServerProxy";
    public static final String FINGERPRINT_KEY = "@FINGERPRINT@";
}
