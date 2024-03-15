part of 'contract_details_bloc.dart';

@immutable
sealed class ContractDetailsEvent {}

class DoContractViewDetails extends ContractDetailsEvent{
  final String contractId;
  DoContractViewDetails(this.contractId);
}