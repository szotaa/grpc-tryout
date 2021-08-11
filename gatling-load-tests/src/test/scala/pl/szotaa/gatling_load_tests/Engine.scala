package pl.szotaa.gatling_load_tests

import io.gatling.app.Gatling
import io.gatling.core.config.GatlingPropertiesBuilder

class Engine extends App {

  val props = new GatlingPropertiesBuilder()
    .resourcesDirectory(IDEPathHelper.mavenResourcesDirectory.toString)
    .resultsDirectory(IDEPathHelper.resultsDirectory.toString)
    .binariesDirectory(IDEPathHelper.mavenBinariesDirectory.toString)

    Gatling.fromMap(props.build)
}
