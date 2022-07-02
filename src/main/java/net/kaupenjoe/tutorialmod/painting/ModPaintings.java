package net.kaupenjoe.tutorialmod.painting;

import net.kaupenjoe.tutorialmod.TutorialMod;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModPaintings {
    public static final DeferredRegister<PaintingVariant> PAINTING_VARIANTS =
            DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, TutorialMod.MOD_ID);

    public static final RegistryObject<PaintingVariant> PLANT = PAINTING_VARIANTS.register("plant",
            () -> new PaintingVariant(16, 16));
    public static final RegistryObject<PaintingVariant> WANDERER = PAINTING_VARIANTS.register("wanderer",
            () -> new PaintingVariant(16, 32));
    public static final RegistryObject<PaintingVariant> SUNSET = PAINTING_VARIANTS.register("sunset",
            () -> new PaintingVariant(32, 16));

    public static void register(IEventBus eventBus) {
        PAINTING_VARIANTS.register(eventBus);
    }
}
