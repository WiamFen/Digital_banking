import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-customers',
  imports: [],
  templateUrl: './customers.html',
  styleUrl: './customers.css',
  standalone: true,
})
export class Customers implements OnInit{
  customers : any;
  constructor(private http:HttpClient) {
  }
  ngOnInit() {
    this.http.get("http://localhost:8085/customers").subscribe(data => {
      this.customers=data;
    },error => {
      console.log(error);
    })
  }
}
