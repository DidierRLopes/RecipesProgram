/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recipes;

/**
 *
 * @author Didier Lopes
 */
public class Recipe {
    String nameRecipe;
    String commentRecipe;
    String ingredientsRecipe;
    String preparationRecipe;

    Recipe (String _name, String _comment, String _ingredients, String _preparation){
        nameRecipe = _name;
        commentRecipe = _comment;
        ingredientsRecipe = _ingredients;
        preparationRecipe = _preparation;
    }

}
