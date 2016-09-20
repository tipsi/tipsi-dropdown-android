#!/bin/sh

# install java
sudo apt-get install openjdk-7-jdk
# install android sdk
wget http://dl.google.com/android/android-sdk_r24.2-linux.tgz
tar -xvf android-sdk_r24.2-linux.tgz
cd android-sdk-linux/tools
./android update sdk --no-ui
vi ~/.zshrc << EOT
export PATH=${PATH}:$HOME/sdk/android-sdk-linux/platform-tools:$HOME/sdk/android-sdk-linux/tools:$HOME/sdk/android-sdk-linux/build-tools/22.0.1/
EOT
source ~/.zshrc

android update sdk --no-ui --all --filter tools,platform-tools,build-tools-24.0.2,android-24,sys-img-armeabi-v7a-android-24,extra-google-m2repository,extra-google-google_play_services,extra-android-support

git clone --depth=50 https://github.com/tipsi/tipsi-dropdown-android.git tipsi/tipsi-dropdown-android
jdk_switcher use oraclejdk8
export TERM=dumb
java -Xmx32m -version
javac -J-Xmx32m -version
echo no | android create avd --force -n test -t android-24 --abi armeabi-v7a
emulator -avd test -no-skin -no-audio -no-window &
android-wait-for-emulator
adb shell input keyevent 82 &
cd tipsi/tipsi-dropdown-android
./gradlew connectedAndroidTest --info --stacktrace
