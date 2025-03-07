import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { OrderService } from '../service/order.service';
import { OrderDTO } from '../models/OrderDTO';

@Component({
  selector: 'app-order-summary',
  templateUrl: './order-summary.component.html',
  styleUrls: ['./order-summary.component.css'],
  standalone: false 
})
export class OrderSummaryComponent {

  orderSummary!: OrderDTO;
  obj: any;
  total?: any;
  showDialog: boolean = false;

  constructor(private route: ActivatedRoute, private orderService: OrderService, private router: Router) { }
  
  ngOnInit() {
    const data = this.route.snapshot.queryParams['data'];
    this.obj = JSON.parse(data);
    this.obj.userId=1;
    this.orderSummary = this.obj;

    // this.total = this.orderSummary.foodItemsList.reduce((accumulator, currentValue) => {
    //   return accumulator + (currentValue.quantity * currentValue.price);
    // }, 0);
    if (this.orderSummary && this.orderSummary.foodItemsList) {
      this.total = this.orderSummary.foodItemsList.reduce((accumulator, currentValue) => {
        // 确保 price 存在，如果不存在则默认为 0
        const price = currentValue.price || 0;
        return accumulator + (currentValue.quantity * price);
      }, 0);
    }

  }

  saveOrder() {
    this.orderService.saveOrder(this.orderSummary)
      .subscribe(
        response => {
            this.showDialog = true;
        },
        error => {
          console.error('Failed to save data:', error);
        }
      );
  }

  closeDialog() {
    this.showDialog = false;
    this.router.navigate(['/']); 
  }
}