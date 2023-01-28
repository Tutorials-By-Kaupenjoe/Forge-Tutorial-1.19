package net.kaupenjoe.tutorialmod.integration;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.recipe.GemInfusingStationRecipe;
import net.kaupenjoe.tutorialmod.screen.GemInfusingStationScreen;
import net.kaupenjoe.tutorialmod.ModBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;

import java.util.List;
import java.util.Objects;

@JeiPlugin
public class JEITutorialModPlugin implements IModPlugin {
    public static RecipeType<GemInfusingStationRecipe> INFUSION_TYPE =
            new RecipeType<>(GemInfusingStationRecipeCategory.UID, GemInfusingStationRecipe.class);

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(TutorialMod.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new
                GemInfusingStationRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();

        List<GemInfusingStationRecipe> recipesInfusing = rm.getAllRecipesFor(GemInfusingStationRecipe.Type.INSTANCE);
        registration.addRecipes(INFUSION_TYPE, recipesInfusing);
    }
    //////allows to rightclick the block in JEI for all Recipes of this Block
    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration){
    	var geminfusingstation = new ItemStack(ModBlocks.GEM_INFUSING_STATION.get());
    	registration.addRecipeCatalyst(geminfusingstation, GemInfusingStationRecipeCategory.RECIPE_TYPE);
    	
    }
    //////Adds a clickable Show Recipes Area to your menu. to adjust the location its on you Kaupen ^^
    //////My Menu is diff for locations and size.
    @Override
	public void registerGuiHandlers(IGuiHandlerRegistration registration)
	{
      registration.addRecipeClickArea(GemInfusingStationScreen.class, 2, 2, 70, 31, JEITutorialModPlugin.INFUSION_TYPE);
	    
	}
}
