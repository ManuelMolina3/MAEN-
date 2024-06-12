import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginPageComponent } from './ui/login-page/login-page.component';
import { ProductPageComponent } from './ui/product-page/product-page.component';
import { SupermarketPageComponent } from './ui/supermarket-page/supermarket-page.component';
import { CompanyPageComponent } from './ui/company-page/company-page.component';
import { ContractPageComponent } from './ui/contract-page/contract-page.component';

const routes: Routes = [

  { path: 'login', component: LoginPageComponent},
  { path: 'product', component: ProductPageComponent},
  { path: 'supermarket', component: SupermarketPageComponent},
  { path: 'company', component: CompanyPageComponent},
  { path: 'contract', component: ContractPageComponent},
  { path: '', pathMatch: 'full', redirectTo: '/login' },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
