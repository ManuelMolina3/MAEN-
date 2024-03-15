class ElectricityContractDto {
  List<ContractDto>? content;
  int? size;
  int? totalElements;
  int? pageNumber;
  bool? first;
  bool? last;

  ElectricityContractDto(
      {this.content,
      this.size,
      this.totalElements,
      this.pageNumber,
      this.first,
      this.last});

  ElectricityContractDto.fromJson(Map<String, dynamic> json) {
    if (json['content'] != null) {
      content = <ContractDto>[];
      json['content'].forEach((v) {
        content!.add(new ContractDto.fromJson(v));
      });
    }
    size = json['size'];
    totalElements = json['totalElements'];
    pageNumber = json['pageNumber'];
    first = json['first'];
    last = json['last'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    if (this.content != null) {
      data['content'] = this.content!.map((v) => v.toJson()).toList();
    }
    data['size'] = this.size;
    data['totalElements'] = this.totalElements;
    data['pageNumber'] = this.pageNumber;
    data['first'] = this.first;
    data['last'] = this.last;
    return data;
  }
}

class ContractDto {
  String? id;
  double? priceEnergy;
  double? discountEnergy;
  double? pricePower;
  double? priceEquipment;
  double? taxes;
  String? companyName;
  String? companyLogotype;
  String? companyId;

  ContractDto(
      {this.id,
      this.priceEnergy,
      this.discountEnergy,
      this.pricePower,
      this.priceEquipment,
      this.taxes,
      this.companyName,
      this.companyLogotype,
      this.companyId});

  ContractDto.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    priceEnergy = json['priceEnergy'];
    discountEnergy = json['discountEnergy'];
    pricePower = json['pricePower'];
    priceEquipment = json['priceEquipment'];
    taxes = json['taxes'];
    companyName = json['companyName'];
    companyLogotype = json['companyLogotype'];
    companyId = json['companyId'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['id'] = this.id;
    data['priceEnergy'] = this.priceEnergy;
    data['discountEnergy'] = this.discountEnergy;
    data['pricePower'] = this.pricePower;
    data['priceEquipment'] = this.priceEquipment;
    data['taxes'] = this.taxes;
    data['companyName'] = this.companyName;
    data['companyLogotype'] = this.companyLogotype;
    data['companyId'] = this.companyId;
    return data;
  }
}
