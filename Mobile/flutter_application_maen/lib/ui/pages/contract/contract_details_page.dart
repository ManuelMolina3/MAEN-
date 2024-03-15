import 'package:flutter/material.dart';
import 'package:flutter_application_maen/bloc/contract_details/contract_details_bloc.dart';
import 'package:flutter_application_maen/repositories/contract/contract_repository.dart';
import 'package:flutter_application_maen/repositories/contract/contract_repository_impl.dart';
import 'package:flutter_application_maen/ui/widget/ligth/contract_details_card.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

class ContractDetailsPage extends StatefulWidget {
  final String id;
  const ContractDetailsPage({super.key, required this.id});

  @override
  State<ContractDetailsPage> createState() => _ContractDetailsPageState();
}

class _ContractDetailsPageState extends State<ContractDetailsPage> {
  late ContractRepository repository;

  @override
  void initState(){
    super.initState();
    repository = ContractRepositoryImpl();
  }

  @override
  Widget build(BuildContext context) {
        return BlocProvider(
      create: (context) =>
          ContractDetailsBloc(repository)..add(DoContractViewDetails(widget.id)),
      child: Scaffold(
          appBar: AppBar(
            title: Image.asset("assets/logoApp/logoAppIntro.png"),
          ),
          body: BlocBuilder<ContractDetailsBloc, ContractDetailsState>(
              builder: (BuildContext context, ContractDetailsState state) {
            if (state is ContractDetailsFetchSuccess) {
              return Center(
                  child: ContractDetails(contract: state.contractDto));
            } else if (state is ContractDetailsFetchError) {
              return Text(state.errorMessage);
            }
            return const CircularProgressIndicator.adaptive();
          })),
    );
  }
}
