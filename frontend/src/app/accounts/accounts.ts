import {Component, OnInit} from '@angular/core';
import {Account} from '../services/account';
import {FormBuilder, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {AccountDetails} from '../model/account.model';
import {Observable} from 'rxjs';
import {AsyncPipe, DatePipe, DecimalPipe, NgClass, NgForOf, NgIf} from '@angular/common';

@Component({
  selector: 'app-accounts',
  imports: [ReactiveFormsModule, NgIf, AsyncPipe, DecimalPipe, NgForOf, DatePipe, NgClass],
  templateUrl: './accounts.html',
  styleUrl: './accounts.css',
  standalone: true,
})
export class Accounts implements OnInit{
  accountFormGroup! : FormGroup;
  currentPage : number = 0;
  pageSize : number = 5;
  accountObservable! : Observable<AccountDetails>

  constructor(private fb: FormBuilder,private accountService : Account) { }
  ngOnInit() {
    this.accountFormGroup=this.fb.group({
      accountId : this.fb.control('')
    })
  }

  handleSearchAccount() {
    let accountId : string=this.accountFormGroup.value.accountId;
    this.accountObservable=this.accountService.getAccount(accountId,this.currentPage,this.pageSize);
  }

  gotoPage(page: number) {
    this.currentPage=page;
    this.handleSearchAccount();
  }
}
