import { Component, OnInit, inject } from '@angular/core';
import { Contract } from '../../models/contract/contract-response.interface';
import { ContractService } from '../../services/contract.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-contract-page',
  templateUrl: './contract-page.component.html',
  styleUrl: './contract-page.component.css'
})
export class ContractPageComponent implements OnInit{
  contractList!: Contract[];
  route: ActivatedRoute = inject(ActivatedRoute);
  page = 0;
  count= 0;
  pageSize= 0;
  constructor(private contractService: ContractService){

  }
  ngOnInit(): void {
    this.loadNewContractPage();
  }
  loadNewContractPage(){
    this.contractService.getAll(this.page).subscribe((resp)=>{
      this.contractList = resp.content;
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
