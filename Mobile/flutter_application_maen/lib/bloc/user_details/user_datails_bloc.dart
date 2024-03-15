import 'package:bloc/bloc.dart';
import 'package:flutter_application_maen/models/response/get_perfile_details.dart';
import 'package:flutter_application_maen/repositories/perfile/perfile_details_repository.dart';
import 'package:meta/meta.dart';

part 'user_datails_event.dart';
part 'user_datails_state.dart';

class UserDatailsBloc extends Bloc<UserDatailsEvent, UserDatailsState> {
  final PerfileRepository repository;
  UserDatailsBloc(this.repository) : super(UserDatailsInitial()) {
    on<DoUserViewDetails>(_onUserdetails);
  }
  void _onUserdetails(
      DoUserViewDetails event, Emitter<UserDatailsState> emit) async {
    try {
      final userDetails = await repository.fetchUserDetails();
      emit(UserMaenDetailsFetchSuccess(userDetails));
      return;
    } on Exception catch (e) {
      emit(UserMaenDetailsFetchError(e.toString()));
    }
  }
}
