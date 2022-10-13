import 'dart:convert';

import 'package:email_validator/email_validator.dart';
import 'package:flutter/material.dart';
import 'register.dart';
import 'package:dio/dio.dart';
import 'dart:convert' as convert;

class LoginPage extends StatefulWidget {
  const LoginPage({Key? key, required this.title}) : super(key: key);
  final String title;

  @override
  State<LoginPage> createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {
  final _formKey = GlobalKey<FormState>();
  var rememberValue = false;
  late TextEditingController emailController = TextEditingController();
  late TextEditingController passwordController = TextEditingController();
  late TextEditingController codeController = TextEditingController();

  loginMethod() async {
    Map<String, dynamic> data = {
      "email": emailController.text,
      "password": passwordController.text,
      "code": codeController.text,
      "userType": 2,
    };
    Response response = await Dio().post(
      "http://127.0.0.1:8081/nd/login",
      data: jsonEncode(data),
    );
    print("http parameters start：");
    print(jsonEncode(data));
    print("http parameters end：");

    print("http request start");
    print(response.data);
    print("http request end");

    Map<String, dynamic> map;
    if (response.data is String) {
      map = convert.jsonDecode(response.data) ?? {};
    } else {
      map = response.data;
    }


    // Update when merged with home page
    // if (map["code"].toString() == "200")
    // {
    //   Navigator.push<void>(
    //     context,
    //     MaterialPageRoute<void>(
    //       builder: (BuildContext context) => const HomePage(),
    //     ),
    //   );
    // }
  }

  getCodeMethod() async{
    Response response = await Dio().get(
        "http://localhost:8081/nd/verify/image/00@qq.com"
    );
    codeController.text = response.data;
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Theme.of(context).colorScheme.background,
      body: Container(
        padding: const EdgeInsets.all(20),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: <Widget>[
            const Text(
              'Sign in',
              style: TextStyle(
                fontWeight: FontWeight.bold,
                fontSize: 40,
              ),
            ),
            const SizedBox(
              height: 60,
            ),
            Form(
              key: _formKey,
              child: Column(
                children: [
                  TextFormField(
                    validator: (value) => EmailValidator.validate(value!)
                        ? null
                        : "Please enter a valid email",
                    controller: emailController,
                    maxLines: 1,
                    decoration: InputDecoration(
                      hintText: 'Enter your email',
                      prefixIcon  : const Icon(Icons.email),
                      border: OutlineInputBorder(
                        borderRadius: BorderRadius.circular(10),
                      ),
                    ),
                  ),
                  const SizedBox(
                    height: 20,
                  ),
                  TextFormField(
                    validator: (value) {
                      if (value == null || value.isEmpty) {
                        return 'Please enter your password';
                      }
                      return null;
                    },
                    controller: passwordController,
                    maxLines: 1,
                    obscureText: true,
                    decoration: InputDecoration(
                      prefixIcon: const Icon(Icons.lock),
                      hintText: 'Enter your password',
                      border: OutlineInputBorder(
                        borderRadius: BorderRadius.circular(10),
                      ),
                    ),
                  ),
                  CheckboxListTile(
                    title: const Text("Remember me"),
                    contentPadding: EdgeInsets.zero,
                    value: rememberValue,
                    activeColor: Theme.of(context).colorScheme.primary,
                    onChanged: (newValue) {
                      setState(() {
                        rememberValue = newValue!;
                      });
                    },
                    controlAffinity: ListTileControlAffinity.leading,
                  ),
                  const SizedBox(
                    height: 20,
                  ),
                  Row(
                      children: [
                        Expanded(
                          child: TextFormField(
                            controller: codeController,
                            maxLines: 1,
                            decoration: InputDecoration(
                              hintText: 'Code',
                              prefixIcon: const Icon(Icons.code),
                              border: OutlineInputBorder(
                                borderRadius: BorderRadius.circular(10),
                              ),
                            ),
                          ),
                        ),
                        const SizedBox(
                          height: 20,
                        ),
                        Expanded(
                          child: ElevatedButton(
                            onPressed: () {
                              getCodeMethod();
                            },
                            style: ElevatedButton.styleFrom(
                              padding: const EdgeInsets.fromLTRB(40, 15, 40, 15),
                            ),
                            child: const Text(
                              'Get Code',
                              style: TextStyle(
                                fontWeight: FontWeight.bold,
                              ),
                            ),
                          ),
                        ),
                      ]
                  ),
                  const SizedBox(
                    height: 20,
                  ),
                  ElevatedButton(
                    onPressed: () {
                      if (_formKey.currentState!.validate()) {
                        loginMethod();
                      }
                    },
                    style: ElevatedButton.styleFrom(
                      padding: const EdgeInsets.fromLTRB(40, 15, 40, 15),
                    ),
                    child: const Text(
                      'Sign in',
                      style: TextStyle(
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                  ),
                  const SizedBox(
                    height: 20,
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      const Text('Not registered yet?'),
                      TextButton(
                        onPressed: () {
                          Navigator.pushReplacement(
                            context,
                            MaterialPageRoute(
                              builder: (context) =>
                              const RegisterPage(title: 'Register UI'),
                            ),
                          );
                        },
                        child: const Text('Create an account'),
                      ),
                    ],
                  ),
                ],
              ),
            )
          ],
        ),
      ),
    );
  }
}