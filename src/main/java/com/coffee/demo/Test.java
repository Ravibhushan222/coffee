package com.coffee.demo;

import com.coffee.demo.pojo.Beverage;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {

        Map<String, Integer> ingredients = new HashMap<>();
        ingredients.put("hot_water", 500);
        ingredients.put("hot_milk", 500);
        ingredients.put("ginger_syrup", 100);
        ingredients.put("sugar_syrup", 100);
        ingredients.put("tea_leaves_syrup", 100);

        Map<String, Beverage> beverages = new HashMap<>();
        Map<String, Integer> hotTeaIngredients = new HashMap<>();
        hotTeaIngredients.put("hot_water", 200);
        hotTeaIngredients.put("hot_milk", 100);
        hotTeaIngredients.put("ginger_syrup", 10);
        hotTeaIngredients.put("sugar_syrup", 10);
        hotTeaIngredients.put("tea_leaves_syrup", 30);
        beverages.put("hot_tea", new Beverage("hot_tea", hotTeaIngredients));

        Map<String, Integer> hotCoffeeIngredients = new HashMap<>();
        hotCoffeeIngredients.put("hot_water", 100);
        hotCoffeeIngredients.put("ginger_syrup", 30);
        hotCoffeeIngredients.put("hot_milk", 400);
        hotCoffeeIngredients.put("sugar_syrup", 50);
        hotCoffeeIngredients.put("tea_leaves_syrup", 30);
        beverages.put("hot_coffee", new Beverage("hot_coffee", hotCoffeeIngredients));

        Map<String, Integer> blackTeaIngredients = new HashMap<>();
        blackTeaIngredients.put("hot_water", 300);
        blackTeaIngredients.put("ginger_syrup", 30);
        blackTeaIngredients.put("sugar_syrup", 50);
        blackTeaIngredients.put("tea_leaves_syrup", 30);
        beverages.put("black_tea", new Beverage("black_tea", blackTeaIngredients));

        Map<String, Integer> greenTeaIngredients = new HashMap<>();
        greenTeaIngredients.put("hot_water", 100);
        greenTeaIngredients.put("ginger_syrup", 30);
        greenTeaIngredients.put("sugar_syrup", 50);
        greenTeaIngredients.put("green_mixture", 30);
        beverages.put("green_tea", new Beverage("green_tea", greenTeaIngredients));

        CoffeeMachine coffeeMachine = new CoffeeMachine(3, ingredients, beverages);
        coffeeMachine.serveBeverages();
    }
}

