import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, tap, throwError } from 'rxjs';
import { API_URL_Order } from '../../constants/url';


@Injectable({
  providedIn: 'root'
})

export class OrderService {

  private apiUrl = API_URL_Order+'/order/saveOrder';

  constructor(private http: HttpClient) { }

 httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':'application/json',
      'Access-Control-Allow-Origin': 'http://localhost:4200' 
    })
  };

  saveOrder(data: any):Observable<any>  {
    // return this.http.post<any>(this.apiUrl, data);
    console.log('Sending order data:', JSON.stringify(data));
    
    // 使用 httpOptions 发送请求
    return this.http.post<any>(this.apiUrl, data, this.httpOptions)
      .pipe(
        tap(response => console.log('Order saved successfully:', response)),
        catchError(this.handleError)
      );
  }

  private handleError(error: any) {
    console.error('An error occurred:', error);
    return throwError(error.message || error);
  }


}