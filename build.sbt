// *****************************************************************************
// Build settings
// *****************************************************************************

inThisBuild(
  Seq(
    organization := "rocks.heikoseeberger",
    organizationName := "Heiko Seeberger",
    startYear := Some(2021),
    licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0")),
    scalaVersion := "3.0.0",
    scalacOptions ++= Seq(
      "-deprecation",
      "-unchecked",
      "-rewrite",
      "-new-syntax",
      "-Xfatal-warnings",
    ),
    testFrameworks += new TestFramework("munit.Framework"),
    scalafmtOnCompile := true,
    dynverSeparator := "_", // the default `+` is not compatible with docker tags
  )
)

// *****************************************************************************
// Projects
// *****************************************************************************

lazy val `game-of-life` =
  project
    .in(file("."))
    .enablePlugins(AutomateHeaderPlugin)
    .settings(commonSettings)
    .settings(
      libraryDependencies ++= Seq(
        library.scalaSwing,
        library.munit           % Test,
        library.munitScalaCheck % Test,
      ),
      run / fork := true,
    )

// *****************************************************************************
// Project settings
// *****************************************************************************

lazy val commonSettings =
  Seq(
    // Also (automatically) format build definition together with sources
    Compile / scalafmt := {
      val _ = (Compile / scalafmtSbt).value
      (Compile / scalafmt).value
    },
  )

// *****************************************************************************
// Library dependencies
// *****************************************************************************

lazy val library =
  new {
    object Version {
      val munit      = "0.7.26"
      val scalaSwing = "3.0.0"
    }
    val scalaSwing      = "org.scala-lang.modules" %% "scala-swing"      % Version.scalaSwing
    val munit           = "org.scalameta"          %% "munit"            % Version.munit
    val munitScalaCheck = "org.scalameta"          %% "munit-scalacheck" % Version.munit
  }
