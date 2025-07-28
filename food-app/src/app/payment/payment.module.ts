import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { PaymentRoutingModule } from './payment-routing.module';
import { PaymentFormComponent } from './components/payment-form/payment-form.component';
import { PaymentSuccessComponent } from './components/payment-success/payment-success.component';
import { PaymentFailedComponent } from './components/payment-failed/payment-failed.component';
import { PaymentService } from './service/payment.service';



@NgModule({
  declarations: [
    PaymentFormComponent,
    PaymentSuccessComponent,
    PaymentFailedComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule,
    PaymentRoutingModule
  ],
  providers: [
    PaymentService
  ]
})
export class PaymentModule { }