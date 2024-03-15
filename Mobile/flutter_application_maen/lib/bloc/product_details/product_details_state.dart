part of 'product_details_bloc.dart';

@immutable
sealed class ProductDetailsState {}

final class ProductDetailsInitial extends ProductDetailsState {}

final class ProductDetailsFetchSuccess extends ProductDetailsState {
  final ProductDetailsDto productDetailsDto;
  ProductDetailsFetchSuccess(this.productDetailsDto);
}

final class ProductDetailsFetchError extends ProductDetailsState {
  final String errorMessage;
  ProductDetailsFetchError(this.errorMessage);
}
