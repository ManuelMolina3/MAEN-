import { Component, OnInit} from '@angular/core';
import { ProductService } from '../../services/product.service';
import { Product, ProductResponse} from '../../models/product/product-response.interface';

@Component({
  selector: 'app-product-page',
  templateUrl: './product-page.component.html',
  styleUrl: './product-page.component.css'
})
export class ProductPageComponent implements OnInit{
  productList!: Product[];
  page = 0;
  count= 0;
  pageSize= 0;
  constructor(private productService: ProductService){

  }
  ngOnInit(): void {
    this.loadNewProductPage();
  }
  
  loadNewProductPage(){
    this.productService.getAll(this.page).subscribe((resp)=>{
      console.log(resp);
      this.productList = resp.content;
      this.pageSize= resp.size;
      if (resp.totalElements > 1000) {
        this.count = 10000;
      } else {
        this.count = resp.totalElements;
      }
      window.scrollTo({ top: 0, behavior: 'smooth' });
    });
  }
  
}
