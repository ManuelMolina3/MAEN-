import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginPageComponent } from './ui/login-page/login-page.component';
import { ProductsPageComponent } from './main/products-page/products-page.component';
import { SupermarketsPageComponent } from './main/supermarkets-page/supermarkets-page.component';
import { CompaniesPageComponent } from './main/companies-page/companies-page.component';
import { ContractsPageComponent } from './main/contracts-page/contracts-page.component';

const routes: Routes = [

  { path: 'login', component: LoginPageComponent},
  { path: 'product', component: ProductsPageComponent},
  { path: 'supermarket', component: SupermarketsPageComponent},
  { path: 'company', component: CompaniesPageComponent},
  { path: 'contract', component: ContractsPageComponent},
  { path: '', pathMatch: 'full', redirectTo: '/login' },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
