name := "lovespark"

resolvers += "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"
resolvers += "Job Server Bintray" at "https://dl.bintray.com/spark-jobserver/maven"

version := "1.0"

scalaVersion := "2.10.6"

libraryDependencies += "org.apache.spark" %% "spark-core" % "1.6.3"
libraryDependencies += "org.apache.spark" %% "spark-streaming" % "1.6.3"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "1.6.3"
libraryDependencies += "org.apache.spark" %% "spark-mllib" % "1.6.3"
libraryDependencies += "org.elasticsearch" % "elasticsearch-spark_2.10" % "2.1.0.Beta3"
//libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.1.2"
libraryDependencies += "com.typesafe" %% "scalalogging-slf4j" % "1.1.0"
//libraryDependencies += "com.typesafe.scala-logging" % "scala-logging-slf4j_2.10" % "2.1.2"
libraryDependencies += "com.github.jsqlparser" % "jsqlparser" % "0.9.5"
libraryDependencies += "spark.jobserver" %% "job-server-api" % "0.6.2" % "provided"

//// https://mvnrepository.com/artifact/org.quartz-scheduler/quartz-jobs
//libraryDependencies += "org.quartz-scheduler" % "quartz-jobs" % "2.2.2"
// https://mvnrepository.com/artifact/org.quartz-scheduler/quartz
libraryDependencies += "org.quartz-scheduler" % "quartz" % "2.2.3"