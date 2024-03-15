import 'package:bloc/bloc.dart';
import 'package:flutter_application_maen/models/response/get_all_products.dart';
import 'package:flutter_application_maen/repositories/product/product_repository.dart';
import 'package:meta/meta.dart';

part 'product_event.dart';
part 'product_state.dart';

class ProductBloc extends Bloc<ProductEvent, ProductState> {
  final ProductRepository repository;
  ProductBloc(this.repository) : super(ProductInitial()) {
    on<ProductFetchList>(_onProductFetchList);
  }
  void _onProductFetchList(
      ProductFetchList event, Emitter<ProductState> emit) async {
    try {
      final productList = await repository.fetchProducts();
      emit(ProductFetchSuccess(productList));
      return;
    } on Exception catch (e) {
      emit(ProductFetchError(e.toString()));
    }
  }
}
