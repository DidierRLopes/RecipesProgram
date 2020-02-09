/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recipes;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Didier Lopes
 */
public class Category {
    String nameCategory;
    List<Recipe> recipesCategory = new ArrayList<>();
    
    Category (String _name, ArrayList<Recipe> _recipes){
        nameCategory = _name;
        recipesCategory = _recipes;
    }    
}
