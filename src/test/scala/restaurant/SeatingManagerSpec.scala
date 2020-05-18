package restaurant

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import scala.collection.mutable

final class SeatingManagerSpec extends AnyWordSpec with Matchers {
  "Given n Tables should locate all the customer groups" in {
    val tables = List(
      Table(6),
      Table(2),
      Table(2),
      Table(4),
    )
    val seatingManager = SeatingManager(tables)

    val group1 = CustomerGroup(2)
    seatingManager.arrives(group1)

    val group2 = CustomerGroup(2)
    seatingManager.arrives(group2)

    val group3 = CustomerGroup(4)
    seatingManager.arrives(group3)

    val group4 = CustomerGroup(6)
    seatingManager.arrives(group4)

    seatingManager.tablesAvailable shouldEqual mutable.TreeMap[Int, List[Table]]()

    seatingManager.customersGroupsQueue shouldEqual mutable.LinkedHashSet[CustomerGroup]()

    seatingManager.customersGroupsSeated shouldEqual mutable.TreeMap(
      2 -> List(
        Table(2),
        Table(2)
      ),
      4 -> List(
        Table(4)
      ),
      6 -> List(
        Table(6),
      ))
  }
}