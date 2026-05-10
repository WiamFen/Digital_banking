import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {CustomerModel} from '../model/customer.model';
import {Customer} from '../services/customer';
import {Router} from '@angular/router';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-new-customer',
  imports: [ReactiveFormsModule, NgIf],
  templateUrl: './new-customer.html',
  styleUrl: './new-customer.css',
})
export class NewCustomer implements OnInit{
  newCustomerFormGroup! : FormGroup;
  constructor(private fb: FormBuilder, private customerService : Customer, private router:Router) { }

  ngOnInit() {
    this.newCustomerFormGroup=this.fb.group({
      name : this.fb.control(null, [Validators.required, Validators.minLength(4) ]),
      email : this.fb.control(null,[Validators.required,Validators.email])
    })
  }

  handleSaveCustomer() {
    let customer:CustomerModel=this.newCustomerFormGroup.value;
    this.customerService.saveCustomer(customer).subscribe({
      next : data=>{
        alert("Customer has been successfully saved!");
        //this.newCustomerFormGroup.reset();
        this.router.navigateByUrl("/customers");
      },
      error : err => {
        console.log(err);
      }
    });
  }
}
