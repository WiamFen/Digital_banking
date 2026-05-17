import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Customer} from '../services/customer';
import {AsyncPipe, JsonPipe, NgIf} from '@angular/common';
import {catchError, map, Observable, throwError} from 'rxjs';
import {CustomerModel} from '../model/customer.model';
import {FormBuilder, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {Router} from "@angular/router";

@Component({
  selector: 'app-customers',
  imports: [
    AsyncPipe,
    NgIf,
    ReactiveFormsModule
  ],
  templateUrl: './customers.html',
  styleUrl: './customers.css',
  standalone: true,
})
export class Customers implements OnInit{
  customers! : Observable<Array<CustomerModel>>;
  errorMessage! : String;
  searchFormGroup : FormGroup | undefined;
  constructor(private customerService : Customer, private fb: FormBuilder, private router : Router) { }
  ngOnInit() {
    this.searchFormGroup=this.fb.group({
      keyword : this.fb.control("")
    })
    this.handleSearchCustomers();
  }

  handleSearchCustomers() {
    let kw=this.searchFormGroup?.value.keyword;
    this.customers=this.customerService.searchCustomers(kw).pipe(
      catchError(err => {
        this.errorMessage=err.message;
        return throwError(err);
      })
    );
  }

  handleDeleteCustomer(c: CustomerModel) {
    let conf = confirm("Are you sure?");
    if(!conf) return;
    this.customerService.deleteCustomer(c.id).subscribe({
      next : (resp) => {
        this.customers=this.customers.pipe(
          map(data=>{
            let index=data.indexOf(c);
            data.slice(index,1)
            return data;
          })
        );
      },
      error : err => {
        console.log(err);
      }
    })
  }

  // handleCustomerAccounts(customer: CustomerModel) {
  //   this.router.navigateByUrl("/customer-accounts/"+customer.id,{state :customer});
  // }

  handleCustomerAccounts(customer: CustomerModel) {
    this.router.navigate(
      ["/customer-accounts", customer.id],
      { state: customer }
    );
  }
}
