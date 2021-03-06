import android.Keys._

android.Plugin.androidBuild

platformTarget in Android := "android-24"

name := "shadowsocks"

scalaVersion := "2.11.8"

compileOrder in Compile := CompileOrder.JavaThenScala

javacOptions ++= Seq("-source", "1.7", "-target", "1.7")

scalacOptions ++= Seq("-target:jvm-1.7", "-Xexperimental")

ndkJavah in Android := List()

ndkBuild in Android := List()

shrinkResources in Android := true

typedResources in Android := false

resConfigs in Android := Seq("ru", "zh-rCN", "zh-rTW")

dexMaxHeap in Android := "4g"

resolvers += Resolver.jcenterRepo

useSupportVectors

googleServicesSettings

libraryDependencies ++= Seq(
  "com.android.support" % "design" % "24.2.1",
  "com.android.support" % "gridlayout-v7" % "24.2.1",
  "com.android.support" % "preference-v14" % "24.2.1",
  "com.github.clans" % "fab" % "1.6.4",
  "com.github.jorgecastilloprz" % "fabprogresscircle" % "1.01",
  "com.github.kevinsawicki" % "http-request" % "6.0",
  "com.google.android.gms" % "play-services-ads" % "9.6.1",
  "com.google.android.gms" % "play-services-analytics" % "9.6.1",
  "com.google.firebase" % "firebase-storage" % "9.6.1",
  "com.j256.ormlite" % "ormlite-android" % "5.0",
  "com.twofortyfouram" % "android-plugin-api-for-locale" % "1.0.2",
  "dnsjava" % "dnsjava" % "2.1.7",
  "eu.chainfire" % "libsuperuser" % "1.0.0.201608240809",
  "me.dm7.barcodescanner" % "zxing" % "1.9",
  "net.glxn.qrgen" % "android" % "2.0"
)

proguardVersion in Android := "5.3"

proguardCache in Android := Seq()

proguardOptions in Android ++= Seq(
  "-keepattributes Signature",
  "-keep class com.github.shadowsocks.System { *; }",
  "-dontwarn com.google.android.gms.internal.**",
  "-dontwarn com.j256.ormlite.**",
  "-dontwarn org.xbill.**")

lazy val nativeBuild = TaskKey[Unit]("native-build", "Build native executables")

nativeBuild := {
  val logger = streams.value.log
  Process("./build.sh") ! logger match {
    case 0 => // Success!
    case n => sys.error(s"Native build script exit code: $n")
  }
}
