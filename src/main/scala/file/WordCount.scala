package file

import akka.NotUsed
import akka.stream.scaladsl.Flow

trait WordCount {

  val wordCountFlow: Flow[String, Int, NotUsed] = {
    Flow[String]
      .map(_.split(' ').count(_.trim.nonEmpty))
      .fold(0)(_ + _)
  }

}
