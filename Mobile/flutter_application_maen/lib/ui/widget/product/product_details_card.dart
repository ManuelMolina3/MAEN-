import 'package:flutter/material.dart';
import 'package:flutter_application_maen/models/response/product_details.dart';
import 'package:transparent_image/transparent_image.dart';

class ProductDetailsCard extends StatelessWidget {
  final ProductDetailsDto product;
  const ProductDetailsCard({super.key, required this.product});

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      width: 300,
      height: 400,
      child: Card(
        child: ClipRRect(
          borderRadius: BorderRadius.circular(10),
          child: Column(children: [
            Row(
              children: [
                Image(
                  image: NetworkImage(product.superMarketImage!),
                  height: 50,
                  width: 50,
                  fit: BoxFit.cover,
                ),
                const SizedBox(
                  height: 25,
                  width: 25,
                ),
                Text(product.superMarketName!)
              ],
            ),
            FadeInImage.memoryNetwork(
              placeholder: kTransparentImage,
              image: product.productImage!,
              width: 170,
              height: 170,
              fit: BoxFit.cover,
            ),
            const SizedBox(
              height: 30,
              width: 30,
            ),
            Center(
              child: Row(
                children: [
                  Text("product: ${product.productName!}"),
                  const SizedBox(
                    height: 20,
                    width: 20,
                  ),
                  Text("brand: ${product.productBrand!}")
                ],
              ),
            ),
            const SizedBox(
              height: 30,
              width: 30,
            ),
            Center(
              child: Row(
                children: [
                  Text("price: ${product.price!}€"),
                  const SizedBox(
                    height: 20,
                    width: 20,
                  ),
                  Text("price/kg: ${product.priceKg!}€"),
                ],
              ),
            ),
            const SizedBox(
              height: 30,
              width: 30,
            ),
            Center(child: Text("IVA: ${product.taxes!}%"))
          ]),
        ),
      ),
    );
  }
}
