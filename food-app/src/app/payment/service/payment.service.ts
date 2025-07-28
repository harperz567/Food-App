import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, tap, throwError } from 'rxjs';
import { PaymentRequest } from '../models/PaymentRequest';
import { PaymentResponse } from '../models/PaymentResponse';
import { API_URL_Payment } from '../../constants/url';

declare var Stripe: any;

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  private createPaymentUrl = API_URL_Payment + '/payment/createPaymentIntent';
  private statusUrl = API_URL_Payment + '/payment/status';
  private stripe: any;

  constructor(private http: HttpClient) {
    // Replace with your actual Stripe publishable key
    this.stripe = Stripe('pk_test_replace_with_your_actual_key');
  }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': 'http://localhost:4200' 
    })
  };

  createPaymentIntent(paymentRequest: PaymentRequest): Observable<PaymentResponse> {
    console.log('Creating payment intent:', JSON.stringify(paymentRequest));
    
    return this.http.post<PaymentResponse>(this.createPaymentUrl, paymentRequest, this.httpOptions)
      .pipe(
        tap(response => console.log('Payment intent created:', response)),
        catchError(this.handleError)
      );
  }

  async confirmPayment(clientSecret: string, cardElement: any): Promise<any> {
    return await this.stripe.confirmCardPayment(clientSecret, {
      payment_method: {
        card: cardElement,
        billing_details: {
          name: 'Test User'
        }
      }
    });
  }

  getPaymentStatus(paymentId: number): Observable<PaymentResponse> {
    return this.http.get<PaymentResponse>(`${this.statusUrl}/${paymentId}`, this.httpOptions)
      .pipe(
        tap(response => console.log('Payment status:', response)),
        catchError(this.handleError)
      );
  }

  createElement() {
    const elements = this.stripe.elements();
    return elements.create('card', {
      style: {
        base: {
          fontSize: '16px',
          color: '#424770',
          '::placeholder': {
            color: '#aab7c4',
          },
        },
      },
    });
  }

  private handleError(error: any) {
    console.error('An error occurred:', error);
    return throwError(error.message || error);
  }
}