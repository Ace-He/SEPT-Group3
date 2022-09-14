import 'dart:convert';

PatientModel patientModelJson(String str) =>
    PatientModel.fromJson(json.decode(str));

String patientModelToJson(PatientModel data) =>
    json.encode(data.toJson());

class PatientModel{
  int? id;
  String? firstName;
  String? lastName;

  PatientModel({this.id, this.firstName, this.lastName});

  factory PatientModel.fromJson(Map<String, dynamic> json)=>PatientModel(
    firstName: json["firstName"],
    lastName: json["lastName"],
    id: json["id"]
  );

  Map<String, dynamic> toJson() => {
    "firstName" : firstName,
    "lastName" : lastName,
    "id": id,
  };

  // String get firstName => firstName;
  //
  // String get lastName => lastName;
}