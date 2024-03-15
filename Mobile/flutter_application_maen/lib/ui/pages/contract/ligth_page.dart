import 'package:flutter/material.dart';
import 'package:flutter_application_maen/bloc/contract/contract_bloc.dart';
import 'package:flutter_application_maen/repositories/contract/contract_repository.dart';
import 'package:flutter_application_maen/repositories/contract/contract_repository_impl.dart';
import 'package:flutter_application_maen/ui/widget/ligth/contact_card.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

class LigthPage extends StatefulWidget {
  const LigthPage({super.key});

  @override
  State<LigthPage> createState() => _LigthPageState();
}

class _LigthPageState extends State<LigthPage> {
  late ContractRepository repository;
  late ContractBloc _contractBloc;
  String title = 'Ligth';

  @override
  void initState() {
    repository = ContractRepositoryImpl();
    _contractBloc = ContractBloc(repository)
      ..add(ContractFetchList('contract'));
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Image.asset("assets/logoApp/logoAppIntro.png"),
      ),
      body: BlocProvider.value(
        value: _contractBloc,
        child: _contractList(),
      ),
    );
  }

  Widget _contractList() {
    return BlocBuilder(
        bloc: _contractBloc,
        builder: (context, state) {
          if (state is ContractFetchSuccess) {
            if (state.ContractDtoList.isNotEmpty) {
              return GridView.builder(
                gridDelegate: const SliverGridDelegateWithMaxCrossAxisExtent(
                  maxCrossAxisExtent: 200,
                  mainAxisSpacing: 5.0,
                  crossAxisSpacing: 5.0,
                  childAspectRatio: 0.7,
                ),
                itemCount: state.ContractDtoList.length,
                itemBuilder: (context, index) {
                  final contract = state.ContractDtoList[index];
                  return ContarctCard(contract: contract, index: index);
                },
              );
            } else {
              return const Center(child: Text('No hay productos disponibles.'));
            }
          } else if (state is ContractFetchError) {
            return Text(state.errorMessage);
          }
          return const Center(child: CircularProgressIndicator());
        });
  }
}
