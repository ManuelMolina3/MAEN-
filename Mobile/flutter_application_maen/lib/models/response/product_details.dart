class ProductDetailsDto {
  String? id;
  String? productName;
  String? productImage;
  String? productBrand;
  double? price;
  double? priceKg;
  int? taxes;
  String? superMarketName;
  String? superMarketImage;

  ProductDetailsDto(
      {this.id,
      this.productName,
      this.productImage,
      this.productBrand,
      this.price,
      this.priceKg,
      this.taxes,
      this.superMarketName,
      this.superMarketImage});

  ProductDetailsDto.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    productName = json['productName'];
    productImage = json['productImage'];
    productBrand = json['productBrand'];
    price = json['price'];
    priceKg = json['priceKg'];
    taxes = json['taxes'];
    superMarketName = json['superMarketName'];
    superMarketImage = json['superMarketImage'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['id'] = this.id;
    data['productName'] = this.productName;
    data['productImage'] = this.productImage;
    data['productBrand'] = this.productBrand;
    data['price'] = this.price;
    data['priceKg'] = this.priceKg;
    data['taxes'] = this.taxes;
    data['superMarketName'] = this.superMarketName;
    data['superMarketImage'] = this.superMarketImage;
    return data;
  }
}
