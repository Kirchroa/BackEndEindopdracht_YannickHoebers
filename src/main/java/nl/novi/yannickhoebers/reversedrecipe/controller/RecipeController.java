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
}