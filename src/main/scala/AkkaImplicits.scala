import akka.actor.ActorSystem

import scala.concurrent.ExecutionContextExecutor

trait AkkaImplicits {
  implicit val system: ActorSystem = ActorSystem("stream-example")
  implicit val dispatcher: ExecutionContextExecutor = system.dispatcher
}
