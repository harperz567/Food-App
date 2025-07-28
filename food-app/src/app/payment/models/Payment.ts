export interface Payment {
  paymentId?: number;
  orderId: number;
  userId: number;
  amount: number;
  paymentStatus: string;
  paymentMethod: string;
}