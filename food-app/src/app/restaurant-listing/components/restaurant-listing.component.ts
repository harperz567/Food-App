//import { Component } from '@angular/core';
import { Restaurant } from '../../shared/models/Restaurants';
import { Router } from '@angular/router'
import { RestaurantService } from '../service/restaurant.service';
import { Component, OnInit, ChangeDetectorRef } from '@angular/core';

@Component({
  selector: 'app-restaurant-listing',
  standalone: false,
  templateUrl: './restaurant-listing.component.html',
  styleUrl: './restaurant-listing.component.css'
})
export class RestaurantListingComponent implements OnInit{
  public restaurantList: Restaurant[];
  public restaurantImages: {[id: number]: string} = {}; // store a static pic

  ngOnInit() {
    this.getAllRestaurants();
  }
  constructor(
    private router: Router, 
    private restaurantService: RestaurantService,
    private cdr: ChangeDetectorRef // Avoid double check pic routes
  ) { }

  getAllRestaurants() {
    this.restaurantService.getAllRestaurants().subscribe(
      data => {
        this.restaurantList = data;
        // 为每个餐厅预先生成固定的图片
        this.restaurantList.forEach(restaurant => {
          if (restaurant.id !== undefined) {
            this.restaurantImages[restaurant.id] = this.getRandomImagePath();
          }
        });
      }
    );
  }

  getRandomImagePath(): string {
    const imageCount = 6;
    const randomIndex = this.getRandomNumber(1, imageCount);
    return `${randomIndex}.jpg`;
  }
  getImageForRestaurant(id: number): string {
    return this.restaurantImages[id] || '1.jpg';
  }

  getRandomNumber(min: number, max: number): number {
    return Math.floor(Math.random() * (max - min + 1)) + min;
  }

  getRandomImage(): string {
    const imageCount = 6; // Adjust this number based on the number of images in your asset folder
    const randomIndex = this.getRandomNumber(1, imageCount);
    return `${randomIndex}.jpg`; // Replace with your image filename pattern
  }

  onButtonClick(id: number) {
    this.router.navigate(['/food-catalog', id]);
  }

}
