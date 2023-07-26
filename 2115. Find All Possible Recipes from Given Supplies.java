/*
You have information about n different recipes. You are given a string array recipes and a 2D string array ingredients. The ith recipe has the name recipes[i], and you can create it if you have all the needed ingredients from ingredients[i]. Ingredients to a recipe may need to be created from other recipes, i.e., ingredients[i] may contain a string that is in recipes.

You are also given a string array supplies containing all the ingredients that you initially have, and you have an infinite supply of all of them.

Return a list of all the recipes that you can create. You may return the answer in any order.

Note that two recipes may contain each other in their ingredients.

Example 1:
Input: recipes = ["bread"], ingredients = [["yeast","flour"]], supplies = ["yeast","flour","corn"]
Output: ["bread"]
Explanation:
We can create "bread" since we have the ingredients "yeast" and "flour".

Example 2:
Input: recipes = ["bread","sandwich"], ingredients = [["yeast","flour"],["bread","meat"]], supplies = ["yeast","flour","meat"]
Output: ["bread","sandwich"]
Explanation:
We can create "bread" since we have the ingredients "yeast" and "flour".
We can create "sandwich" since we have the ingredient "meat" and can create the ingredient "bread".

Example 3:
Input: recipes = ["bread","sandwich","burger"], ingredients = [["yeast","flour"],["bread","meat"],["sandwich","meat","bread"]], supplies = ["yeast","flour","meat"]
Output: ["bread","sandwich","burger"]
Explanation:
We can create "bread" since we have the ingredients "yeast" and "flour".
We can create "sandwich" since we have the ingredient "meat" and can create the ingredient "bread".
We can create "burger" since we have the ingredient "meat" and can create the ingredients "bread" and "sandwich".
 
Constraints:
n == recipes.length == ingredients.length
1 <= n <= 100
1 <= ingredients[i].length, supplies.length <= 100
1 <= recipes[i].length, ingredients[i][j].length, supplies[k].length <= 10
recipes[i], ingredients[i][j], and supplies[k] consist only of lowercase English letters.
All the values of recipes and supplies combined are unique.
Each ingredients[i] does not contain any duplicate values.
*/

// topological sorting
class Solution {
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        List<String> res = new ArrayList<>();
        Set<String> supplySet = new HashSet<>();
        // key is ingredient(some are recipes), value is recipes
        Map<String, List<String>> map = new HashMap<>();
        // key is recipe, value is indegree
        Map<String, Integer> indegrees = new HashMap<>();
        // store indegree 0 recipes
        Queue<String> queue = new LinkedList<>();

        for (String supply : supplies)
            supplySet.add(supply);

        // build graph of ingredients and recipes, to get recipe dependency
        for (int i = 0; i < recipes.length; i++) {
            List<String> curIngreList = ingredients.get(i);
            for (String ingre : curIngreList) {
                map.putIfAbsent(ingre, new ArrayList<>());
                map.get(ingre).add(recipes[i]);
            }
        }

        // build indegree map
        for (int i = 0; i < recipes.length; i++) {
            List<String> curIngreList = ingredients.get(i);
            for (String ingre : curIngreList) {
                indegrees.putIfAbsent(recipes[i], 0);
                if (!supplySet.contains(ingre))
                    indegrees.put(recipes[i], indegrees.get(recipes[i]) + 1);
            }
        }

        for (String recipe : indegrees.keySet()) {
            if (indegrees.get(recipe) == 0) {
                queue.add(recipe);
                res.add(recipe);
            }
        }

        while (!queue.isEmpty()) {
            String recipe = queue.poll();
            if (!map.keySet().contains(recipe))
                continue;
            List<String> nextRecipes = map.get(recipe);
            for (String nextRecipe : nextRecipes) {
                int newIndegree = indegrees.get(nextRecipe) - 1;
                if (newIndegree == 0) {
                    queue.add(nextRecipe);
                    res.add(nextRecipe);
                }
                indegrees.put(nextRecipe, newIndegree);
            }
        }
        return res;
    }
}
