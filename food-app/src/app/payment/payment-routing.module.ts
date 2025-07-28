import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PaymentFormComponent } from './components/payment-form/payment-form.component';
import { PaymentSuccessComponent } from './components/payment-success/payment-success.component';
import { PaymentFailedComponent } from './components/payment-failed/payment-failed.component';

const routes: Routes = [
  { path: 'checkout', component: PaymentFormComponent },
  { path: 'success', component: PaymentSuccessComponent },
  { path: 'failed', component: PaymentFailedComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PaymentRoutingModule { }