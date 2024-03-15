part of 'product_bloc.dart';

@immutable
sealed class ProductState {}

final class ProductInitial extends ProductState {}

final class ProductFetchSuccess extends ProductState {
  final List<ProductDto> productDtoList;
  ProductFetchSuccess(this.productDtoList);
}

final class ProductFetchError extends ProductState {
  final String errorMessage;
  ProductFetchError(this.errorMessage);
}
