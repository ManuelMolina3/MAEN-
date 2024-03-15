part of 'product_details_bloc.dart';

@immutable
sealed class ProductDetailsEvent {}

class DoProductViewDetails extends ProductDetailsEvent {
  final String productId;
  DoProductViewDetails(this.productId);
}