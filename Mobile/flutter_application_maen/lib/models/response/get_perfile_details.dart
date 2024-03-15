class UserMaenDetailsResponse {
  String? username;
  String? email;
  String? name;
  int? numMemberOfFamily;
  int? salary;

  UserMaenDetailsResponse(
      {this.username,
      this.email,
      this.name,
      this.numMemberOfFamily,
      this.salary});

  UserMaenDetailsResponse.fromJson(Map<String, dynamic> json) {
    username = json['username'];
    email = json['email'];
    name = json['name'];
    numMemberOfFamily = json['numMemberOfFamily'];
    salary = json['salary'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['username'] = this.username;
    data['email'] = this.email;
    data['name'] = this.name;
    data['numMemberOfFamily'] = this.numMemberOfFamily;
    data['salary'] = this.salary;
    return data;
  }
}
