import { Component } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Customer } from './customer.model';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  title = 'JS';
  filteredCustomers : Array<Customer> = [];
  allCustomers : Array<Customer> = [];
  searchCustomer: Customer = new Customer();

  constructor(private httpClient: HttpClient){}
  ngOnInit(){
    this.httpClient.get("assets/customers.json").subscribe(data =>{
      this.allCustomers = data as Array<Customer>;
      this.display();
    })  
  }



  display() : void {
    this.filteredCustomers = [];
    let numberOfFilteredCustomers = 0;
      for(let customer of this.allCustomers) {
        if(this.filterbyEmail(customer) 
          && this.filterbyFirstName(customer)
          && this.filterbyLastName(customer)){
          this.filteredCustomers[numberOfFilteredCustomers] = customer;
          numberOfFilteredCustomers++;
        }
      }
      console.log(this.filteredCustomers);
  }

  filterbyEmail(customer: Customer): boolean {
    return this.searchCustomer.email == "" || customer.email == this.searchCustomer.email.trim() ;
  }

  filterbyFirstName(customer: Customer): boolean {
   return  this.searchCustomer.first_name.trim() == "" || customer.first_name.toLowerCase() == this.searchCustomer.first_name.toLowerCase().trim();
  }
  filterbyLastName(customer: Customer): boolean {
    return this.searchCustomer.last_name.trim() == "" || customer.last_name.toLowerCase() == this.searchCustomer.last_name.toLowerCase().trim();
  }
}
