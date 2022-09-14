import 'dart:convert';

PatientModel patientModelJson(String str) =>
    PatientModel.fromJson(json.decode(str));

String patientModelToJson(PatientModel data) => json.encode(data.toJson());

class PatientModel {
  int id;
  String username;
  String password;
  String firstName;
  String lastName;
  String sex;
  String age;
  int smokes;


  PatientModel({this.id, this.firstName, this.lastName, this.username, this.password,
  this.sex, this.age, this.smokes});

  factory PatientModel.fromJson(Map<String, dynamic> json) => PatientModel(
      firstName: json["firstName"], lastName: json["lastName"], id: json["id"], username: json["username"],
      password: json["password"], password: json["password"], sex: json["sex"],  age: json["age"],  smokes: json["smokes"]);

  Map<String, dynamic> toJson() => {
    "firstName": firstName,
    "lastName": lastName,
    'id': id,
    "username": username,
    "password": password,
    "sex": sex,
    "age": age,
    "smokes": smoke
  };

  String get firstname => firstName;

  String get lastname => lastName;

  String get username => username;

  String get password => password;

  String get sex => sex;

  String get smokes => smokes;

  int get age => age;
}