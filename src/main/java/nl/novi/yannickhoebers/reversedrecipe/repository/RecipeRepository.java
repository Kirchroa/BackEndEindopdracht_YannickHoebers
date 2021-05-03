package nl.novi.yannickhoebers.reversedrecipe.repository;

import java.util.List;

import nl.novi.yannickhoebers.reversedrecipe.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findByCookId(Long cookId);
}

