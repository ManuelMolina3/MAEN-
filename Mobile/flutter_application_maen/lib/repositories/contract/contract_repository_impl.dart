import 'dart:convert';

import 'package:flutter_application_maen/models/response/get_electricity_response.dart';
import 'package:flutter_application_maen/repositories/contract/contract_repository.dart';
import 'package:http/http.dart';
import 'package:shared_preferences/shared_preferences.dart';

class ContractRepositoryImpl extends ContractRepository {
  final Client _httpClient = Client();
  final Future<SharedPreferences> _prefs = SharedPreferences.getInstance();
  @override
  Future<List<ContractDto>> fecthContracts() async {
    final SharedPreferences prefs = await _prefs;
    final token = prefs.getString('TOKEN');
    final response = await _httpClient.get(
      Uri.parse("http://10.0.2.2:8080/contract/"),
      headers: <String, String>{
        'Content-Type': 'application/json',
        'Authorization': 'Bearer $token'
      },
    );
    if (response.statusCode == 200) {
      return ElectricityContractDto.fromJson(json.decode(response.body))
          .content!;
    } else {
      throw Exception('Failed to load contract');
    }
  }

  @override
  Future<List<ContractDto>> fetchContractByCompany() {
    // TODO: implement fetchContractByCompany
    throw UnimplementedError();
  }

  @override
  Future<ContractDto> fetchContractDetails(String id) async{
    final SharedPreferences prefs= await _prefs;
    final token= prefs.getString('TOKEN');
    final response= await _httpClient.get(
      Uri.parse("http://10.0.2.2:8080/contract/$id"),
      headers: <String, String>{
        'Content-Type': 'application/json',
        'Authorization': 'Bearer $token'
      },);
      if(response.statusCode == 200){
        return ContractDto.fromJson(json.decode(response.body));
      }else{
        throw Exception('Failed to load contract details');
      }
  }
}
