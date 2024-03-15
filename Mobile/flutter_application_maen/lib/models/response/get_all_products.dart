class GetProductDto {
  List<ProductDto>? content;
  int? size;
  int? totalElements;
  int? pageNumber;
  bool? first;
  bool? last;

  GetProductDto(
      {this.content,
      this.size,
      this.totalElements,
      this.pageNumber,
      this.first,
      this.last});

  GetProductDto.fromJson(Map<String, dynamic> json) {
    if (json['content'] != null) {
      content = <ProductDto>[];
      json['content'].forEach((v) {
        content!.add(new ProductDto.fromJson(v));
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

class ProductDto {
  String? id;
  String? productName;
  String? productImage;
  String? productBrand;
  double? price;
  String? superMarketName;
  String? superMarketImage;

  ProductDto(
      {this.id,
      this.productName,
      this.productImage,
      this.productBrand,
      this.price,
      this.superMarketName,
      this.superMarketImage});

  ProductDto.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    productName = json['productName'];
    productImage = json['productImage'];
    productBrand = json['productBrand'];
    price = json['price'];
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
    data['superMarketName'] = this.superMarketName;
    data['superMarketImage'] = this.superMarketImage;
    return data;
  }
}
