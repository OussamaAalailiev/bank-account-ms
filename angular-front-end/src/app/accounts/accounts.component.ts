import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-accounts',
  templateUrl: './accounts.component.html',
  styleUrl: './accounts.component.css'
})
export class AccountsComponent implements OnInit {

    accounts: any;

    constructor(private http: HttpClient) {}

    ngOnInit() {
      this.http.get("http://localhost:8888/ACCOUNT-SERVICE/accounts")
        .subscribe({
          next: (data: any) => {
            console.log(data);
            this.accounts = data;
          },
          error: (err: any) => {
            console.error(err);
          }
        })
    }
}
