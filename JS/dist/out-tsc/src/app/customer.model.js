import * as tslib_1 from "tslib";
import { Injectable } from '@angular/core';
let Customer = class Customer {
    constructor() {
        this.id = 0;
        this.email = "";
        this.first_name = "";
        this.last_name = "";
        this.ip = "";
        this.latitude = "";
        this.longitude = "";
        this.created_at = "";
        this.updated_at = "";
    }
};
Customer = tslib_1.__decorate([
    Injectable({ providedIn: 'root' })
], Customer);
export { Customer };
//# sourceMappingURL=customer.model.js.map