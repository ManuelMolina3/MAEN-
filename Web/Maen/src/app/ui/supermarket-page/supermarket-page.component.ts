import { Component, OnInit, inject } from '@angular/core';
import { Supermarket } from '../../models/supermarket/supermarket-response.interface';
import { ActivatedRoute } from '@angular/router';
import { SupermarketService } from '../../services/supermarket.service';

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
  constructor(private supermarketService: SupermarketService){

  }
  ngOnInit(): void {
    this.loadNewSupermarketPage();
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

}
