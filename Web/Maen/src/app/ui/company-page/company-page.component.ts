import { Component, OnInit, inject } from '@angular/core';
import { Company } from '../../models/company/company-response.interface';
import { ActivatedRoute } from '@angular/router';
import { CompanyService } from '../../services/company.service';

@Component({
  selector: 'app-company-page',
  templateUrl: './company-page.component.html',
  styleUrl: './company-page.component.css'
})
export class CompanyPageComponent implements OnInit{
  companyList!: Company[];
  route: ActivatedRoute = inject(ActivatedRoute);
  page = 0;
  count= 0;
  pageSize= 0;
  constructor(private companyService: CompanyService){

  }
  ngOnInit(): void {
    this.loadNewCompanyPage();
  }
  loadNewCompanyPage(){
    this.companyService.getAll(this.page).subscribe((resp)=>{
      this.companyList = resp.content;
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
