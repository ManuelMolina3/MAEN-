part of '../register/register_bloc.dart';

@immutable
sealed class RegisterEvent {

}

class DoRegisterEvent extends RegisterEvent{
  final String username;
  final String password;
  final String verifyPassword;
  final String email;
  final String name;
  final int numMemberOfFamily;
  final double salary; 

  DoRegisterEvent(this.username, this.password, this.verifyPassword, this.email, this.name, this.numMemberOfFamily, this.salary);

}