import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import { environment } from '../../environments/environment';
import { CustomerModel } from '../model/customer.model';

@Injectable({
  providedIn: 'root',
})
export class Customer {
  private apiUrl = `${environment.backendHost}/customers`;

  constructor(private http : HttpClient) { }

  public getCustomers():Observable<Array<CustomerModel>>{
    return this.http.get<Array<CustomerModel>>(this.apiUrl)
  }

  public searchCustomers(keyword: string): Observable<CustomerModel[]> {
    return this.http.get<CustomerModel[]>(`${this.apiUrl}/search?keyword=${keyword}`);
  }

  public saveCustomer(customer: CustomerModel):Observable<CustomerModel>{
    return this.http.post<CustomerModel>(environment.backendHost+"/customers",customer);
  }

  public deleteCustomer(id: number){
    return this.http.delete(environment.backendHost+"/customers/"+id);
  }
}
