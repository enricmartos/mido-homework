package treenode

import scala.annotation.tailrec

case class TreeNode(private val parent: TreeNode, private val value: String) {
  def findFirstCommonAncestor(other: TreeNode): TreeNode = lowestCommonAncestor(this, this, other)

  @tailrec
  private def lowestCommonAncestor(root: TreeNode, a: TreeNode, b: TreeNode): TreeNode = {
    if (a != null && b == null) {
      return null
    }

    if (a.parent == null && b.parent == null) {
      return null
    }

    if (a.parent == b.parent) {
      return a.parent
    }

    if (a == b.parent) {
      return a
    }

    if (a.parent == null && b.parent != null) lowestCommonAncestor(root, root, b.parent) else lowestCommonAncestor(root, a.parent, b)
  }

  override def toString: String = value
}
