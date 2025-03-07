import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FoodItemService } from '../service/foodItem.service';
import { FoodCatalogPage } from '../../shared/models/FoodCatalogPage';
import { FoodItem } from '../../shared/models/FoodItem';
import { Restaurant } from '../../shared/models/Restaurants';


@Component({
  selector: 'app-food-catalog',
  standalone: false,
  templateUrl: './food-catalog.component.html',
  styleUrl: './food-catalog.component.css'
})

export class FoodCatalogComponent {

  restaurantId: number;
  // foodItemResponse: FoodCatalogPage;
  foodItemResponse?: FoodCatalogPage;
  foodItemCart: FoodItem[] = [];
  orderSummary: FoodCatalogPage;


  constructor(private route: ActivatedRoute, private foodItemService: FoodItemService, private router: Router) {
  }

  ngOnInit() {

    this.route.paramMap.subscribe(params => {
      this.restaurantId = +params.get('id')!;
    });

    this.getFoodItemsByRestaurant(this.restaurantId);
    
  }

  getFoodItemsByRestaurant(restaurant: number) {
    this.foodItemService.getFoodItemsByRestaurant(restaurant).subscribe(
      data => {
        this.foodItemResponse = data;
        
        // Initialize quantity to be 0
        if (this.foodItemResponse && this.foodItemResponse.foodItemsList) {
          this.foodItemResponse.foodItemsList.forEach(item => {
            item.quantity = 0;
          });
        }
      }
    )
  }

  increment(food: any) {
    if (!food.quantity) {
      food.quantity = 1; 
    } else {
      food.quantity++;
    }
  
    const index = this.foodItemCart.findIndex(item => item.id === food.id);
    if (index === -1) {
      // If record does not exist, add it to the array
      this.foodItemCart.push(food);
    } else {
      // If record exists, update it in the array
      this.foodItemCart[index] = food;
    }
  }
  
  decrement(food: any) {
    if (!food.quantity || food.quantity === 0) {
      return; 
    }
  
    food.quantity--;
  
    const index = this.foodItemCart.findIndex(item => item.id === food.id);
    if (index !== -1) {
      if (food.quantity === 0) {
        this.foodItemCart.splice(index, 1);
      } else {
        // If record exists, update it in the array
        this.foodItemCart[index] = food;
      }
    }
  }
  

  onCheckOut() {
    //this.foodItemCart;
    const foodItems = this.foodItemResponse?.foodItemsList || [];
  
    // Get all items > 0
    this.foodItemCart = foodItems.filter(item => item.quantity && item.quantity > 0);
    this.orderSummary = {
      foodItemsList: [],
      restaurant: {} as Restaurant
    }
    this.orderSummary.foodItemsList = this.foodItemCart;
    this.orderSummary.restaurant = this.foodItemResponse!.restaurant;
    this.router.navigate(['/orderSummary'], { queryParams: { data: JSON.stringify(this.orderSummary) } });
  }

}
