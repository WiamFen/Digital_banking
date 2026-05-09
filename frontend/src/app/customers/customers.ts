import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Customer} from '../services/customer';
import {JsonPipe} from '@angular/common';

@Component({
  selector: 'app-customers',
  imports: [
    JsonPipe
  ],
  templateUrl: './customers.html',
  styleUrl: './customers.css',
  standalone: true,
})
export class Customers implements OnInit{
  customers : any;
  errorMessage! : object;
  constructor(private customerService : Customer) { }
  ngOnInit() {
    this.customerService.getCustomers().subscribe({
      next : (data) => {
        this.customers=data;
      },error : (err) => {
        this.errorMessage=err;
      }
    });
  }
}
