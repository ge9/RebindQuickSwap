package walksy.quickswaprebinder;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;
import walksy.quickswaprebinder.mixin.KeybindingAccessor;

public class RebindQuickSwapMod implements ModInitializer {
    public static KeyBinding.Category keybindCat = KeyBinding.Category.create(Identifier.of("quickswaprebinder"));
    public static KeyBinding keyBinding = KeyBindingHelper.registerKeyBinding(
            new KeyBinding("Quick Swap Keybind", GLFW.GLFW_KEY_LEFT_SHIFT, keybindCat));
    public static KeyBinding keyBinding2 = KeyBindingHelper.registerKeyBinding(
            new KeyBinding("Quick Swap Keybind 2", GLFW.GLFW_KEY_LEFT_SHIFT, keybindCat));

    @Override
    public void onInitialize() {

    }

    public static boolean shouldQuickSwap()
    {
        int code = InputUtil.fromTranslationKey(keyBinding.getBoundKeyTranslationKey()).getCode();
        boolean bl2 = ((KeybindingAccessor)keyBinding).getKey().getCategory() == InputUtil.Type.MOUSE;
        boolean rtrn = (bl2 ? isMouseButtonPressed(MinecraftClient.getInstance().getWindow().getHandle(), code)
                : isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), code));
        int code2 = InputUtil.fromTranslationKey(keyBinding2.getBoundKeyTranslationKey()).getCode();
        boolean bl22 = ((KeybindingAccessor)keyBinding2).getKey().getCategory() == InputUtil.Type.MOUSE;
        boolean rtrn2 = (bl22 ? isMouseButtonPressed(MinecraftClient.getInstance().getWindow().getHandle(), code2)
                : isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), code2));

        return rtrn || rtrn2;
    }

    private static boolean isKeyPressed(long handle, int code) {
        return GLFW.glfwGetKey(handle, code) == 1;
    }

    private static boolean isMouseButtonPressed(long handle, int code) {
        return GLFW.glfwGetMouseButton(handle, code) == 1;
    }
}
