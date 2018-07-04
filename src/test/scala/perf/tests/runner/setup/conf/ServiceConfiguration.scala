package perf.tests.runner.setup.conf

/**
  * Created by anuja on 24/05/18.
  */
class ServiceConfiguration extends Configuration{
  private val baseUrl = {
    val prop = readProperty("baseUrl")
    if (prop.isEmpty) throw new RuntimeException("baseUrl is mandatory but couldn't be found in application.conf")
    else prop
  }

  private def urlFor(protocol: String, host: String, port: String) = {
    if (port.toInt == 80 || port.toInt == 443) s"$protocol://$host" else s"$protocol://$host:$port"
  }

  def baseUrlFor(serviceName: String): String = {
    val protocol = readProperty(s"services.$serviceName.protocol", "")
    val host = readProperty(s"services.$serviceName.host", "")
    val port = readProperty(s"services.$serviceName.port", "")

    if (serviceIsDefined(protocol, host, port)) {

      val protocolOrDefault = if (protocol.isEmpty) "http" else protocol
      val hostOrDefault = if (host.isEmpty) "localhost" else host
      val portOrDefault = if (port.isEmpty) "80" else port

      urlFor(protocolOrDefault, hostOrDefault, portOrDefault)
    } else {
      baseUrl
    }
  }

  def serviceIsDefined(protocol: String, host: String, port: String): Boolean = {
    !protocol.isEmpty || !host.isEmpty || !port.isEmpty
  }

}
