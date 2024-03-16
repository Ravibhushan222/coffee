package com.coffee.demo;

import com.coffee.demo.pojo.Beverage;
import com.coffee.demo.pojo.Ingredient;

import java.util.HashMap;
import java.util.Map;

public class CoffeeMachine {
    private int outlets;
    private Map<String, Ingredient> availableIngredients;
    private Map<String, Beverage> availableBeverages;

    public CoffeeMachine(int outlets, Map<String, Integer> ingredients, Map<String, Beverage> beverages) {
        this.outlets = outlets;
        this.availableIngredients = new HashMap<>();
        for (Map.Entry<String, Integer> entry : ingredients.entrySet()) {
            this.availableIngredients.put(entry.getKey(), new Ingredient(entry.getKey(), entry.getValue()));
        }
        this.availableBeverages = beverages;
    }

    public synchronized void serveBeverages() {
        for (String beverageName : availableBeverages.keySet()) {
            Beverage beverage = availableBeverages.get(beverageName);
            if (canServeBeverage(beverage)) {
                System.out.println(beverage.getName() + " is prepared");
                reduceIngredientQuantities(beverage);
            } else {
                System.out.println(beverage.getName() + " cannot be prepared");
            }
        }
    }

    private boolean canServeBeverage(Beverage beverage) {
        for (Map.Entry<String, Integer> entry : beverage.getIngredients().entrySet()) {
            String ingredientName = entry.getKey();
            int requiredQuantity = entry.getValue();
            if (!availableIngredients.containsKey(ingredientName) || availableIngredients.get(ingredientName).getQuantity() < requiredQuantity) {
                return false;
            }
        }
        return true;
    }

    private void reduceIngredientQuantities(Beverage beverage) {
        for (Map.Entry<String, Integer> entry : beverage.getIngredients().entrySet()) {
            String ingredientName = entry.getKey();
            int requiredQuantity = entry.getValue();
            availableIngredients.get(ingredientName).setQuantity(availableIngredients.get(ingredientName).getQuantity() - requiredQuantity);
        }
    }
}