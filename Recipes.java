/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recipes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Didier Lopes
 */
public class Recipes {
    static List<Category> ALL = new ArrayList<>();
    Category C;
    Recipe R;
    
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException  {
    }
     
    public static void loadAll() throws FileNotFoundException, IOException{
        
        File directory = new File("C:\\Recipes");
        
        if(!directory.exists())
            directory.mkdir();
        
        File[] files = new File("C:\\Recipes").listFiles();
        
        for (File file : files) {
            if (file.isFile() && file.toString().contains(".txt")) {
                int length = file.getName().length();
                String categoryName = file.getName().substring(0, length-4);
                
                ArrayList<Recipe> recipes;
                recipes = new ArrayList<>();
                
                Category C;
                C = new Category(categoryName, recipes);
                
                FileReader arq = new FileReader(file);
                try (BufferedReader readArq = new BufferedReader(arq)) {
                    String data;
                    String recipeName, recipeComment, recipeIngredients, recipePreparation;

                    while ((data = readArq.readLine()) != null) {
                        recipeName = data.substring(0, data.indexOf(';'));

                        recipeComment = readArq.readLine();
                        while (!recipeComment.contains(";")){
                            data = readArq.readLine();
                            recipeComment = recipeComment.concat('\n' + data);
                        }
                        recipeComment = recipeComment.substring(0, recipeComment.indexOf(';'));
                        
                        recipeIngredients = readArq.readLine();
                        while (!recipeIngredients.contains(";")){
                            data = readArq.readLine();
                            recipeIngredients = recipeIngredients.concat('\n' + data);
                        }
                        recipeIngredients = recipeIngredients.substring(0, recipeIngredients.indexOf(';'));
                        
                        recipePreparation = readArq.readLine();
                        while (!recipePreparation.contains(";")){
                            data = readArq.readLine();
                            recipePreparation = recipePreparation.concat('\n' + data);
                        }
                        recipePreparation = recipePreparation.substring(0, recipePreparation.indexOf(';'));

                        data = readArq.readLine();
                        
                        Recipe R = new Recipe(recipeName, recipeComment, recipeIngredients, recipePreparation);
                        C.recipesCategory.add(R);        
                    }
                    readArq.close();
                } catch(Exception E){
                
                }
                ALL.add(C);
            }
        }
    }
    
    public static ArrayList<String> listOfCategories(){
        ArrayList<String> categories = new ArrayList<>();

        for(Category cat : ALL){
            categories.add(cat.nameCategory);
        }   
        return categories;
    }

    public static ArrayList<String> listOfRecipes(int catIndice){
        ArrayList<String> recipes = new ArrayList<>();
        
        for(Recipe rep : ALL.get(catIndice).recipesCategory){
            recipes.add(rep.nameRecipe);
        }   
        return recipes;
    }
    
    /*8888888888888888888888888888888  MANAGE FILES 8888888888888888888888888888888*/
    
    public static void createCategoryFile(String recipeCategory){
        try {
            File file = new File ("C:\\Recipes\\" + recipeCategory + ".txt");
            file.createNewFile();
            
        } catch (IOException ex) {
            Logger.getLogger(Recipes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static boolean saveCategoryFile(int catIndice) throws IOException{
        String recipeCategory = ALL.get(catIndice).nameCategory;
        File file = new File ("C:\\Recipes\\" + recipeCategory + ".txt");
        
        try (PrintWriter pw = new PrintWriter(file)) {
            pw.write("");
            pw.flush();
        }
        
        Iterator<Recipe> iterateRecipes = ALL.get(catIndice).recipesCategory.iterator();

        FileWriter arq = new FileWriter(file);
        try (BufferedWriter writeArq = new BufferedWriter(arq)){  
        
        if(ALL.get(catIndice).recipesCategory.isEmpty()){
            return false;
        }    
        else{    
            while (iterateRecipes.hasNext()){

                Recipe Rep = iterateRecipes.next();

                String recipeName = Rep.nameRecipe;
                String recipeComment = Rep.commentRecipe;
                String recipeIngredients = Rep.ingredientsRecipe;
                String recipePreparation = Rep.preparationRecipe;

                writeArq.write(recipeName + ";");
                writeArq.newLine();
                writeArq.write(recipeComment + ";");
                writeArq.newLine();
                writeArq.write(recipeIngredients + ";");
                writeArq.newLine();
                writeArq.write(recipePreparation + ";");
                writeArq.newLine();
                writeArq.newLine();              
                }
            return true;
            }
        }
    }
    
    public static void saveRecipeFile(String recipeCategory, String recipeName, String recipeComment, String recipeIngredients, String recipePreparation) throws IOException{ 
        File file = new File ("C:\\Recipes\\" + recipeCategory + ".txt");
        
        FileWriter arq = new FileWriter(file, true);   
        try (BufferedWriter writeArq = new BufferedWriter(arq)) {
            writeArq.write(recipeName + ";");
            writeArq.newLine();
            writeArq.write(recipeComment + ";");
            writeArq.newLine();
            writeArq.write(recipeIngredients + ";");
            writeArq.newLine();
            writeArq.write(recipePreparation + ";");
            writeArq.newLine();
            writeArq.newLine();
        }    
    }
    
    public static void deleteCategoryFile(String recipeCategory){
        File fileDel = new File ("C:\\Recipes\\" + recipeCategory + ".txt");
        fileDel.delete();
    }
    
    /*8888888888888888888888888888888 MANAGE GLOBAL VARIABLES 8888888888888888888888888888888*/
    
    public static void createCategory(String recipeCategory){
        ArrayList<Recipe> recipes;
        recipes = new ArrayList<>();
                
        Category C;
        C = new Category(recipeCategory, recipes);
            
        ALL.add(C);
    }
    
    public static void createRecipe(int catIndice, String recipeName, String recipeComment, String recipeIngredients, String recipePreparation){
        Category C;
        C = ALL.get(catIndice);
        
        Recipe R = new Recipe(recipeName, recipeComment, recipeIngredients, recipePreparation);
        C.recipesCategory.add(R); 
    }
    
    public static void removeRecipe(int catIndice, int recIndice){
        ALL.get(catIndice).recipesCategory.remove(recIndice);
    }
    
    public static void removeCategory(int catIndice){
        ALL.remove(catIndice);
    }
       
    public static void changeRecipe(int catIndice, int recIndice, String recipeComment, String recipeIngredients, String recipePreparation){
        ALL.get(catIndice).recipesCategory.get(recIndice).commentRecipe = recipeComment;
        ALL.get(catIndice).recipesCategory.get(recIndice).ingredientsRecipe = recipeIngredients;
        ALL.get(catIndice).recipesCategory.get(recIndice).preparationRecipe = recipePreparation;
    }
    
}
