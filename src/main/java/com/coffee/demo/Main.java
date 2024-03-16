package com.coffee.demo;

import com.coffee.demo.pojo.Beverage;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Read JSON input
            JsonNode jsonNode = objectMapper.readTree(new File("D:/coffee/src/main/resources/input.json"));


            // Extract machine configuration
            JsonNode outletsNode = jsonNode.get("machine").get("outlets");
            int outlets = outletsNode.get("count_n").asInt();

            // Extract total items quantity
            JsonNode totalItemsNode = jsonNode.get("machine").get("total_items_quantity");
            Map<String, Integer> ingredients = new HashMap<>();
            Iterator<Map.Entry<String, JsonNode>> totalItemsIterator = totalItemsNode.fields();
            while (totalItemsIterator.hasNext()) {
                Map.Entry<String, JsonNode> entry = totalItemsIterator.next();
                ingredients.put(entry.getKey(), entry.getValue().asInt());
            }

            // Extract beverages
            JsonNode beveragesNode = jsonNode.get("machine").get("beverages");
            Map<String, Beverage> beverages = new HashMap<>();
            Iterator<Map.Entry<String, JsonNode>> beveragesIterator = beveragesNode.fields();
            while (beveragesIterator.hasNext()) {
                Map.Entry<String, JsonNode> entry = beveragesIterator.next();
                String beverageName = entry.getKey();
                JsonNode beverageNode = entry.getValue();
                Map<String, Integer> beverageIngredients = new HashMap<>();
                Iterator<Map.Entry<String, JsonNode>> beverageIngredientsIterator = beverageNode.fields();
                while (beverageIngredientsIterator.hasNext()) {
                    Map.Entry<String, JsonNode> ingredientEntry = beverageIngredientsIterator.next();
                    beverageIngredients.put(ingredientEntry.getKey(), ingredientEntry.getValue().asInt());
                }
                beverages.put(beverageName, new Beverage(beverageName, beverageIngredients));
            }

            // Creating coffee machine instance
            CoffeeMachine coffeeMachine = new CoffeeMachine(outlets, ingredients, beverages);

            // Output
            coffeeMachine.serveBeverages();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}