import 'dart:convert';

import 'package:flutter_application_maen/models/response/get_perfile_details.dart';
import 'package:flutter_application_maen/repositories/perfile/perfile_details_repository.dart';
import 'package:http/http.dart';
import 'package:shared_preferences/shared_preferences.dart';

abstract class PerfileRepositoryImpl extends PerfileRepository {
  final Client _httpClient = Client();
  final Future<SharedPreferences> _prefs = SharedPreferences.getInstance();

  @override
  Future<UserMaenDetailsResponse> fetchUserDetails() async {
    final SharedPreferences prefs = await _prefs;
    final token = prefs.getString('TOKEN');
    final response = await _httpClient.get(
      Uri.parse("http://10.0.2.2:8080/perfile/"),
      headers: <String, String>{
        'Content-Type': 'application/json',
        'Authorization': 'Bearer $token'
      },
    );
    if (response.statusCode == 200) {
      return UserMaenDetailsResponse.fromJson(json.decode(response.body));
    } else {
      throw Exception('Failed to load perfile');
    }
  }
}
