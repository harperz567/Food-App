import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-payment-failed',
  templateUrl: './payment-failed.component.html',
  styleUrls: ['./payment-failed.component.css'],
  standalone: false
})
export class PaymentFailedComponent implements OnInit {

  errorMessage: string = 'Payment processing failed. Please try again.';

  constructor(private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    const message = this.route.snapshot.queryParams['message'];
    if (message) {
      this.errorMessage = message;
    }
  }

  tryAgain() {
    window.history.back();
  }

  goHome() {
    this.router.navigate(['/']);
  }

  contactSupport() {
    alert('Support contact: support@foodapp.com or call 1-800-FOOD-APP');
  }
}