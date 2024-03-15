part of 'user_datails_bloc.dart';

@immutable
sealed class UserDatailsState {}

final class UserDatailsInitial extends UserDatailsState {}

final class UserMaenDetailsFetchSuccess extends UserDatailsState {
  final UserMaenDetailsResponse userDetailsResponse;
  UserMaenDetailsFetchSuccess(this.userDetailsResponse);
}

final class UserMaenDetailsFetchError extends UserDatailsState {
  final String errorMessage;
  UserMaenDetailsFetchError(this.errorMessage);
}
