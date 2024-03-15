import 'package:flutter_application_maen/models/response/get_electricity_response.dart';

abstract class ContractRepository {
  Future<List<ContractDto>> fecthContracts();
  Future<ContractDto> fetchContractDetails(String id);
  Future<List<ContractDto>> fetchContractByCompany();
}
