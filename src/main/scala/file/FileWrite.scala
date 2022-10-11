package file

import akka.stream.IOResult
import akka.stream.scaladsl.{FileIO, Sink}
import akka.util.ByteString

import java.nio.file.{OpenOption, Path}
import java.nio.file.StandardOpenOption._
import scala.concurrent.Future

trait FileWrite {

  def fileSink(path: String, fileWriteOptions: Set[OpenOption] = Set(APPEND, CREATE)): Sink[ByteString, Future[IOResult]] = {
    FileIO
      .toPath(Path.of(path), fileWriteOptions)
  }

}
