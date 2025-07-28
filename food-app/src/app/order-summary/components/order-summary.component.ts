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
  isProcessingOrder: boolean = false;

  constructor(private route: ActivatedRoute, private orderService: OrderService, private router: Router) { }
  
  ngOnInit() {
    const data = this.route.snapshot.queryParams['data'];
    this.obj = JSON.parse(data);
    this.obj.userId = 1;
    this.orderSummary = this.obj;

    // Calculate total
    if (this.orderSummary && this.orderSummary.foodItemsList) {
      this.total = this.orderSummary.foodItemsList.reduce((accumulator, currentValue) => {
        const price = currentValue.price || 0;
        return accumulator + (currentValue.quantity * price);
      }, 0);
    }
  }

  saveOrder() {
    this.isProcessingOrder = true;
    
    this.orderService.saveOrder(this.orderSummary)
      .subscribe({
        next: (response) => {
          console.log('Order saved successfully:', response);
          this.isProcessingOrder = false;
          
          // Add the saved order ID to the order data
          this.obj.orderId = response.orderId;
          
          // Navigate to payment page instead of showing success dialog
          this.router.navigate(['/payment/checkout'], { 
            queryParams: { 
              data: JSON.stringify(this.obj) 
            }
          });
        },
        error: (error) => {
          console.error('Failed to save order:', error);
          this.isProcessingOrder = false;
          // You can show an error message here if needed
          alert('Failed to save order. Please try again.');
        }
      });
  }

  closeDialog() {
    this.showDialog = false;
    this.router.navigate(['/']); 
  }
}