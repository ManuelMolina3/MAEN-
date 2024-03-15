import 'package:flutter/material.dart';
import 'package:flutter_application_maen/bloc/product/product_bloc.dart';
import 'package:flutter_application_maen/repositories/product/product_repository.dart';
import 'package:flutter_application_maen/repositories/product/product_repository_impl.dart';
import 'package:flutter_application_maen/ui/widget/product/product_card.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

class SuperMarketPage extends StatefulWidget {
  const SuperMarketPage({super.key});

  @override
  State<SuperMarketPage> createState() => _SuperMarketPageState();
}

class _SuperMarketPageState extends State<SuperMarketPage> {
  late ProductRepository repository;
  late ProductBloc _productBloc;

  String title = 'Supermarket';
  @override
  void initState() {
    repository = ProductRepositoryImpl();
    _productBloc = ProductBloc(repository)..add(ProductFetchList('product'));
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Image.asset("assets/logoApp/logoAppIntro.png"),
      ),
      body: BlocProvider.value(
        value: _productBloc,
        child: _productList(),
      ),
    );
  }

  Widget _productList() {
    return BlocBuilder(
        bloc: _productBloc,
        builder: (context, state) {
          if (state is ProductFetchSuccess) {
            if (state.productDtoList.isNotEmpty) {
              return GridView.builder(
                gridDelegate: const SliverGridDelegateWithMaxCrossAxisExtent(
                  maxCrossAxisExtent: 200,
                  mainAxisSpacing: 5.0,
                  crossAxisSpacing: 5.0,
                  childAspectRatio: 0.7,
                ),
                itemCount: state.productDtoList.length,
                itemBuilder: (context, index) {
                  final product = state.productDtoList[index];
                  return ProductCard(product: product, index: index);
                },
              );
            } else {
              return const Center(child: Text('No hay productos disponibles.'));
            }
          } else if (state is ProductFetchError) {
            return Text(state.errorMessage);
          }
          return const CircularProgressIndicator();
        });
  }
}
