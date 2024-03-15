part of 'contract_details_bloc.dart';

@immutable
sealed class ContractDetailsState {}

final class ContractDetailsInitial extends ContractDetailsState {}

final class ContractDetailsFetchSuccess extends ContractDetailsState{
  final ContractDto contractDto;
  ContractDetailsFetchSuccess(this.contractDto);
}
final class ContractDetailsFetchError extends ContractDetailsState{
  final String errorMessage;
  ContractDetailsFetchError(this.errorMessage);
}