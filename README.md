# mjolnir-codegen

## Overview

Kotlin based generator for "data" classes to multiple languages, based on [patrol](https://github.com/vishna/patrol).

You define your domain models in YAML:

```yaml
user:
  id: String
  nickname: String
  fullname: String
  blocked: Boolean?
```

...and get them back in one of the supported languages code:

```dart
class User implements MjolnirObject {
  // members
  String id;
  String nickname;
  String fullname;
  bool blocked;

  // ctor
  User._({
    this.id,
    this.nickname,
    this.fullname,
    this.blocked,
  });

  // factory
  factory User.fromJson(Map<String, dynamic> json) {
    if (json == null) {
      return null;
    }
    dynamic id = json['id']?.toString();
    dynamic nickname = json['nickname']?.toString();
    dynamic fullname = json['fullname']?.toString();
    dynamic blocked = json['blocked'];

    return User._(
        id: id, nickname: nickname, fullname: fullname, blocked: blocked);
  }

  User copyWith({String id, String nickname, String fullname, bool blocked}) {
    return User._(
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
```

## Installation


## Usage