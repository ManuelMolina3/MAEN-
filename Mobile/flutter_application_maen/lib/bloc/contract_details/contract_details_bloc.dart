import 'package:bloc/bloc.dart';
import 'package:flutter_application_maen/models/response/get_electricity_response.dart';
import 'package:flutter_application_maen/repositories/contract/contract_repository.dart';
import 'package:meta/meta.dart';

part 'contract_details_event.dart';
part 'contract_details_state.dart';

class ContractDetailsBloc extends Bloc<ContractDetailsEvent, ContractDetailsState> {
  final ContractRepository repository;
  ContractDetailsBloc(this.repository) : super(ContractDetailsInitial()) {
    on<DoContractViewDetails>(_onContractViewDetails);
  }
  void _onContractViewDetails(DoContractViewDetails event, Emitter<ContractDetailsState> emit)async{
    try{
      final contractDetails= await repository.fetchContractDetails(event.contractId);
      emit(ContractDetailsFetchSuccess(contractDetails));
    }on Exception catch(e){
      emit(ContractDetailsFetchError(e.toString()));
    }
  }
}
