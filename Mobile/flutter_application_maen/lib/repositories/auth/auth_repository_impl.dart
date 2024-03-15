import 'dart:convert';

import 'package:http/http.dart';

import 'package:flutter_application_maen/models/dto/add_user_maen_dto.dart';
import 'package:flutter_application_maen/models/dto/login_dto.dart';

import 'package:flutter_application_maen/models/response/login_response.dart';
import 'package:flutter_application_maen/repositories/auth/auth_repository.dart';

class AuthRespositoryImpl extends AuthRepository {
  final Client _httpClient = Client();
  @override
  Future<LoginResponse> login(LoginDto loginDto) async {
    final response =
        await _httpClient.post(Uri.parse("http://10.0.2.2:8080/auth/login"),
            headers: <String, String>{
              'Content-Type': 'application/json',
            },
            body: jsonEncode(loginDto.toJson()));
    if (response.statusCode == 201) {
      return LoginResponse.fromJson(json.decode(response.body));
    } else {
      throw Exception('Failed to do login');
    }
  }

  @override
  Future registerUserMaen(AddUserMaenDto addUserMaenDto) async {
    final response =
        await _httpClient.post(Uri.parse("http://10.0.2.2:8080/auth/register"),
            headers: <String, String>{
              'Content-Type': 'application/json',
            },
            body: jsonEncode(addUserMaenDto.toJson()));
    if (response.statusCode == 201) {
      return LoginResponse.fromJson(json.decode(response.body));
    } else {
      throw Exception('Failed to Register');
    }
  }
}
