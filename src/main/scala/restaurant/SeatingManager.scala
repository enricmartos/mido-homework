package restaurant

import scala.collection.mutable

case class SeatingManager(tables: List[Table]) {
  val tablesAvailable: mutable.TreeMap[Int, List[Table]] = tablesAvailable(tables, mutable.TreeMap[Int, List[Table]]())
  val customersGroupsSeated: mutable.TreeMap[Int, List[Table]] = mutable.TreeMap[Int, List[Table]]()
  val customersGroupsQueue: mutable.LinkedHashSet[CustomerGroup] = mutable.LinkedHashSet[CustomerGroup]()

  def arrives(group: CustomerGroup): Unit = {
    customersGroupsQueue.add(group)
    locateCustomersGroupQueue()
  }

  def leaves(group: CustomerGroup): Unit = {
    if (customersGroupsSeated.contains(group.size)) {
      customersGroupsSeated(group.size) match {
        case ::(_, next) =>
          leaveTable(table = customersGroupsSeated(group.size).head)
          if (next.isEmpty) customersGroupsSeated.remove(group.size) else customersGroupsSeated.update(group.size, next)
      }

      locateCustomersGroupQueue()
    }

    if (customersGroupsQueue.contains(group)) {
      customersGroupsQueue.remove(group)
    }
  }

  def locate(group: CustomerGroup): Option[Table] = {
    if (customersGroupsSeated(group.size).isEmpty) {
      Some(customersGroupsSeated(group.size).head)
    }

    None
  }

  private def locateCustomersGroupQueue(): Unit = {
    if (tablesAvailable.nonEmpty) {
      customersGroupsQueue.foreach(group => {
        findTableAvailable(group.size) match {
          case Some(table) =>
            customersGroupsQueue.remove(group)
            customersGroupsSeated.get(group.size) match {
              case Some(list) => customersGroupsSeated.update(group.size, list.appended(table))
              case None => customersGroupsSeated.put(group.size, List().appended(table))
            }
          case None => ()
        }
      })
    }
  }

  private def findTableAvailable(seats: Int): Option[Table] = {
    tablesAvailable.rangeFrom(seats).headOption match {
      case Some(value) =>
        val (size, tables) = value
        tables match {
          case ::(_, next) => if (next.isEmpty) tablesAvailable.remove(size) else tablesAvailable.update(size, next)
        }

        Some(tables.head)
      case None => None
    }
  }

  private def leaveTable(table: Table, tablesAvailable: mutable.TreeMap[Int, List[Table]] = tablesAvailable): Unit = {
    tablesAvailable.get(table.size) match {
      case Some(list) => tablesAvailable.update(table.size, list.appended(table))
      case None => tablesAvailable.put(table.size, List().appended(table))
    }
  }

  private def tablesAvailable(tables: List[Table], tablesAvailable: mutable.TreeMap[Int, List[Table]]): mutable.TreeMap[Int, List[Table]] = {
    tables.foreach(leaveTable(_, tablesAvailable))
    tablesAvailable
  }
}
