import 'package:bloc/bloc.dart';
import 'package:flutter_application_maen/models/response/get_electricity_response.dart';
import 'package:flutter_application_maen/repositories/contract/contract_repository.dart';
import 'package:meta/meta.dart';

part 'contract_event.dart';
part 'contract_state.dart';

class ContractBloc extends Bloc<ContractEvent, ContractState> {
  final ContractRepository repository;
  ContractBloc(this.repository) : super(ContractInitial()) {
    on<ContractFetchList>(_onContractFetchList);
  }
  void _onContractFetchList(
      ContractFetchList event, Emitter<ContractState> emit) async {
    try {
      final contractList = await repository.fecthContracts();
      emit(ContractFetchSuccess(contractList));
      return;
    } on Exception catch (e) {
      emit(ContractFetchError(e.toString()));
    }
  }
}
