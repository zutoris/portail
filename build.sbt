name := """portail"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  specs2 % Test
  // éventuellement utiliser bootstrap si besoin : 
  //"org.webjars"   %% "webjars-play"  % "2.4.6",
  //"org.webjars" % "bootstrap" % "3.3.6" exclude("org.webjars", "jquery"),
  //"org.webjars" % "jquery" % "1.11.3"

)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator
