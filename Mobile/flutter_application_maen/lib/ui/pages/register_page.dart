import 'package:flutter/material.dart';
import 'package:flutter_application_maen/bloc/register/register_bloc.dart';
import 'package:flutter_application_maen/repositories/auth/auth_repository.dart';
import 'package:flutter_application_maen/repositories/auth/auth_repository_impl.dart';
import 'package:flutter_application_maen/ui/pages/home_page.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:google_fonts/google_fonts.dart';

class RegisterPage extends StatefulWidget {
  const RegisterPage({super.key});

  @override
  State<RegisterPage> createState() => _RegisterPageState();
}

class _RegisterPageState extends State<RegisterPage> {
  final _fromRegister = GlobalKey<FormState>();
  final userTextController = TextEditingController();
  final passTextController = TextEditingController();
  final veriPassTextController = TextEditingController();
  final emailTextController = TextEditingController();
  final nameTextController = TextEditingController();
  final numMemFamilyTextController = TextEditingController();
  final salaryTextController = TextEditingController();

  late AuthRepository authRepository;
  late RegisterBloc _registerBloc;

  @override
  void initState() {
    authRepository = AuthRespositoryImpl();
    _registerBloc = RegisterBloc(authRepository);
    super.initState();
  }

  @override
  void dispose() {
    userTextController.dispose();
    passTextController.dispose();
    veriPassTextController.dispose();
    emailTextController.dispose();
    nameTextController.dispose();
    numMemFamilyTextController.dispose();
    salaryTextController.dispose();
    _registerBloc.close();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: ListView(
        children: [
          BlocProvider.value(
            value: _registerBloc,
            child: BlocConsumer<RegisterBloc, RegisterState>(
              buildWhen: (previous, state) {
                return state is RegisterInitial ||
                    state is DoRegisterSuccess ||
                    state is DoRegisterErrror;
              },
              builder: (context, state) {
                if (state is DoRegisterSuccess) {
                  return const HomePage();
                } else if (state is DoRegisterErrror) {
                  return const Text('Resgiter Error');
                }
                return Center(child: _buildRegisterForm());
              },
              listener: (BuildContext context, RegisterState state) {},
            ),
          ),
        ],
      ),
    );
  }

  _buildRegisterForm() {
    return Form(
        key: _fromRegister,
        child: Padding(
          padding: const EdgeInsets.all(8.0),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            mainAxisSize: MainAxisSize.min,
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Image.asset("assets/logoApp/logoAppFirst.png"),
              const SizedBox(
                height: 20,
              ),
              TextFormField(
                controller: userTextController,
                decoration: const InputDecoration(
                    border: OutlineInputBorder(), labelText: 'Username'),
                validator: (value) {
                  if (value == null || value.isEmpty) {
                    return 'Please enter your username';
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
              TextFormField(
                controller: veriPassTextController,
                obscureText: true,
                decoration: const InputDecoration(
                    border: OutlineInputBorder(), labelText: 'Verify password'),
                validator: (value) {
                  if (value == null || value.isEmpty) {
                    return 'Please enter your password again';
                  }
                  return null;
                },
              ),
              const SizedBox(
                height: 20,
              ),
              TextFormField(
                controller: emailTextController,
                decoration: const InputDecoration(
                    border: OutlineInputBorder(), labelText: 'Email'),
                validator: (value) {
                  if (value == null || value.isEmpty) {
                    return 'Please enter your email';
                  }
                  return null;
                },
              ),
              const SizedBox(
                height: 20,
              ),
              TextFormField(
                controller: nameTextController,
                decoration: const InputDecoration(
                    border: OutlineInputBorder(), labelText: 'Name'),
                validator: (value) {
                  if (value == null || value.isEmpty) {
                    return 'Please enter your name';
                  }
                  return null;
                },
              ),
              const SizedBox(
                height: 20,
              ),
              TextFormField(
                controller: numMemFamilyTextController,
                decoration: const InputDecoration(
                    border: OutlineInputBorder(),
                    labelText: 'Number of members in the family'),
                validator: (value) {
                  if (value == null || value.isEmpty) {
                    return 'Please enter your number of members in the family';
                  }
                  return null;
                },
              ),
              const SizedBox(
                height: 20,
              ),
              TextFormField(
                controller: salaryTextController,
                decoration: const InputDecoration(
                    border: OutlineInputBorder(), labelText: 'Salary'),
                validator: (value) {
                  if (value == null || value.isEmpty) {
                    return 'Please enter your salary';
                  }
                  return null;
                },
              ),
              const SizedBox(
                height: 20,
              ),
              Center(
                child: SizedBox(
                    height: 40,
                    width: 400,
                    child: ElevatedButton(
                        style: ElevatedButton.styleFrom(
                            backgroundColor: Colors.tealAccent[400],
                            shadowColor: Colors.transparent,
                            shape: const RoundedRectangleBorder(
                                borderRadius:
                                    BorderRadius.all(Radius.circular(15)))),
                        child: Text('Register',
                            style: GoogleFonts.openSans(
                                textStyle: const TextStyle(
                                    color: Colors.white,
                                    fontSize: 20,
                                    fontWeight: FontWeight.w600))),
                        onPressed: () {
                          if (_fromRegister.currentState!.validate()) {
                            _registerBloc.add(DoRegisterEvent(
                                userTextController.text,
                                passTextController.text,
                                veriPassTextController.text,
                                emailTextController.text,
                                nameTextController.text,
                                int.parse(numMemFamilyTextController.text),
                                double.parse(salaryTextController.text)));
                          }
                        })),
              )
            ],
          ),
        ));
  }
}
