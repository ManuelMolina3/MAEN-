part of 'contract_bloc.dart';

@immutable
sealed class ContractState {}

final class ContractInitial extends ContractState {}

final class ContractFetchSuccess extends ContractState {
  final List<ContractDto> ContractDtoList;
  ContractFetchSuccess(this.ContractDtoList);
}

final class ContractFetchError extends ContractState {
  final String errorMessage;
  ContractFetchError(this.errorMessage);
}
