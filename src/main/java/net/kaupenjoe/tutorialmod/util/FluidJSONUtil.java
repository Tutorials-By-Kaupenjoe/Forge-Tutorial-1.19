package net.kaupenjoe.tutorialmod.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.serialization.JsonOps;
import net.minecraftforge.fluids.FluidStack;

public class FluidJSONUtil {
    public static FluidStack readFluid(JsonObject json) {
        return FluidStack.CODEC.decode(JsonOps.INSTANCE, json).result().orElseThrow().getFirst();
    }

    public static JsonElement toJson(FluidStack stack) {
        return FluidStack.CODEC.encodeStart(JsonOps.INSTANCE, stack).result().orElseThrow();
    }
}
