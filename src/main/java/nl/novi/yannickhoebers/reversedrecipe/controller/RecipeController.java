package nl.novi.yannickhoebers.reversedrecipe.controller;

import nl.novi.yannickhoebers.reversedrecipe.model.Recipe;
import nl.novi.yannickhoebers.reversedrecipe.repository.CookRepository;
import nl.novi.yannickhoebers.reversedrecipe.repository.RecipeRepository;
import nl.novi.yannickhoebers.reversedrecipe.service.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000/")
public class RecipeController {
    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private CookRepository cookRepository;

    @GetMapping("/cooks/{cookId}/recipes")
    public List<Recipe> getContactByCookId(@PathVariable Long cookId) {

        if(!cookRepository.existsById(cookId)) {
            throw new NotFoundException("Cook not found!");
        }

        return recipeRepository.findByCookId(cookId);
    }

    @PostMapping("/cooks/{cookId}/recipes")
    public Recipe addRecipe(@PathVariable Long cookId,
                            @Valid @RequestBody Recipe recipe) {
        return cookRepository.findById(cookId)
                .map(cook -> {
                    recipe.setCook(cook);
                    return recipeRepository.save(recipe);
                }).orElseThrow(() -> new NotFoundException("Cook not found!"));
    }

    @PutMapping("/cooks/{cookId}/recipes/{recipeId}")
    public Recipe updateRecipe(@PathVariable Long cookId,
                               @PathVariable Long recipeId,
                               @Valid @RequestBody Recipe recipeUpdated) {

        if(!cookRepository.existsById(cookId)) {
            throw new NotFoundException("Cook not found!");
        }

        return recipeRepository.findById(recipeId)
                .map(recipe -> {
                    recipe.setRecipeName(recipeUpdated.getRecipeName());
                    recipe.setPortion(recipeUpdated.getPortion());
                    recipe.setMeat(recipeUpdated.getMeat());
                    recipe.setVegetable(recipeUpdated.getVegetable());
                    recipe.setOther(recipeUpdated.getOther());
                    recipe.setInstructions(recipeUpdated.getInstructions());
                    return recipeRepository.save(recipe);
                }).orElseThrow(() -> new NotFoundException("Recipe not found!"));
    }

    @DeleteMapping("/cooks/{cookId}/recipes/{recipeId}")
    public String deleteRecipe(@PathVariable Long cookId,
                               @PathVariable Long recipeId) {

        if(!cookRepository.existsById(cookId)) {
            throw new NotFoundException("Cook not found!");
        }

        return recipeRepository.findById(recipeId)
                .map(recipe -> {
                    recipeRepository.delete(recipe);
                    return "Deleted Successfully!";
                }).orElseThrow(() -> new NotFoundException("Recipe not found!"));
    }


}