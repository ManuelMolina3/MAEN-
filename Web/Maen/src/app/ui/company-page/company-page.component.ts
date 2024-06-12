import { Component, OnInit, TemplateRef, inject } from '@angular/core';
import { Company } from '../../models/company/company-response.interface';
import { ActivatedRoute } from '@angular/router';
import { CompanyService } from '../../services/company.service';
import { AddCompanyDTO } from '../../models/company/add-company-dto.interface';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

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
  name: string = '';
  logotype: string = '';
  nameErr: string = '';
  logotypeErr: string = '';
  constructor(private companyService: CompanyService, private modalService: NgbModal){

  }
  ngOnInit(): void {
    this.loadNewCompanyPage();
  }
  open(content: TemplateRef<any>) {
    this.modalService.open(content);
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
  saveCreatedCompany(){
    let newCompany : AddCompanyDTO = new AddCompanyDTO(
      this.name,
      this.logotype
    );
    this.companyService.createNewCompany(newCompany).subscribe({
      next: (data)=>{
        window.location.href = 'http://localhost:4200/company/'
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
