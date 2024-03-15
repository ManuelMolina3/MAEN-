import 'dart:convert';

import 'package:flutter_application_maen/models/response/get_all_products.dart';
import 'package:flutter_application_maen/models/response/product_details.dart';
import 'package:flutter_application_maen/repositories/product/product_repository.dart';
import 'package:http/http.dart';
import 'package:shared_preferences/shared_preferences.dart';

class ProductRepositoryImpl extends ProductRepository {
  final Client _httpClient = Client();
  final Future<SharedPreferences> _prefs = SharedPreferences.getInstance();

  @override
  Future<ProductDetailsDto> fetchProductDetails(String id) async {
    final SharedPreferences prefs= await _prefs;
    final token= prefs.getString('TOKEN');
    final response= await _httpClient.get(
      Uri.parse("http://10.0.2.2:8080/product/$id"),
      headers: <String, String>{
        'Content-Type': 'application/json',
        'Authorization': 'Bearer $token'
      },);
      if( response.statusCode == 200){
        return ProductDetailsDto.fromJson(json.decode(response.body));
      }else{
        throw Exception('Failed to load product details');
      }
  }

  @override
  Future<List<ProductDto>> fetchProducts() async {
    final SharedPreferences prefs = await _prefs;

    final token = prefs.getString('TOKEN');
    final response = await _httpClient.get(
      Uri.parse("http://10.0.2.2:8080/product/"),
      headers: <String, String>{
        'Content-Type': 'application/json',
        'Authorization': 'Bearer $token'
      },
    );

    if (response.statusCode == 200) {
      return GetProductDto.fromJson(json.decode(response.body)).content!;
    } else {
      throw Exception('Failed to load products');
    }
  }

  @override
  Future<List<ProductDto>> fetchProductsByName(String name) async {
    // TODO: implement fetchProductsByName
    throw UnimplementedError();
  }
}
