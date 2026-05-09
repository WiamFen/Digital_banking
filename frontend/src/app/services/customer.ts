import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class Customer {
  constructor(private http : HttpClient) { }

  public getCustomers(){
    return this.http.get("http://localhost:8085/customers")
  }
}
