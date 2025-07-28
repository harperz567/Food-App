export interface PaymentResponse {
  paymentId: number;
  paymentStatus: string;
  clientSecret: string;
  message: string;
  amount: number;
}
