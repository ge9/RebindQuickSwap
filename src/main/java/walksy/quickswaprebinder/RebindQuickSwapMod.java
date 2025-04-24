package walksy.quickswaprebinder;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import walksy.quickswaprebinder.mixin.KeybindingAccessor;

public class RebindQuickSwapMod implements ModInitializer {

    public static KeyBinding keyBinding = KeyBindingHelper.registerKeyBinding(
            new KeyBinding("Quick Swap Keybind", GLFW.GLFW_KEY_LEFT_SHIFT, "Walksy's Quick Swap Rebind"));

    @Override
    public void onInitialize() {

    }

    public static boolean shouldQuickSwap()
    {
        int code = InputUtil.fromTranslationKey(keyBinding.getBoundKeyTranslationKey()).getCode();
        boolean bl2 = ((KeybindingAccessor)keyBinding).getKey().getCategory() == InputUtil.Type.MOUSE;
        boolean rtrn = (bl2 ? isMouseButtonPressed(MinecraftClient.getInstance().getWindow().getHandle(), code)
                : isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), code));

        return rtrn;
    }

    private static boolean isKeyPressed(long handle, int code) {
        return GLFW.glfwGetKey(handle, code) == 1;
    }

    private static boolean isMouseButtonPressed(long handle, int code) {
        return GLFW.glfwGetMouseButton(handle, code) == 1;
    }
}
