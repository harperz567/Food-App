import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-payment-success',
  templateUrl: './payment-success.component.html',
  styleUrls: ['./payment-success.component.css'],
  standalone: false
})
export class PaymentSuccessComponent implements OnInit {

  paymentId: number | null = null;
  amount: number | null = null;

  constructor(private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    this.paymentId = Number(this.route.snapshot.queryParams['paymentId']);
    this.amount = Number(this.route.snapshot.queryParams['amount']);
  }

  goHome() {
    this.router.navigate(['/']);
  }

  trackOrder() {
    alert('Order tracking feature coming soon!');
  }
}