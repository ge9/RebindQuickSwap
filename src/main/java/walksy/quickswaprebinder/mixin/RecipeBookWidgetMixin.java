package walksy.quickswaprebinder.mixin;

import net.minecraft.client.gui.screen.recipebook.RecipeBookWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import walksy.quickswaprebinder.RebindQuickSwapMod;

@Mixin(RecipeBookWidget.class)
public abstract class RecipeBookWidgetMixin {
    @Redirect(
            method = "select",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screen/Screen;hasShiftDown()Z"
            )
    )
    private boolean redirectRecipeClick() {
        return RebindQuickSwapMod.shouldQuickSwap();
    }
}
