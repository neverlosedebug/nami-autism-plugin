package autismdevelopment.maybelilim.autismplugin.client.impl.hudfeature;

import autismdevelopment.maybelilim.autismplugin.client.AutismPluginClient;
import namidevelopment.kiriyaga.api.model.feature.HudElementFeature;
import net.minecraft.network.chat.Component;
import static namidevelopment.kiriyaga.api.NamiApi.*;

public class WatermarkPluginFeature extends HudElementFeature {

    public WatermarkPluginFeature() {
        super("WatermarkPlugin", "Displays plugin watermark in the top-left corner.", 2, 2, 100, 9);

    }

    @Override
    public Component getDisplayText() {
        if (MC.player == null) {
            return Component.empty();
        }

        String name = AutismPluginClient.PLUGIN_NAME;
        String version = AutismPluginClient.VERSION;

        String formatted = "{red}" + name + "{/}{gray} - {/}{white}" + version + "{/}";

        width = FONT_SERVICE.getWidth(name + " - " + version);
        height = FONT_SERVICE.getHeight();

        return CAT_FORMAT.format(formatted);
    }
}