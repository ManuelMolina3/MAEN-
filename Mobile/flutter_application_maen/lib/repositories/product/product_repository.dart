import 'package:flutter_application_maen/models/response/get_all_products.dart';
import 'package:flutter_application_maen/models/response/product_details.dart';

abstract class ProductRepository {
  Future<List<ProductDto>> fetchProducts();
  Future<ProductDetailsDto> fetchProductDetails(String id);
  Future<List<ProductDto>> fetchProductsByName(String name);
}
