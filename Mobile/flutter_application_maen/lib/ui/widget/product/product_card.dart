import 'package:flutter/material.dart';
import 'package:flutter_application_maen/models/response/get_all_products.dart';
import 'package:flutter_application_maen/ui/pages/product/product_details_page.dart';
import 'package:transparent_image/transparent_image.dart';

class ProductCard extends StatelessWidget {
  final ProductDto product;
  final int index;
  const ProductCard({super.key, required this.product, required this.index});

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: () {
        Navigator.push(
          context,
          MaterialPageRoute(
              builder: (context) => ProductDetailsPage(
                    id: product.id!, 
                  )),
        );
      },
      child: SizedBox(
        width: 400,
        height: 300,
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
                Text(product.productName!),
                Text("${product.price!}€")
              ])),
        ),
      ),
    );
  }
}
