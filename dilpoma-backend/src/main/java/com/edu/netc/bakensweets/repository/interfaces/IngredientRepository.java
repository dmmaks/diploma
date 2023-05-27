package com.edu.netc.bakensweets.repository.interfaces;

import com.edu.netc.bakensweets.model.Ingredient;
import com.edu.netc.bakensweets.model.form.SearchIngredientModel;

import java.util.Collection;

public interface IngredientRepository extends BaseCrudRepository<Ingredient, Long> {
    Collection<Ingredient> findAll(SearchIngredientModel searchIngredientModel);
    int count(SearchIngredientModel searchIngredientModel);
    boolean updateStatus(Long id, boolean status);

}
