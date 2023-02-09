package net.kaupenjoe.tutorialmod.recipe;

import net.kaupenjoe.tutorialmod.TutorialMod;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistry;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, TutorialMod.MOD_ID);

    public static final RegistryObject<RecipeSerializer<GemInfusingStationRecipe>> GEM_INFUSING_SERIALIZER =
            SERIALIZERS.register("gem_infusing", () -> GemInfusingStationRecipe.Serializer.INSTANCE);
    
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, TutorialMod.MOD_ID);

    public static final RegistryObject<RecipeType<GemInfusingStationRecipe>> GEM_INFUSING_TYPE =
            RECIPE_TYPES.register("gem_infusing", () -> GemInfusingStationRecipe.Type.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
        RECIPE_TYPES.register(eventBus);
    }
}
