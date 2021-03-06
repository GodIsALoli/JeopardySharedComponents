/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeopardygame.sharedmodel;

import java.util.*;
import java.io.*;
/**
 *
 * @author student
 */
public class QuestionManager extends Observable implements Serializable{
    public final ArrayList<Category> categories;
    private int doubleCategoryIndex1, doubleQuestionIndex1, doubleCategoryIndex2, doubleQuestionIndex2;
    
    public QuestionManager(){
        categories = new ArrayList();
    }
    
    public void start(){
        Random rnd = new Random();
        doubleCategoryIndex1 = rnd.nextInt(categories.size());
        doubleQuestionIndex1 = rnd.nextInt(categories.get(doubleCategoryIndex1).questions.size());
        System.out.println(Integer.toString(doubleCategoryIndex1) + " " + Integer.toString(doubleQuestionIndex1));
        do{
            doubleCategoryIndex2 = rnd.nextInt(categories.size());
            doubleQuestionIndex2 = rnd.nextInt(categories.get(doubleCategoryIndex2).questions.size());
        } while((doubleCategoryIndex2 == doubleCategoryIndex1) && (doubleQuestionIndex2 == doubleQuestionIndex1));
        
        System.out.println(Integer.toString(doubleCategoryIndex2) + " " + Integer.toString(doubleQuestionIndex2));
    }
    
    public void addQuestion(Question newQuestion){
        categories.get(newQuestion.categoryIndex).addQuestion(newQuestion.questionIndex, newQuestion);
        this.setChanged();
        this.notifyObservers();
    }
    
    public void removeQuestion(int categoryIndex, int questionIndex){
        categories.get(categoryIndex).removeQuestion(questionIndex);
        if(categories.get(categoryIndex).getNumberOfQuestions() == 0)
            categories.remove(categoryIndex);
        
        this.setChanged();
        this.notifyObservers();
    }
    
    public void addNewCategory(Category newCategory){
        categories.add(newCategory);
    }
    
    public int getNumOfQuestions(int categoryIndex){
        return categories.get(categoryIndex).questions.size();
    }
    
    public int getNumOfCategories(){
        return categories.size();
    }
    
    public int getNumOfTotalQuestions(){
        int total = 0;
    
        Iterator<Category> it = categories.iterator();
        while(it.hasNext())
            total += it.next().getNumberOfQuestions();

        return total;
    }
    
    public Category getCategory(int categoryIndex){
        return categories.get(categoryIndex);
    }
    
    public Question getQuestion(int categoryIndex, int questionIndex){
        return categories.get(categoryIndex).questions.get(questionIndex);
    }
    
    public boolean isDoubleJeopardy(int categoryIndex, int questionIndex){
        return categoryIndex == this.doubleCategoryIndex1 && questionIndex == this.doubleQuestionIndex1 ||
               categoryIndex == this.doubleCategoryIndex2 && questionIndex == this.doubleQuestionIndex2;
    }
    
    public void setCredits(int categoryIndex, int questionIndex, int newCredits){
        this.categories.get(categoryIndex).questions.get(questionIndex).setCredits(newCredits);
    }
}
