part of 'contract_bloc.dart';

@immutable
sealed class ContractEvent {}

class ContractFetchList extends ContractEvent {
  final String contract;
  ContractFetchList(this.contract);
}
