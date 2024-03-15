import 'package:flutter/material.dart';
import 'package:flutter_application_maen/bloc/product_details/product_details_bloc.dart';
import 'package:flutter_application_maen/repositories/product/product_repository.dart';
import 'package:flutter_application_maen/repositories/product/product_repository_impl.dart';
import 'package:flutter_application_maen/ui/widget/product/product_details_card.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

class ProductDetailsPage extends StatefulWidget {
  final String id;
  const ProductDetailsPage({super.key, required this.id});

  @override
  State<ProductDetailsPage> createState() => _ProductDetailsPageState();
}

class _ProductDetailsPageState extends State<ProductDetailsPage> {
  late ProductRepository repository;

  @override
  void initState() {
    super.initState();
    repository = ProductRepositoryImpl();
  }

  @override
  Widget build(BuildContext context) {
    return BlocProvider(
      create: (context) =>
          ProductDetailsBloc(repository)..add(DoProductViewDetails(widget.id)),
      child: Scaffold(
          appBar: AppBar(
            title: Image.asset("assets/logoApp/logoAppIntro.png"),
          ),
          body: BlocBuilder<ProductDetailsBloc, ProductDetailsState>(
              builder: (BuildContext context, ProductDetailsState state) {
            if (state is ProductDetailsFetchSuccess) {
              return Center(
                  child: ProductDetailsCard(product: state.productDetailsDto));
            } else if (state is ProductDetailsFetchError) {
              return Text(state.errorMessage);
            }
            return const CircularProgressIndicator.adaptive();
          })),
    );
  }
}
