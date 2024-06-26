import { Component, OnInit, TemplateRef, inject } from '@angular/core';
import { Contract } from '../../models/contract/contract-response.interface';
import { ContractService } from '../../services/contract.service';
import { ActivatedRoute } from '@angular/router';
import { AddContractDTO, EditContractDTO } from '../../models/contract/add-contract-dto.interface';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Company } from '../../models/company/company-response.interface';
import { CompanyService } from '../../services/company.service';

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
  priceEnergy: number = 0;
  discountEnergy: number = 0;
  pricePower: number = 0;
  priceEquipment: number = 0;
  taxes: number = 0;
  nameCompany: string = '';
  priceEnergyErr: string = '';
  discountEnergyErr: string = '';
  pricePowerErr: string = '';
  priceEquipmentErr: string = '';
  taxesErr: string = '';
  nameCompanyErr: string = '';
  companyList!: Company[];
  idCompany: string = '';
  showModal: boolean = false;
  editingContract: Contract | null =null;
  deleteContract: Contract | null =null;
  showDeleteModal: boolean = false;
  constructor(private contractService: ContractService, private modalService: NgbModal, private companyService: CompanyService){

  }
  ngOnInit(): void {
    this.loadNewContractPage();
  }
  open(content: TemplateRef<any>) {
    this.modalService.open(content);
  }
  capturar(nameCompany: any){
    this.idCompany = nameCompany;
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
  getAllCompanies(){
    this.companyService.getAllCompanies().subscribe((resp)=>{
      this.companyList = resp;
    });
  }

saveCreatedContract(){
  let newContract: AddContractDTO = new AddContractDTO(
    this.priceEnergy,
    this.discountEnergy,
    this.pricePower,
    this.priceEquipment,
    this.taxes,
    this.nameCompany
  );
  this.contractService.createNewContract(newContract).subscribe({
    next: (data)=>{
      window.location.href = 'http://localhost:4200/product/';
    },
    error: (err)=>{
      if((err.status = 404)){
        let badReqs = err;
        let errores = badReqs.error.body.fields_errors;
        errores.forEach((error: {field:any; message:any})=>{
          switch (error.field){
            case 'priceEnergy':
              this.priceEnergyErr = error.message;
            break;
            case 'discountEnergy':
              this.discountEnergyErr = error.message;
            break;
            case 'pricePower':
              this.pricePowerErr = error.message;
            break;
            case 'priceEquipment':
              this.priceEquipmentErr = error.message;
            break;
            case 'taxes':
              this.taxesErr = error.message;
            break;
            case 'nameCompany':
              this.nameCompanyErr = error.message;
            break;
          }
        });
      }
    },
  });
}
editContract(): void{
  if(this.editingContract){
    const updateContract : EditContractDTO = {
      priceEnergy: this.editingContract.priceEnergy,
      discountEnergy: this.editingContract.discountEnergy,
      pricePower: this.editingContract.pricePower,
      priceEquipment: this.editingContract.priceEquipment,
      taxes: this.editingContract.taxes

    };
    this.contractService.editContract(this.editingContract.id, updateContract).subscribe(
      editedContract =>{
        const contractIndex = this.contractList.findIndex(p => p.id === editedContract.id);
        if(contractIndex !== -1){
          this.contractList[contractIndex] = editedContract;
        }
        this.closeModal();
      },
      error =>{
        console.error('There was an error!', error);
      }
    )
  }
}
editThisContract(contractEdit: Contract): void{
  this.editingContract = {...contractEdit};
  this.showModal= true;
}
closeModal(): void {
  this.showModal = false;
  this.editingContract = null;
}
deleteCon(){
  if(this.deleteContract){
    this.contractService.deleteContract(this.deleteContract.id).subscribe(
      () =>{
        this.contractList = this.contractList.filter(c => c.id === this.deleteContract!.id);
        this.showDeleteModal= false;
      },
      error =>{
        console.error('There was an error!', error);
      }
    )
  }
}
deleteThisContract(contDelete: Contract): void{
  this.deleteContract = contDelete;
  this.showDeleteModal = true;
}
cancelDeleteModal(){
  this.showDeleteModal = false;
}


}
