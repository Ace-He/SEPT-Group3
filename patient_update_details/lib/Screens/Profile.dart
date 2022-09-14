import 'package:flutter/material.dart';

class profile extends StatefulWidget{
  @override
  State<StatefulWidget> createState(){
    return profileState();
  }

}

class profileState extends State<profile>{
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Profile'),
      ),
      body: Center(child: Text('Profile Data'))
    );
  }

}