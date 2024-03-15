part of 'user_datails_bloc.dart';

@immutable
sealed class UserDatailsEvent {}

class DoUserViewDetails extends UserDatailsEvent {
  final String user;
  DoUserViewDetails(this.user);
}
