import 'dart:convert';

PatientModel patientModelJson(String str) =>
    PatientModel.fromJson(json.decode(str));

String patientModelToJson(PatientModel data) =>
    json.encode(data.toJson());

class PatientModel{
  int? id;
  String? firstName;
  String? lastName;
  String? address;
  String? username;
  String? email;

  PatientModel({this.id, this.firstName, this.lastName, this.address, this.username, this.email});

  factory PatientModel.fromJson(Map<String, dynamic> json)=>PatientModel(
    firstName: json["firstName"],
    lastName: json["lastName"],
    address: json["address"],
    username: json["username"],
    email: json["email"],
    id: json["id"]
  );

  Map<String, dynamic> toJson() => {
    "firstName" : firstName,
    "lastName" : lastName,
    "address" : address,
    "username" : username,
    "email" : email,
    "id": id,
  };

  // String get firstName => firstName;
  //
  // String get lastName => lastName;
}