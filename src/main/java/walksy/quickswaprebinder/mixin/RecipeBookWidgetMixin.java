package walksy.quickswaprebinder.mixin;

import net.minecraft.client.gui.screen.recipebook.RecipeBookWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import walksy.quickswaprebinder.RebindQuickSwapMod;

@Mixin(RecipeBookWidget.class)
public abstract class RecipeBookWidgetMixin {
    @ModifyVariable(
            method = "select",
            at = @At("HEAD"),
            index = 3,
            argsOnly = true)
    private boolean redirect(boolean bl) {
        return RebindQuickSwapMod.shouldQuickSwap();
    }
}
