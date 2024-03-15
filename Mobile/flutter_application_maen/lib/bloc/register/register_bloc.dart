import 'package:bloc/bloc.dart';
import 'package:flutter_application_maen/models/dto/add_user_maen_dto.dart';
import 'package:flutter_application_maen/models/response/login_response.dart';
import 'package:flutter_application_maen/repositories/auth/auth_repository.dart';
import 'package:meta/meta.dart';
import 'package:shared_preferences/shared_preferences.dart';

part 'register_event.dart';
part 'register_state.dart';

class RegisterBloc extends Bloc<RegisterEvent, RegisterState> {
  final AuthRepository authRepository;
  
  final Future<SharedPreferences> _prefs = SharedPreferences.getInstance();
  RegisterBloc(this.authRepository) : super(RegisterInitial()) {
    on<DoRegisterEvent>(_doRegister);
  }
  void _doRegister(DoRegisterEvent event, Emitter<RegisterState> emit) async{
    final SharedPreferences prefs = await _prefs;

    try{
      final AddUserMaenDto register = AddUserMaenDto(
        username: event.username,
        password: event.password,
        verifyPassword: event.verifyPassword,
        email: event.email,
        name: event.name,
        numMemberOfFamily: event.numMemberOfFamily,
        salary: event.salary
      );
      final response= await authRepository.registerUserMaen(register);
      prefs.setString('token', response.token!);
      prefs.setString('username', response.username!);

      emit(DoRegisterSuccess(response));
    } on Exception catch(e){
      emit(DoRegisterErrror(e.toString()));
    }
  }
}
