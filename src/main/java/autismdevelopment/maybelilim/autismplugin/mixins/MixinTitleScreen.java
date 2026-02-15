package autismdevelopment.maybelilim.autismplugin.mixins;

import autismdevelopment.maybelilim.autismplugin.client.AutismPluginClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.Component;
import namidevelopment.kiriyaga.api.NamiApi;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class MixinTitleScreen {

    @Inject(method = "render", at = @At("RETURN"))
    private void renderWatermark(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {
        Minecraft mc = Minecraft.getInstance();

        String name = AutismPluginClient.PLUGIN_NAME;
        String version = AutismPluginClient.VERSION;

        String formatted = "{red}" + name + "{/}{gray} - {/}{white}" + version + "{/}";
        Component textComponent = NamiApi.CAT_FORMAT.format(formatted);

        int x = 2;
        int y = 2;

        guiGraphics.drawString(mc.font, textComponent, x, y, -1, true);
    }
}