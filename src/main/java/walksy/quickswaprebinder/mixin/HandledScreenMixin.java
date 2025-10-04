package walksy.quickswaprebinder.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import net.minecraft.client.gui.Click;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import walksy.quickswaprebinder.RebindQuickSwapMod;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.InputUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;


@Mixin(HandledScreen.class)
public abstract class HandledScreenMixin {

    @Redirect(
            method = "mouseClicked",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/Click;hasShift()Z"
            )
    )
    private boolean redirect1(Click instance) {
        return RebindQuickSwapMod.shouldQuickSwap();
    }

    @Redirect(
            method = "mouseReleased",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/Click;hasShift()Z"
            )
    )
    private boolean redirect2(Click instance) {
        return RebindQuickSwapMod.shouldQuickSwap();
    }

}
