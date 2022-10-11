import akka.stream.IOResult
import akka.stream.scaladsl.{RunnableGraph, Sink}
import akka.util.ByteString
import file.{FileRead, FileWrite, WordCount}

import scala.concurrent.Future
import scala.util.{Failure, Success}

object Main extends App with AkkaImplicits with FileRead with FileWrite with WordCount {

  val fileToRead = "sample.txt"
  val fileToWrite = "output.txt"

  val runnable: RunnableGraph[Future[IOResult]] = {
    fileSource(fileToRead)
      .via(wordCountFlow)
      .map(c => ByteString(s"Word count is $c${System.lineSeparator()}"))
      .to(fileSink(fileToWrite))
  }

  runnable.run().onComplete {
    case Success(_) => system.terminate()
    case Failure(exception) =>
      println(exception.getMessage)
      system.terminate()

  }

}
