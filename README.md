# Flutter + Appium Integration Setup

This guide explains how to set up Flutter automation testing with Appium.

## Setup

1. **Download Flutter SDK**  
   Get Flutter SDK from [Flutter Install Guide](https://docs.flutter.dev/get-started/install).  
   Add the Flutter SDK path to your **environment variables**.

2. **Run basic Flutter commands**  
   Open terminal in your Flutter project and run:
   flutter clean
   flutter pub get
   flutter run pub
   flutter upgrade --force

## Step 1: Update pubspec.yaml

Open the pubspec.yaml file and add the following under dev_dependencies:
dev_dependencies:
appium_flutter_server: 0.0.33
flutter_test:
sdk: flutter
flutter_driver:
sdk: flutter

## Step 2: Update Podfile for iOS

Navigate to the ios/ folder and update the Podfile with:
platform :ios, '18.1'

## Step 3: Create Integration Test File

1. Create an integration_test folder in the root of your Flutter project.
2. Inside it, create a file named appium_test.dart with the following content:

import 'package:flutter_application_counter/main.dart';
import 'package:appium_flutter_server/appium_flutter_server.dart';

void main() {
initializeTest(app: const MyApp());
}

## Step 4: VSCode Configuration

1. Create a .vscode folder in the project root.
2. Inside it, create a launch.json file with this content:

{
"version": "0.2.0",
"configurations": [
{
"name": "Debug",
"request": "launch",
"type": "dart"
},
{
"name": "Profile",
"request": "launch",
"type": "dart",
"flutterMode": "profile"
},
{
"name": "Release",
"request": "launch",
"type": "dart",
"flutterMode": "release"
}
]
}

## Step 5: Open Flutter DevTools/Inspector

1. In VSCode, press Ctrl/Cmd + Shift + P.
2. Search for Dart: Open DevTools.
3. Click on Open DevTools in Web Browser/Widget Inspector.

## Step 6: Install Appium and Flutter Integration Driver
https://github.com/AppiumTestDistribution/appium-flutter-integration-driver

appium driver install --source npm appium-flutter-integration-driver

## Step 7: Build Flutter Apps (APK/IPA)

To generate Flutter app builds for testing, run:

For Android (APK):
flutter build apk --debug --target="/Path/flutter_application_counter/integration_test/appium_test.dart"

For iOS (IPA/App):
flutter build ios --debug --target="/Path/flutter_application_counter/integration_test/appium_test.dart"



