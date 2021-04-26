import * as tslib_1 from "tslib";
import { Component } from '@angular/core';
import { Customer } from './customer.model';
let AppComponent = class AppComponent {
    constructor(httpClient) {
        this.httpClient = httpClient;
        this.title = 'JS';
        this.filteredCustomers = [];
        this.allCustomers = [];
        this.searchCustomer = new Customer();
    }
    ngOnInit() {
        this.httpClient.get("assets/customers.json").subscribe(data => {
            this.allCustomers = data;
            this.display();
        });
    }
    display() {
        this.filteredCustomers = [];
        let numberOfFilteredCustomers = 0;
        for (let customer of this.allCustomers) {
            if (this.filterbyEmail(customer)
                && this.filterbyFirstName(customer)
                && this.filterbyLastName(customer)) {
                this.filteredCustomers[numberOfFilteredCustomers] = customer;
                numberOfFilteredCustomers++;
            }
        }
        console.log(this.filteredCustomers);
    }
    filterbyEmail(customer) {
        return this.searchCustomer.email == "" || customer.email == this.searchCustomer.email.trim();
    }
    filterbyFirstName(customer) {
        return this.searchCustomer.first_name.trim() == "" || customer.first_name.toLowerCase() == this.searchCustomer.first_name.toLowerCase().trim();
    }
    filterbyLastName(customer) {
        return this.searchCustomer.last_name.trim() == "" || customer.last_name.toLowerCase() == this.searchCustomer.last_name.toLowerCase().trim();
    }
};
AppComponent = tslib_1.__decorate([
    Component({
        selector: 'app-root',
        templateUrl: './app.component.html',
        styleUrls: ['./app.component.css']
    })
], AppComponent);
export { AppComponent };
//# sourceMappingURL=app.component.js.map