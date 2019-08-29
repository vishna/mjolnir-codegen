/// Generated file, DO NOT EDIT!!!
// ignore_for_file: strong_mode_implicit_dynamic_parameter
// ignore_for_file: strong_mode_implicit_dynamic_type
// ignore_for_file: non_constant_identifier_names
// ignore_for_file: omit_local_variable_types
// ignore_for_file: prefer_final_locals
// ignore_for_file: sort_constructors_first
// ignore_for_file: directives_ordering
// ignore_for_file: avoid_init_to_null
// ignore_for_file: prefer_collection_literals
// ignore_for_file: unnecessary_parenthesis
// ignore_for_file: unnecessary_brace_in_string_interps
class Photo implements MjolnirObject {
  /// members
  final String id;
  final String url;
  final int width;
  final int height;
  final DateTime updated;
  final User user;

  /// ctor
  const Photo({
    this.id,
    this.url,
    this.width,
    this.height,
    this.updated,
    this.user,
  });

  /// factory
  factory Photo.fromJson(Map<String, dynamic> json) {
    if (json == null) {
      return null;
    }
    dynamic id = json['id']?.toString();
    dynamic url = json['url']?.toString();
    dynamic width = json['width'];
    dynamic height = json['height'];
    DateTime updated = DateTime.parse(json['updated']);
    User user = User.fromJson(json['user']);

    return Photo(
        id: id,
        url: url,
        width: width,
        height: height,
        updated: updated,
        user: user);
  }

  Photo copyWith(
      {String id,
      String url,
      int width,
      int height,
      DateTime updated,
      User user}) {
    return Photo(
        id: id ?? this.id,
        url: url ?? this.url,
        width: width ?? this.width,
        height: height ?? this.height,
        updated: updated ?? this.updated,
        user: user ?? this.user?.copyWith());
  }

  Map<String, dynamic> toJson() {
    final output = Map<String, dynamic>();

    output["id"] = id;
    output["url"] = url;
    output["width"] = width;
    output["height"] = height;
    output["updated"] = updated?.toIso8601String();
    output["user"] = user?.toJson();

    return output;
  }

  static Photo Function(dynamic json) deserializer =
      ((json) => Photo.fromJson(json));

  @override
  String get objectId => "Photo#${id}";
}

class User implements MjolnirObject {
  /// members
  final String id;
  final String nickname;
  final String fullname;
  final bool blocked;

  /// ctor
  const User({
    this.id,
    this.nickname,
    this.fullname,
    this.blocked,
  });

  /// factory
  factory User.fromJson(Map<String, dynamic> json) {
    if (json == null) {
      return null;
    }
    dynamic id = json['id']?.toString();
    dynamic nickname = json['nickname']?.toString();
    dynamic fullname = json['fullname']?.toString();
    dynamic blocked = json['blocked'];

    return User(
        id: id, nickname: nickname, fullname: fullname, blocked: blocked);
  }

  User copyWith({String id, String nickname, String fullname, bool blocked}) {
    return User(
        id: id ?? this.id,
        nickname: nickname ?? this.nickname,
        fullname: fullname ?? this.fullname,
        blocked: blocked ?? this.blocked);
  }

  Map<String, dynamic> toJson() {
    final output = Map<String, dynamic>();

    output["id"] = id;
    output["nickname"] = nickname;
    output["fullname"] = fullname;
    output["blocked"] = blocked;

    return output;
  }

  static User Function(dynamic json) deserializer =
      ((json) => User.fromJson(json));

  @override
  String get objectId => "User#${id}";
}

dynamic Function(dynamic json) deserializerByClassName(String className) {
  switch (className) {
    case "Photo":
      return Photo.deserializer;
    case "User":
      return User.deserializer;
    default:
      {
        throw ArgumentError("deserializer for $className not defined");
      }
  }
}
