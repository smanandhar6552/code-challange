import { Injectable } from '@angular/core';

@Injectable({providedIn :'root'})
export class Customer {
    id: number = 0;
    email: string = "";
    first_name: string = "";
    last_name: string = "";
    ip: string = "";
    latitude: string = "";
    longitude: string = "";
    created_at: string = "";
    updated_at: string = "";

}