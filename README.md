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
```

## Installation

Download ready to use `mjolnir-codegen.jar` from jitpack and put it where your flutter project is (that is where `pubspec.yaml` is):

```
wget https://jitpack.io/com/github/vishna/mjolnir-codegen/cli/master-SNAPSHOT/cli-master-SNAPSHOT-all.jar -O mjolnir-codegen.jar
```

Next up start mjolnir-codegen:

```
java -jar mjolnir-codegen.jar
```

The above will bootstrap `mjolnir-cogegen.yaml` in your current directory:

```yaml
- name: MjolnirSDK # base name
  source: # location of domain models YAML
  lang: dart
  target: lib/gen/mjolnir_sdk.dart
```

## Usage

```
Usage: mjolnir-codegen [OPTIONS]

  Mjolnir Code Generator.

Options:
  --run-once  Runs mjolnir-codegen only once, doesn't watch file system,
              useful for CI/CD.
  --dry-run   Runs mjolnir-codegen in a dry mode
  --debug     Runs mjolnir-codegen in a debug mode
  -h, --help  Show this message and exit
```