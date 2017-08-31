trapExit := false
fork := true

mainClass in Compile := Some("main.scala.GUI.MainMenu")
mainClass in assembly := Some("main.scala.GUI.MainMenu")

assemblyJarName in assembly := "FaceRecognition.jar"

// https://mvnrepository.com/artifact/org.apache.httpcomponents/httpclient
libraryDependencies += "org.apache.httpcomponents" % "httpclient" % "4.5.3"

// https://mvnrepository.com/artifact/org.json/json
libraryDependencies += "org.json" % "json" % "20170516"

// https://mvnrepository.com/artifact/org.scala-lang/scala-swing
libraryDependencies += "org.scala-lang" % "scala-swing" % "2.10.6"

val javacvVersion = "0.9"

val javacppVersion = "0.9"

// Some dependencies like `javacpp` are packaged with maven-plugin packaging
classpathTypes += "maven-plugin"

// Determine current platform
val platform = {
  // Determine platform name using code similar to javacpp
  // com.googlecode.javacpp.Loader.java line 60-84
  val jvmName = System.getProperty("java.vm.name").toLowerCase
  var osName = System.getProperty("os.name").toLowerCase
  var osArch = System.getProperty("os.arch").toLowerCase
  if (jvmName.startsWith("dalvik") && osName.startsWith("linux")) {
    osName = "android"
  } else if (jvmName.startsWith("robovm") && osName.startsWith("darwin")) {
    osName = "ios"
    osArch = "arm"
  } else if (osName.startsWith("mac os x")) {
    osName = "macosx"
  } else {
    val spaceIndex = osName.indexOf(' ')
    if (spaceIndex > 0) {
      osName = osName.substring(0, spaceIndex)
    }
  }
  if (osArch.equals("i386") || osArch.equals("i486") || osArch.equals("i586") || osArch.equals("i686")) {
    osArch = "x86"
  } else if (osArch.equals("amd64") || osArch.equals("x86-64") || osArch.equals("x64")) {
    osArch = "x86_64"
  } else if (osArch.startsWith("arm")) {
    osArch = "arm"
  }
  val platformName = osName + "-" + osArch
  println("platform: " + platformName)
  platformName
}

libraryDependencies ++= Seq(
  "org.bytedeco" % "javacv" % javacvVersion excludeAll(
    ExclusionRule(organization = "org.bytedeco.javacpp-presets"),
    ExclusionRule(organization = "org.bytedeco.javacpp")
    ),
  "org.bytedeco.javacpp-presets" % "opencv"  % ("2.4.9-" + javacppVersion) classifier "",
  "org.bytedeco.javacpp-presets" % "opencv"  % ("2.4.9-" + javacppVersion) classifier platform,
  "org.bytedeco"                 % "javacpp" % javacppVersion,
  "org.scala-lang"               % "scala-swing"     % scalaVersion.value,
  "net.imagej"                   % "ij"              % "1.47v",
  "junit"                        % "junit"           % "4.11" % "test",
  "com.novocode"                 % "junit-interface" % "0.10" % "test"
)

resolvers ++= Seq(
  Resolver.sonatypeRepo("snapshots"),
  "ImageJ Releases" at "http://maven.imagej.net/content/repositories/releases/",
  // Use local maven repo for local javacv builds
  "Local Maven Repository" at "file:///" + Path.userHome.absolutePath + "/.m2/repository"
)