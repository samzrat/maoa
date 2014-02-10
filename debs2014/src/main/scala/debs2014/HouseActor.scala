package debs2014

import akka.cluster.Cluster
import akka.cluster.ClusterEvent._
import akka.actor.ActorLogging
import akka.actor.Actor
import akka.cluster.ClusterEvent.MemberEvent
import akka.cluster.ClusterEvent.UnreachableMember
import akka.actor.Props
import akka.contrib.pattern._

object HouseActor {
  def props(plugIds: Set[String]): Props = Props(classOf[HouseActor], plugIds)
  
  case class PublishedMsg(content: String)
}

class HouseActor(plugIds: Set[String]) extends Actor with ActorLogging {

  plugIds.foreach(plugId => context.actorOf(Props(classOf[PlugActor], plugId), name = plugId))
  
  import DistributedPubSubMediator.{ Subscribe, SubscribeAck, Publish }
  val mediator = DistributedPubSubExtension(context.system).mediator
  // subscribe to the topic named "content"
  mediator ! Subscribe("self.path.toStringWithoutAddress", self)
  log.info("(self.path.toStringWithoutAddress = " + self.path.toStringWithoutAddress)
  mediator ! Publish("self.path.toStringWithoutAddress", "howdy")
  
  def ready: Actor.Receive = {
    case s: String ⇒
      log.info("Got {}", s)
  }
  
  def receive = {
    case SubscribeAck(Subscribe("self.path.toStringWithoutAddress", self)) ⇒ {
      log.info("SubscribeAck received")
      //context become ready
      for (i <- 1 to 5) 
      {
        log.info("Publishing msg {}", i)
        //mediator ! Publish("self.path.toStringWithoutAddress", HouseActor.PublishedMsg("howdy"))
        mediator ! Publish("self.path.toStringWithoutAddress", "howdy")
      }  
      Thread.sleep(10000)
    }  
    case in: String =>
      log.info("Pubished message received {} sender is {}", in, sender.path)
      //mediator ! Publish("self.path.toStringWithoutAddress", "howdy Ranganath")
    case HouseActor.PublishedMsg(content) =>
      log.info("Pubished message received {} sender is {}", content, sender.path)

    case _ => throw new Exception
  }
}