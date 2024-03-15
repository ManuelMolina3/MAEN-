import 'package:flutter/material.dart';
import 'package:flutter_application_maen/models/response/get_electricity_response.dart';
import 'package:flutter_application_maen/ui/pages/contract/contract_details_page.dart';

class ContarctCard extends StatelessWidget {
  final ContractDto contract;
  final int index;
  const ContarctCard({super.key, required this.contract, required this.index});

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: () {
        Navigator.push(
          context,
          MaterialPageRoute(
              builder: (context) => ContractDetailsPage(id: contract.id!)),
        );
      },
      child: SizedBox(
          width: 150,
          height: 150,
          child: Card(
            child: ClipRRect(
              borderRadius: BorderRadius.circular(10),
              child: Column(
                children: [
                  Row(
                    children: [
                      Image(
                        image: NetworkImage(contract.companyLogotype!),
                        height: 50,
                        width: 50,
                        fit: BoxFit.cover,
                      ),
                      const SizedBox(
                        height: 20,
                        width: 20,
                      ),
                      Text(contract.companyName!)
                    ],
                  ),
                  const SizedBox(
                    height: 20,
                    width: 20,
                  ),
                  Text("Power price: ${contract.pricePower}€ * KWh"),
                  const SizedBox(
                    height: 20,
                    width: 20,
                  ),
                  Text("Energy price: ${contract.priceEnergy}€ * KWh"),
                  const SizedBox(
                    height: 20,
                    width: 20,
                  ),
                  Text("Equipment price: ${contract.priceEquipment}€"),
                ],
              ),
            ),
          )),
    );
  }
}
