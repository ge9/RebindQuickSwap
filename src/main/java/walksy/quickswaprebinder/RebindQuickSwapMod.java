package walksy.quickswaprebinder;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.resources.ResourceLocation;
import org.lwjgl.glfw.GLFW;
import walksy.quickswaprebinder.mixin.KeybindingAccessor;

public class RebindQuickSwapMod implements ModInitializer {
    public static KeyMapping.Category keybindCat = KeyMapping.Category.register(ResourceLocation.parse("quickswaprebinder"));
    public static KeyMapping keyBinding = KeyBindingHelper.registerKeyBinding(
            new KeyMapping("Quick Swap Keybind", GLFW.GLFW_KEY_LEFT_SHIFT, keybindCat));
    public static KeyMapping keyBinding2 = KeyBindingHelper.registerKeyBinding(
            new KeyMapping("Quick Swap Keybind 2", GLFW.GLFW_KEY_LEFT_SHIFT, keybindCat));

    @Override
    public void onInitialize() {

    }

    public static boolean shouldQuickSwap()
    {
        int code = InputConstants.getKey(keyBinding.saveString()).getValue();
        boolean bl2 = ((KeybindingAccessor)keyBinding).getKey().getType() == InputConstants.Type.MOUSE;
        boolean rtrn = (bl2 ? isMouseButtonPressed(Minecraft.getInstance().getWindow().handle(), code)
                : isKeyPressed(Minecraft.getInstance().getWindow().handle(), code));
        int code2 = InputConstants.getKey(keyBinding2.saveString()).getValue();
        boolean bl22 = ((KeybindingAccessor)keyBinding2).getKey().getType() == InputConstants.Type.MOUSE;
        boolean rtrn2 = (bl22 ? isMouseButtonPressed(Minecraft.getInstance().getWindow().handle(), code2)
                : isKeyPressed(Minecraft.getInstance().getWindow().handle(), code2));

        return rtrn || rtrn2;
    }

    private static boolean isKeyPressed(long handle, int code) {
        return GLFW.glfwGetKey(handle, code) == 1;
    }

    private static boolean isMouseButtonPressed(long handle, int code) {
        return GLFW.glfwGetMouseButton(handle, code) == 1;
    }
}
