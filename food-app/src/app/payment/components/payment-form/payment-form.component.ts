import { Component, OnInit, AfterViewInit, ViewChild, ElementRef } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PaymentService } from '../../service/payment.service';
import { PaymentRequest } from '../../models/PaymentRequest';
import { PaymentResponse } from '../../models/PaymentResponse';

@Component({
  selector: 'app-payment-form',
  templateUrl: './payment-form.component.html',
  styleUrls: ['./payment-form.component.css'],
  standalone: false
})
export class PaymentFormComponent implements OnInit, AfterViewInit {

  @ViewChild('cardElement', { static: false }) cardElement!: ElementRef;

  orderData: any;
  paymentRequest: PaymentRequest;
  paymentResponse: PaymentResponse | null = null;
  isProcessing: boolean = false;
  card: any;
  clientSecret: string = '';

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private paymentService: PaymentService
  ) {
    this.paymentRequest = {
      orderId: 0,
      userId: 1,
      amount: 0,
      paymentMethod: 'card'
    };
  }

  ngOnInit() {
    const data = this.route.snapshot.queryParams['data'];
    if (data) {
      this.orderData = JSON.parse(data);
      
      const total = this.orderData.foodItemsList.reduce((accumulator: number, currentValue: any) => {
        const price = currentValue.price || 0;
        return accumulator + (currentValue.quantity * price);
      }, 0);

      this.paymentRequest = {
        orderId: this.orderData.orderId || Math.floor(Math.random() * 1000),
        userId: this.orderData.userId || 1,
        amount: total,
        paymentMethod: 'card'
      };

      this.createPaymentIntent();
    }
  }

  ngAfterViewInit() {
    setTimeout(() => {
      this.card = this.paymentService.createElement();
      this.card.mount(this.cardElement.nativeElement);
    }, 100);
  }

  createPaymentIntent() {
    this.paymentService.createPaymentIntent(this.paymentRequest)
      .subscribe({
        next: (response) => {
          console.log('Payment intent created:', response);
          this.paymentResponse = response;
          this.clientSecret = response.clientSecret;
        },
        error: (error) => {
          console.error('Failed to create payment intent:', error);
          this.router.navigate(['/payment/failed'], { 
            queryParams: { 
              message: 'Failed to create payment intent' 
            }
          });
        }
      });
  }

  async processPayment() {
    if (!this.clientSecret || !this.card) {
      return;
    }

    this.isProcessing = true;
    
    try {
      const result = await this.paymentService.confirmPayment(this.clientSecret, this.card);
      
      if (result.error) {
        console.error('Payment failed:', result.error);
        this.router.navigate(['/payment/failed'], { 
          queryParams: { 
            message: result.error.message 
          }
        });
      } else {
        console.log('Payment succeeded:', result.paymentIntent);
        this.router.navigate(['/payment/success'], { 
          queryParams: { 
            paymentId: this.paymentResponse?.paymentId,
            amount: this.paymentRequest.amount 
          }
        });
      }
    } catch (error) {
      console.error('Payment error:', error);
      this.router.navigate(['/payment/failed'], { 
        queryParams: { 
          message: 'Payment processing failed' 
        }
      });
    } finally {
      this.isProcessing = false;
    }
  }

  goBack() {
    this.router.navigate(['/orderSummary'], { 
      queryParams: { data: JSON.stringify(this.orderData) }
    });
  }
}

// Test Cards:

// Success: 4242 4242 4242 4242

// Decline: 4000 0000 0000 0002

// Use any future date and any 3-digit CVV