import { Restaurant } from "./Restaurants";
import { FoodItem } from "./FoodItem";

export interface FoodCatalogPage{
    foodItemsList: FoodItem[];
    restaurant: Restaurant;
}