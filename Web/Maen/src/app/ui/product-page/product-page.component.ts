import { Component, OnInit, TemplateRef} from '@angular/core';
import { ProductService } from '../../services/product.service';
import { Product} from '../../models/product/product-response.interface';
import { AddProductDTO } from '../../models/product/add-product-dto.interface';
import { Supermarket } from '../../models/supermarket/supermarket-response.interface';
import { SupermarketService } from '../../services/supermarket.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

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
  name: string = '';
  image: string = '';
  brand: string = '';
  price: number= 0;
  priceKg: number= 0;
  taxes: number= 0;
  category: number= 0;
  supermarketId: string = '';
  namErr: string = '';
  imageErr: string = '';
  brandErr: string = '';
  priceErr: string = '';
  priceKgErr: string = '';
  taxesErr: string = '';
  categoryErr: string = '';
  supermarketIdErr: string = '';
  supermarketList!: Supermarket[];
  idSuper: string = '';
  constructor(private productService: ProductService, private supermarketSevice: SupermarketService, private modalService: NgbModal){

  }
  ngOnInit(): void {
    this.loadNewProductPage();
  }
  open(content: TemplateRef<any>) {
    this.modalService.open(content);
  }
  loadNewProductPage(){
    this.productService.getAll(this.page).subscribe((resp)=>{
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
  findAllSupermarket(){
    this.supermarketSevice.findAll().subscribe((resp)=>{
      this.supermarketList= resp.content;
    });
  }
  capturar(id: any){
    this.idSuper = id;
  }
  saveCreatedProduct(){
    let newProduct: AddProductDTO = new AddProductDTO(
      this.name,
      this.image,
      this.brand,
      this.price,
      this.priceKg,
      this.taxes,
      this.category,
      this.supermarketId
    );
    this.productService.createNewProduct(newProduct).subscribe({
      next: (data)=>{
        window.location.href = 'http://localhost:4200/product/';
      },
      error: (err)=>{
        if((err.status = 404)){
          let badReqs = err;
          let errores = badReqs.error.body.fields_errors;
          errores.forEach((error: {field:any; message:any})=>{
            switch (error.field){
              case 'name':
                this.namErr = error.message;
              break;
              case 'image':
                this.imageErr = error.message;
              break;
              case 'brand':
                this.brandErr = error.message;
              break;
              case 'price':
                this.priceErr = error.message;
              break;
              case 'priceKg':
                this.priceKgErr = error.message;
              break;
              case 'taxes':
                this.taxesErr = error.message;
              break;
              case 'category':
                this.categoryErr = error.message;
              break;
              case 'supermarketId':
                this.supermarketIdErr = error.message;
              break;
            }
          });
        }
      },
    });
  }
  
}
