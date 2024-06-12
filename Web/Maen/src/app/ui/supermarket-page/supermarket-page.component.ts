import { Component, OnInit, TemplateRef, inject } from '@angular/core';
import { Supermarket } from '../../models/supermarket/supermarket-response.interface';
import { ActivatedRoute } from '@angular/router';
import { SupermarketService } from '../../services/supermarket.service';
import { AddSupermarketDTO } from '../../models/supermarket/add-supermarket-dto.interface';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-supermarket-page',
  templateUrl: './supermarket-page.component.html',
  styleUrl: './supermarket-page.component.css'
})
export class SupermarketPageComponent implements OnInit{
  supermarketList!: Supermarket[];
  route: ActivatedRoute = inject(ActivatedRoute);
  page = 0;
  count= 0;
  pageSize= 0;
  name: string = '';
  logotype: string = '';
  nameErr: string = '';
  logotypeErr: string = '';
  constructor(private supermarketService: SupermarketService, private modalService: NgbModal){

  }
  ngOnInit(): void {
    this.loadNewSupermarketPage();
  }
  open(content: TemplateRef<any>) {
    this.modalService.open(content);
  }
  loadNewSupermarketPage(){
    this.supermarketService.getAll(this.page).subscribe((resp)=>{
      this.supermarketList = resp.content;
      this.pageSize= resp.size;
      if(resp.totalElements> 1000){
        this.count = 10000;
      } else{
        this.count = resp.totalElements;
      }
      window.scrollTo({top: 0, behavior: 'smooth'});
    })
  }
  saveCreatedSupermarket(){
    let newSupermarket : AddSupermarketDTO = new AddSupermarketDTO(
      this.name,
      this.logotype
    );
    this.supermarketService.createNewSupermarket(newSupermarket).subscribe({
      next: (data)=>{
        window.location.href = 'http://localhost:4200/supermarket/'
      },error: (err)=>{
        if((err.status = 404)){
          let badReqs = err;
          let errores = badReqs.error.body.fields_errors;
          errores.forEach((error: {field:any; message:any})=>{
            switch (error.field){
              case 'name':
                this.nameErr = error.message;
              break;
              case 'logotype':
                this.logotypeErr = error.message;
              break;
            }
          });
        }
      },
  });
  }
}
