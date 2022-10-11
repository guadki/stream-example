package file

import akka.NotUsed
import akka.stream._
import akka.stream.scaladsl._
import akka.util.ByteString

import java.nio.file.Path
import scala.concurrent.Future

trait FileRead {

  private val lines: Flow[ByteString, ByteString, NotUsed] = {
    Framing
      .delimiter(ByteString(System.lineSeparator), maximumFrameLength = 10000, allowTruncation = true)
  }

  private def resourcePath(path: String): Path = Path.of(ClassLoader.getSystemResource(path).toURI)

  def fileSource(path: String): Source[String, Future[IOResult]] = {
    FileIO
      .fromPath(resourcePath(path))
      .via(lines)
      .map(_.utf8String)
  }

}
