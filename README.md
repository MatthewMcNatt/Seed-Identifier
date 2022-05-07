# Seed-identifier

Seed-identifier is an application developed by group 12 in the spring semester of the CSE3310 Fundamentals of Engineering course, at the University of Texas at Arlington.

This application allows the user to scan, recognize and describe **corn, cucumber and sunflower seeds**. This is done using a trained tensor flow neural network in order to tell apart which seed is which, by parsing an image either with the hardware camera of the device, or by picking an image out of the device's gallery.

Seed-identifier was coded using Java on Android Studio.

## Contents

- [Running Seed-identifier](#running-seed-identifier)
    - [Install Android Studio](#install-android-studio)
    - [Run using Android Studio's emulator](#run-using-android-studios-emulator)
    - [Run using .apk on an Android device](#run-using-apk-on-an-android-device)
        - [Obtaining the .apk file manually](#obtaining-the-apk-file-manually)
    - [Installing the .apk file in your Android device](#installing-the-apk-file-in-your-android-device)

## Running Seed-identifier

In order to install this application in either an android device or an emulator, either the source code or an .apk can be used. These are provided with the source of the application.

### Install Android Studio

In order to install the application using this method, a current installation of Android Studio on either Windows, Linux or macOS.

    https://developer.android.com/studio

Also, make sure the emulator is also installed along with the installation of Android Studio, documentation for that can be found here: <https://developer.android.com/studio/install>.

## Run using Android Studio's emulator

First of all, the repository has to be cloned using Android Studio's built-in tools:

1. Open Android Studio on a blank project.

2. Click on **Git** > **Clone...** on the tool bar.

3. A menu where the Github's repository link can be inserted will be prompted
    * Use the following link: <https://github.com/MatthewMcNatt/Seed-Identifier>

4. Press on clone, and then make sure you **Trust project**.

5. If cloning was successful, the source code of the application will be visible in the file manager on the left side of the application.

6. Click on the play button or press **Ctrl+F10** to compile and run the application in the android emulator. 

## Run using .apk on an Android device

For this method, an android device running **android 5+** is required.

### Obtaining the .apk file manually

1. Make sure the you have the repository cloned and everything is set up correctly following the steps in [Run using Android Studio's emulator](#run-using-android-studios-emulator) (steps 1-5).

2. Click on **Build** > **Build Bundle(s)/APK(s)** > **Build APK(s)**.

3. A pop up will show up in the bottom-right corner of the display, press **Locate**.

4. A folder will open with the location where the .apk files are located. Note that these files are for development only.

## Installing the .apk file in your Android device

Once we got the .apk file ready, we can manually install these by getting the file into your smartphone, and performing a manual installation.

1. Once you have access to the .apk file, which should be named **app-debug.apk**, transfer it to your Android device.
    - Alternatively, the you can download the appropriate .apk file using the following link: <https://drive.google.com/file/d/1I0-E8iGZkR5wsAXWseQE6AsX7y8GLF4I/view?usp=sharing>

2. Locate the file in your Android device using a file manager app, which is either included in the device or downloaded from the Play Store.
3. Open the file by clicking on it, and proceed with the installation.
    - the option to **Allow unknown sources** must be active in order to install this application. This option can be enabled as the installation process is happening.

4. Once the installation finishes, the application will be shown in the home menu.


