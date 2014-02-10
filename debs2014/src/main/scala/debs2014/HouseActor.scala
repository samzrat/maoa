package debs2014

import akka.cluster.Cluster
import akka.cluster.ClusterEvent._
import akka.actor.ActorLogging
import akka.actor.Actor
import akka.cluster.ClusterEvent.MemberEvent
import akka.cluster.ClusterEvent.UnreachableMember
import akka.actor.Props

object HouseActor {
  def props(plugIds: Set[String]): Props =
    Props(classOf[HouseActor], plugIds)
}

class HouseActor(plugIds: Set[String]) extends Actor with ActorLogging {

  plugIds.foreach(plugId => context.actorOf(Props(classOf[PlugActor], plugId), name = plugId))
  
  

  def receive = {
    case MemberUp(member) =>
      log.info("Member is Up: {}", member.address)
    case UnreachableMember(member) =>
      log.info("Member detected as unreachable: {}", member)
    case MemberRemoved(member, previousStatus) =>
      log.info("Member is Removed: {} after {}",
        member.address, previousStatus)
    case _: MemberEvent => // ignore
  }
}