import 'package:flutter/material.dart';

import 'login.dart';

void main() {
  runApp(const MyApp());
}

ColorScheme defaultColorScheme = const ColorScheme(
  primary: Color(0xff98AFC7),
  secondary: Color(0xffFC652E),
  surface: Color(0xff686a6c),
  background: Color(0xff686a6c),
  error: Color(0xffff2800),
  onPrimary: Color(0xff686a6c),
  onSecondary: Color(0xff686a6c),
  onSurface: Color(0xffffffff),
  onBackground: Color(0xffffffff),
  onError: Color(0xff686a6c),
  brightness: Brightness.dark,
);

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'ND Telemedicine App',
      theme: ThemeData(
        colorScheme: defaultColorScheme,
        primarySwatch: Colors.blue,
      ),
      home: const LoginPage(title: 'Login UI'),
    );
  }
}