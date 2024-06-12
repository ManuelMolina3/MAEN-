export class AddCompanyDTO {
    name:     string;
    logotype: string;
    constructor(name: string, logotype: string){
        this.name = name;
        this.logotype = logotype;
    }
}
