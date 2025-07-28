export interface PaymentRequest {
  orderId: number;
  userId: number;
  amount: number;
  paymentMethod: string;
}