import 'package:bloc/bloc.dart';
import 'package:flutter_application_maen/models/response/product_details.dart';
import 'package:flutter_application_maen/repositories/product/product_repository.dart';
import 'package:meta/meta.dart';

part 'product_details_event.dart';
part 'product_details_state.dart';

class ProductDetailsBloc extends Bloc<ProductDetailsEvent, ProductDetailsState> {
  final ProductRepository repository;
  ProductDetailsBloc(this.repository) : super(ProductDetailsInitial()) {
    on<DoProductViewDetails>(_onProductViewDetails);
  }
  void _onProductViewDetails(DoProductViewDetails event, Emitter<ProductDetailsState> emit) async{
    try{
      final productDetail = await repository.fetchProductDetails(event.productId);
      emit(ProductDetailsFetchSuccess(productDetail));
      return;
    }on Exception catch(e){
      emit(ProductDetailsFetchError(e.toString()));
    }
  }
}
