import { Routes } from '@angular/router';
import { Login } from './login/login'
import { Customers } from './customers/customers'
import {Accounts} from './accounts/accounts';
import {NewCustomer} from './new-customer/new-customer';
import {CustomerAccounts} from './customer-accounts/customer-accounts';

export const routes: Routes = [
  {path : "login", component : Login},
  {path : "", redirectTo : "/login", pathMatch:"full"},
  {path : "customers", component : Customers},
  {path : "accounts", component : Accounts},
  {path : "new-costumer", component : NewCustomer},
  {path : "customer-accounts/:id", component : CustomerAccounts}
];
