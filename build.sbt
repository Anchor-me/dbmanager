name := "dbmanager"

version := "1.0"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "com.evojam" %% "play-elastic4s" % "0.2.1",
  "com.anchor" %% "anchor-models" % "1.0"
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

resolvers += Resolver.url("Typesafe Ivy releases", url("https://repo.typesafe.com/typesafe/ivy-releases"))(Resolver.ivyStylePatterns)

routesGenerator := InjectedRoutesGenerator


fork in run := true
