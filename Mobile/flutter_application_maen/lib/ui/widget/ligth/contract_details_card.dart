import 'package:flutter/material.dart';
import 'package:flutter_application_maen/models/response/get_electricity_response.dart';

class ContractDetails extends StatelessWidget {
  final ContractDto contract;
  const ContractDetails({super.key, required this.contract});

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      width: 300,
      height: 400,
      child: Card(
        child: ClipRRect(
          borderRadius: BorderRadius.circular(10),
          child: 
          Column(children: [
            Row(
              children: [
                Image(
                  image: NetworkImage(contract.companyLogotype!),
                  height: 50,
                  width: 50,
                  fit: BoxFit.cover,
                ),
                const SizedBox(
                  height: 50,
                  width: 50,
                ),
                Text(contract.companyName!)
              ],
            ),
            const SizedBox(
              height: 25,
              width: 25,
            ),
            Row(
              children: [
                Text("Energy price: ${contract.priceEnergy!} €kWh"),
                const SizedBox(
                  height: 20,
                  width: 20,
                ),
                Text("- ${contract.discountEnergy!}%")
              ],
            ),
            const SizedBox(
              height: 20,
              width: 20,
            ),
            Text("Power price: ${contract.pricePower!}€ kW/Day"),
            const SizedBox(
              height: 20,
              width: 20,
            ),
            Text("Equipment price: ${contract.priceEquipment} €"),
            const SizedBox(
              height: 20,
              width: 20,
            ),
            Text("Taxes: ${contract.taxes} %")
          ]
          ),
        ),
      ),
    );
  }
}