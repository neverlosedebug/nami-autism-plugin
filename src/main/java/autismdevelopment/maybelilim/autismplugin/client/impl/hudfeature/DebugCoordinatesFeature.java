package autismdevelopment.maybelilim.autismplugin.client.impl.hudfeature;

import autismdevelopment.maybelilim.autismplugin.client.AutismPluginClient;
import autismdevelopment.maybelilim.autismplugin.client.impl.feature.ExampleFeature;
import namidevelopment.kiriyaga.api.model.feature.HudElementFeature;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;

import static namidevelopment.kiriyaga.api.NamiApi.*;

public class DebugCoordinatesFeature extends HudElementFeature {

    // this only works if you have specified: -debug on and ExampleFeature is enabled too
    public DebugCoordinatesFeature() {
        super("DebugCoordinates", "Shows player coordinates in debug mode.", 0, 0, 150, 12);
    }

    @Override
    public Component getDisplayText() {
        ExampleFeature exampleFeature = null;
        var plugin = PLUGIN_SERVICE.getPlugin(AutismPluginClient.PLUGIN_ID);
        if (plugin != null) {
            for (var f : plugin.getRegisteredFeatures()) {
                if (f instanceof ExampleFeature) {
                    exampleFeature = (ExampleFeature) f;
                    break;
                }
            }
        }

        if (exampleFeature == null || !exampleFeature.debug.get()) {
            return Component.empty();
        }

        if (MC.player == null || MC.level == null) {
            return Component.empty();
        }

        double x = MC.player.getX();
        double y = MC.player.getY();
        double z = MC.player.getZ();

        String dimName = "Unknown";
        var dim = MC.level.dimension();
        if (dim == Level.OVERWORLD) {
            dimName = "Overworld";
        } else if (dim == Level.NETHER) {
            dimName = "Nether";
        } else if (dim == Level.END) {
            dimName = "End";
        }

        String coords = String.format("%.1f, %.1f, %.1f", x, y, z).replace(',', '.');

        String formatted = "{red}[DEBUG]{/} Player now at: {white}" + coords + " {gray}(" + dimName + "){/}";

        width = FONT_SERVICE.getWidth(formatted.replaceAll("\\{.*?\\}", ""));
        height = FONT_SERVICE.getHeight();

        return CAT_FORMAT.format(formatted);
    }
}