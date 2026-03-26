package walksy.quickswaprebinder.mixin;

import net.minecraft.client.input.MouseButtonEvent;
import org.spongepowered.asm.mixin.injection.Redirect;
import walksy.quickswaprebinder.RebindQuickSwapMod;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AbstractContainerScreen.class)
public abstract class AbstractContainerScreenMixin {

    @Redirect(
            method = "mouseClicked",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/input/MouseButtonEvent;hasShiftDown()Z"
            )
    )
    private boolean redirect1(MouseButtonEvent event) {
        return RebindQuickSwapMod.shouldQuickSwap();
    }

    @Redirect(
            method = "mouseReleased",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/input/MouseButtonEvent;hasShiftDown()Z"
            )
    )
    private boolean redirect2(MouseButtonEvent event) {
        return RebindQuickSwapMod.shouldQuickSwap();
    }

}
