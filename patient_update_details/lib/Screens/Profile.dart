import 'package:flutter/material.dart';

class profile extends StatefulWidget{
  @override
  State<StatefulWidget> createState(){
    return profileState();
  }

}

class profileState extends State<profile>{

  void submit(){
    print(firstController.text);
    print(secondController.text);
}

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
              ElevatedButton(onPressed: submit, child: Text("Submit"),),
            ]
          )
        )
      ),
    );
  }

}