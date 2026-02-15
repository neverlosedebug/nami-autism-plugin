package autismdevelopment.maybelilim.autismplugin.client;

import autismdevelopment.maybelilim.autismplugin.client.impl.feature.ExampleFeature;
import autismdevelopment.maybelilim.autismplugin.client.impl.hudfeature.GreetingFeature;
import autismdevelopment.maybelilim.autismplugin.client.impl.hudfeature.HelloHudFeature;
import autismdevelopment.maybelilim.autismplugin.client.impl.hudfeature.WatermarkPluginFeature;
import namidevelopment.kiriyaga.api.model.plugin.Plugin;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;

import static namidevelopment.kiriyaga.api.NamiApi.*;

public class AutismPluginClient implements ClientModInitializer {

    public static final String VERSION;
    public static final String PLUGIN_NAME = "autismplugin";
    public static final String AUTHORS = "maybelilim";
    public static final int PLUGIN_ID = 20260215;

    static {
        ModContainer mod = FabricLoader.getInstance().getModContainer("nami-autism-plugin").orElse(null);
        if (mod != null) {
            VERSION = mod.getMetadata().getVersion().getFriendlyString();
        } else {
            VERSION = "dev-environment";
        }
    }

    @Override
    public void onInitializeClient() {
        Plugin plugin = new Plugin(PLUGIN_ID, PLUGIN_NAME, VERSION, AUTHORS);

        // FEATURES
        plugin.getRegisteredFeatures().add(new ExampleFeature());

        // HUD FEATURES
        plugin.getRegisteredFeatures().add(new GreetingFeature());
        plugin.getRegisteredFeatures().add(new HelloHudFeature());
        plugin.getRegisteredFeatures().add(new WatermarkPluginFeature());

        // COMMANDS

        // You need to register plugin here
        PLUGIN_SERVICE.registerPlugin(plugin);

        // You can force enabling your client from start, config will still disable it if its not first launch
        // Please keep it clear, dont touch some core api functionality you dont know about, dont force people to load anything they cant disable
        //PLUGIN_SERVICE.enablePlugin(PLUGIN_ID);

        LOGGER.info(NAME + " " + VERSION + " has been initialized\n");

        ClientLifecycleEvents.CLIENT_STARTED.register(client -> {
            // add here everything you need to do after resource loaded
        });

        ClientLifecycleEvents.CLIENT_STOPPING.register(client -> {
            // add here everything you need to do before client shutdown
        });
    }
}