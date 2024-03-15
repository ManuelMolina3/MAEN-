part of 'product_bloc.dart';

@immutable
sealed class ProductEvent {}

class ProductFetchList extends ProductEvent {
  final String product;
  ProductFetchList(this.product);
}


