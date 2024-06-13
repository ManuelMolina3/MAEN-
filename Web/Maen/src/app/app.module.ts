import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { LoginPageComponent } from './ui/login-page/login-page.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { ProductPageComponent } from './ui/product-page/product-page.component';
import { SupermarketPageComponent } from './ui/supermarket-page/supermarket-page.component';
import { CompanyPageComponent } from './ui/company-page/company-page.component';
import { ContractPageComponent } from './ui/contract-page/contract-page.component';
import { ProductsPageComponent } from './main/products-page/products-page.component';
import { SupermarketsPageComponent } from './main/supermarkets-page/supermarkets-page.component';
import { ContractsPageComponent } from './main/contracts-page/contracts-page.component';
import { CompaniesPageComponent } from './main/companies-page/companies-page.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginPageComponent,
    NavbarComponent,
    ProductPageComponent,
    SupermarketPageComponent,
    CompanyPageComponent,
    ContractPageComponent,
    ProductsPageComponent,
    SupermarketsPageComponent,
    ContractsPageComponent,
    CompaniesPageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
 }
