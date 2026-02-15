package autismdevelopment.maybelilim.autismplugin.client.impl.feature.commands;

import autismdevelopment.maybelilim.autismplugin.client.AutismPluginClient;
import autismdevelopment.maybelilim.autismplugin.client.impl.feature.ExampleFeature;
import namidevelopment.kiriyaga.api.model.command.Command;
import namidevelopment.kiriyaga.api.model.command.CommandRoute;

import static namidevelopment.kiriyaga.api.NamiApi.*;

public class DebugCommandFeature extends Command {

    public DebugCommandFeature() {
        super("debug");
    }

    @Override
    public CommandRoute[] getRoutes() {
        return new CommandRoute[]{
                new CommandRoute(null),
                new CommandRoute("on"),
                new CommandRoute("off")
        };
    }

    @Override
    public void execute(String routeLiteral, Object[] args) {
        ExampleFeature feature = null;
        var plugin = PLUGIN_SERVICE.getPlugin(AutismPluginClient.PLUGIN_ID);
        if (plugin != null) {
            for (var f : plugin.getRegisteredFeatures()) {
                if (f instanceof ExampleFeature) {
                    feature = (ExampleFeature) f;
                    break;
                }
            }
        }

        if (feature == null) {
            sendError("ExampleFeature not found!");
            return;
        }

        boolean newState;
        if ("on".equals(routeLiteral)) {
            newState = true;
        } else if ("off".equals(routeLiteral)) {
            newState = false;
        } else {

            newState = !feature.debug.get();
        }

        feature.debug.set(newState);

        String state = newState ? "{green}enabled" : "{red}disabled";
        sendSuccess("{gray}[{red}AutismPlugin{gray}] Debug mode {/}" + state + "{/}.");
    }

    private void sendSuccess(String msg) {
        CHAT_SERVICE.sendPersistent("DebugCommandFeature", CAT_FORMAT.format(msg));
    }

    private void sendError(String msg) {
        CHAT_SERVICE.sendPersistent("DebugCommandFeature", CAT_FORMAT.format("{red}[AutismPlugin] Error: {/}" + msg));
    }
}