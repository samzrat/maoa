package debs2014

import com.typesafe.config.ConfigFactory
import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.ActorLogging
import akka.actor.Actor

object EventProcessor {
  def main(args: Array[String]): Unit = {
    if (args.isEmpty)
      startup(Seq("2551", "2552", "0"))
    else
      startup(args)
  }

  def startup(ports: Seq[String]): Unit = {
    ports foreach { port =>
      // Override the configuration of the port
      val config = ConfigFactory.parseString("akka.remote.netty.tcp.port=" + port).
        withFallback(ConfigFactory.load())

      // Create an Akka system
      val system = ActorSystem("ClusterSystem", config)
      // Create an actor that handles cluster domain events
      val eventProcessor = system.actorOf(Props[EventProcessor], name = "EventProcessor")
      eventProcessor ! CreateHouseActorMsg("house_id1", Set("plug_id1", "plug_id2"))
    }
  }
  
  /**
  * Marker trait for remote messages with special serializer.
  */
  trait SystemConstructionMsg extends Serializable
  
  case class CreateHouseActorMsg(houseId: String, plugIds: Set[String]) extends SystemConstructionMsg

}


class EventProcessor extends Actor with ActorLogging {

  def receive = {
    case EventProcessor.CreateHouseActorMsg(houseId: String, plugIds: Set[String]) =>
      log.info("CreateHouseActorMsg received; houseId is {}, plugIds are {}", houseId, plugIds.toString)
      context.actorOf(Props(classOf[HouseActor], plugIds), name = houseId)
    case _ => throw new Exception
    
  }
}