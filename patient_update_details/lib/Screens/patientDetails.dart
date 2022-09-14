import 'package:flutter/material.dart';

class patientDetails extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return patientDetailsState();
  }
}

class patientDetailsState extends State<patientDetails> {
  final minimumPadding = 5.0;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Home'),
      ),
    );
  }
}