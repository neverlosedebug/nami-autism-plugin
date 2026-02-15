package autismdevelopment.maybelilim.autismplugin.client.impl.hudfeature;

import autismdevelopment.maybelilim.autismplugin.client.AutismPluginClient;
import autismdevelopment.maybelilim.autismplugin.client.impl.feature.ExampleFeature;
import namidevelopment.kiriyaga.api.model.feature.HudElementFeature;
import net.minecraft.network.chat.Component;

import static namidevelopment.kiriyaga.api.NamiApi.*;

public class StatusIndicatorFeature extends HudElementFeature {

    public StatusIndicatorFeature() {
        super("StatusIndicator", "Shows plugin status with colored prefix.", 0, 0, 180, 12);
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

        String modeText;
        if (exampleFeature != null && exampleFeature.debug.get()) {
            modeText = "{gray}[{/}{red}DEBUG{/}{gray}]{/}";
        } else {
            modeText = "{gray}[{/}{red}ENABLE{/}{gray}]{/}";
        }

        String formatted = "{red}NAMI{/}{white}: AUTISMPLUGIN:{/} " + modeText;

        width = FONT_SERVICE.getWidth(formatted.replaceAll("\\{.*?\\}", ""));
        height = FONT_SERVICE.getHeight();

        return CAT_FORMAT.format(formatted);
    }
}