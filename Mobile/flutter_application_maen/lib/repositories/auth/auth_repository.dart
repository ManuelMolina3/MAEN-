import 'package:flutter_application_maen/models/dto/add_user_maen_dto.dart';
import 'package:flutter_application_maen/models/dto/login_dto.dart';
import 'package:flutter_application_maen/models/response/login_response.dart';

abstract class AuthRepository {
  Future<LoginResponse> login(LoginDto loginDto);
  Future registerUserMaen(AddUserMaenDto addUserMaenDto);
}
