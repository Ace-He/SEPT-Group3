import 'package:flutter/material.dart';
import 'package:patient_update_details/Screens/Profile.dart';

void main(){
  runApp(App());
}

// class myApp extends StatelessWidget{
//   @override
//   Widget build(buildContext){
//
//   }
// }
class App extends StatelessWidget {
  const App({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Profile', debugShowCheckedModeBanner: false,
      theme: ThemeData(
        primarySwatch: Colors.lightGreen,
      ),
      home: profile(),
    );
  }
}