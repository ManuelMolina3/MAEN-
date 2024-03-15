import 'package:flutter_application_maen/models/response/get_perfile_details.dart';

abstract class PerfileRepository {
  Future<UserMaenDetailsResponse> fetchUserDetails();
}
