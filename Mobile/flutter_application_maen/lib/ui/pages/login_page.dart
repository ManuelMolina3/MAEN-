import 'package:flutter/material.dart';
import 'package:flutter_application_maen/bloc/login/login_bloc.dart';
import 'package:flutter_application_maen/repositories/auth/auth_repository.dart';
import 'package:flutter_application_maen/repositories/auth/auth_repository_impl.dart';
import 'package:flutter_application_maen/ui/pages/home_page.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:google_fonts/google_fonts.dart';

class LoginPage extends StatefulWidget {
  const LoginPage({super.key});

  @override
  State<LoginPage> createState() => __LoginPageState();
}

class __LoginPageState extends State<LoginPage> {
  final _formLogin = GlobalKey<FormState>();
  final userTextController = TextEditingController(text: '');
  final passTextController = TextEditingController(text: '');

  late AuthRepository authRepository;
  late LoginBloc _loginBloc;

  @override
  void initState() {
    authRepository = AuthRespositoryImpl();
    _loginBloc = LoginBloc(authRepository);

    super.initState();
  }

  @override
  void dispose() {
    userTextController.dispose();
    passTextController.dispose();
    _loginBloc.close();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: ListView(
        children: [
          BlocProvider.value(
              value: _loginBloc,
              child: Padding(
                  padding: const EdgeInsets.all(20.0),
                  child: BlocConsumer<LoginBloc, LoginState>(
                    buildWhen: (context, state) {
                      return state is LoginInitial ||
                          state is DoLoginError ||
                          state is DoLoginLoading;
                    },
                    builder: (context, state) {
                      if (state is DoLoginError) {
                        return const Text('Login error');
                      } else if (state is DoLoginLoading) {
                        return const Center(child: CircularProgressIndicator());
                      }
                      return Center(child: _buildLoginFrom());
                    },
                    listenWhen: (previous, current) {
                      return current is DoLoginSuccess;
                    },
                    listener: (BuildContext context, LoginState state) {
                      Navigator.push(
                        context,
                        MaterialPageRoute(
                            builder: (context) => const HomePage()),
                      );
                    },
                  ))),
        ],
      ),
    );
  }

  _buildLoginFrom() {
    return Form(
        key: _formLogin,
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          mainAxisSize: MainAxisSize.min,
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Image.asset("assets/logoApp/logoAppFirst.png"),
            const SizedBox(
              height: 100,
            ),
            TextFormField(
              controller: userTextController,
              decoration: const InputDecoration(
                  border: OutlineInputBorder(), labelText: 'Username'),
              validator: (value) {
                if (value == null || value.isEmpty) {
                  return 'Please enter some text';
                }
                return null;
              },
            ),
            const SizedBox(
              height: 20,
            ),
            TextFormField(
              controller: passTextController,
              obscureText: true,
              decoration: const InputDecoration(
                  border: OutlineInputBorder(), labelText: 'Password'),
              validator: (value) {
                if (value == null || value.isEmpty) {
                  return 'Please enter your password';
                }
                return null;
              },
            ),
            const SizedBox(
              height: 20,
            ),
            SizedBox(
                height: 40,
                width: 400,
                child: ElevatedButton(
                    style: ElevatedButton.styleFrom(
                        backgroundColor: Colors.tealAccent[400],
                        shadowColor: Colors.transparent,
                        shape: const RoundedRectangleBorder(
                            borderRadius:
                                BorderRadius.all(Radius.circular(15)))),
                    child: Text('Login',
                        style: GoogleFonts.openSans(
                            textStyle: const TextStyle(
                                color: Colors.white,
                                fontSize: 20,
                                fontWeight: FontWeight.w600))),
                    onPressed: () {
                      if (_formLogin.currentState!.validate()) {
                        _loginBloc.add(DoLoginEvent(
                            userTextController.text, passTextController.text));
                      }
                    }))
          ],
        ));
  }
}
