package supercoder79.ecotones.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.level.LevelGeneratorType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import supercoder79.ecotones.generation.WorldType;

@Mixin(LevelGeneratorType.class)
public class MixinLevelGeneratorTypeClient {
    @Shadow @Final private String name;

    @Inject(at = @At("HEAD"), method = "getTypeFromName", cancellable = true)
    private static void getTypeFromName(String name, CallbackInfoReturnable<LevelGeneratorType> info) {
        if (WorldType.STR_TO_WT_MAP.containsKey(name)) {
            info.setReturnValue(WorldType.STR_TO_WT_MAP.get(name).generatorType);
        }
    }

    //dirty hack but i can't be assed to figure out why the lang file won't work
    @Inject(method = "getTranslationKey", at = @At("HEAD"), cancellable = true)
    @Environment(EnvType.CLIENT)
    void getTranslationKey(CallbackInfoReturnable<Text> cir) {
        if (this.name.equals("ecotones")) cir.setReturnValue(new LiteralText(Formatting.DARK_GREEN + "Ecotones"));
    }
}