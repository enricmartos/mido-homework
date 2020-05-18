package treenode

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

final class TreeNodeSpec extends AnyWordSpec with Matchers {
  "Given a Tree should return the correct Lowest Common Ancestor" in {
    val rootNode = TreeNode(null, null)
    val node40 = TreeNode(rootNode, "40")
    val node20 = TreeNode(node40, "20")
    val node30 = TreeNode(node20, "30")
    val node10 = TreeNode(node20, "10")
    val node5 = TreeNode(node10, "5")

    val node60 = TreeNode(node40, "60")
    val node50 = TreeNode(node60, "50")
    val node70 = TreeNode(node60, "70")
    val node55 = TreeNode(node50, "30")

    node5.findFirstCommonAncestor(node55) shouldBe node40
    node5.findFirstCommonAncestor(node30) shouldBe node20
    node60.findFirstCommonAncestor(node70) shouldBe node60
  }
}
