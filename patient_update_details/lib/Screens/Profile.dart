import 'dart:convert';

import 'package:patient_update_details/Model/PatientModel.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

class profile extends StatefulWidget{
  @override
  State<StatefulWidget> createState(){
    return profileState();
  }

}

Future<PatientModel> updateProfile(
    String firstName, String lastName, BuildContext context) async {
  var Url = "http://localhost:8080/updateProfile";
  var response = await http.post(Url,
      headers: <String, String>{"Content-Type": "application/json"},
      body: jsonEncode(<String, String>{
        "firstName": firstName,
        "lastName": lastName,
      }));

  String responseString = response.body;
  if (response.statusCode == 200) {
    showDialog(
      context: context,
      barrierDismissible: true,
      builder: (BuildContext dialogContext) {
        return MyAlertDialog(title: 'Backend Response', content: response.body);
      },
    );
  }
  throw new Exception(["Status Code != 200"]);
  }

class profileState extends State<profile>{

  final padding = 5.0;
  @override

  TextEditingController firstController = TextEditingController();
  TextEditingController secondController = TextEditingController();

  Widget build(BuildContext context) {
    TextStyle? textStyle = Theme.of(context).textTheme.subtitle2;
    return Scaffold(
      appBar: AppBar(
        title: Text('Profile'),
      ),
      body: Form(
        child: Padding(
          padding: EdgeInsets.all(padding*2),
          child: ListView(
            children: <Widget>[
              Padding(
                  padding: EdgeInsets.only(top: padding, bottom: padding),
              child: TextFormField(
                style: textStyle,
                controller: firstController,
                // validator: (String value){
                //   if(value.isEmpty){
                //     return 'Please enter your name';
                //   }
                // },
                decoration: InputDecoration(
                  labelText: 'First Name',
                  hintText: 'Enter your name',
                  labelStyle: textStyle,
                    border: OutlineInputBorder(
                      borderRadius: BorderRadius.circular(5.0)
                    )
                )
              )
              ),
              Padding(
                  padding: EdgeInsets.only(top: padding, bottom: padding),
                  child: TextFormField(
                      style: textStyle,
                      controller: secondController,
                      // validator: (String value){
                      //   if(value.isEmpty){
                      //     return 'Please enter your name';
                      //   }
                      // },
                      decoration: InputDecoration(
                          labelText: 'Last Name',
                          hintText: 'Enter your name',
                          labelStyle: textStyle,
                          border: OutlineInputBorder(
                              borderRadius: BorderRadius.circular(5.0)
                          )
                      )
                  )
              ),
              ElevatedButton(
                  child: Text('Submit'),
                  onPressed: () async {
                    String firstName = firstController.text;
                    String lastName = secondController.text;
                    PatientModel patient =
                    await updateProfile(firstName, lastName, context);
                    firstController.text = '';
                    secondController.text = '';
                    setState(() {
                      patient = patient;
                    });
                  })
            ]
          )
        )
      ),
    );
  }

}

class MyAlertDialog extends StatelessWidget {
  final String title;
  final String content;
  final List<Widget> actions;

  MyAlertDialog({
    required this.title,
    required this.content,
    this.actions = const [],
  });

  @override
  Widget build(BuildContext context) {
    return AlertDialog(
      title: Text(
        this.title,
        style: Theme.of(context).textTheme.titleMedium,
      ),
      actions: this.actions,
      content: Text(
        this.content,
        style: Theme.of(context).textTheme.bodyText1,
      ),
    );
  }
}