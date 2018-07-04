package perf.tests.runner.setup.conf

import com.typesafe.config.{Config, ConfigFactory}

import scala.util.Try

trait Configuration {

  import scala.collection.JavaConverters._

  private[conf] val defaultConfig = ConfigFactory.systemProperties().withFallback(ConfigFactory.load("journeys")).withFallback(ConfigFactory.load())

  lazy val runLocal: Boolean = !defaultConfig.hasPath("runLocal") || defaultConfig.getBoolean("runLocal")

  lazy val applicationConfig: Config = {
    if (runLocal)
      defaultConfig.withFallback(ConfigFactory.load("services-local"))
    else
      defaultConfig.withFallback(ConfigFactory.load("services"))
  }

  def hasProperty(property: String): Boolean = applicationConfig.hasPath(property)

  def readProperty(property: String): String = applicationConfig.getString(property)

  def readProperty(property: String, default: String): String = Try(applicationConfig.getString(property)).getOrElse(default)

  def readPropertyOption(property: String): Option[String] = Try(applicationConfig.getString(property)).toOption

  def readPropertyBooleanOption(property: String): Option[Boolean] = Try(applicationConfig.getBoolean(property)).toOption

  def readPropertyList(property: String): List[String] = applicationConfig.getStringList(property).asScala.toList

  def readPropertySet(property: String): Set[String] = applicationConfig.getStringList(property).asScala.toSet

  def readPropertySetOrEmpty(property: String): Set[String] = Try(applicationConfig.getStringList(property).asScala.toSet).getOrElse(Set.empty)

  def keys(property: String): List[String] = applicationConfig.getObject(property).keySet().asScala.toList

}
