# Midokura Software Engineer - Homework

## TreeNode

You have the following Java class:

```java
public class TreeNode {
TreeNode parent;
TreeNode findFirstCommonAncestor(TreeNode other);
}
```

Implement the 'findFirstCommonAncestor' method that given another TreeNode
finds the first (deepest in the tree) common ancestor of 'this' TreeNode and
the one passed to the method. Assume that each TreeNode is a node in a
binary tree. You may add methods but not data members to the TreeNode class.

## Restaurant

Design/implement a system that manages seating for a Restaurant.

Our restaurant has round tables. Tables come in different sizes that can
accommodate 2, 3, 4, 5 or 6 people. People arrive at our restaurant in
groups of 6 or less. People in the same group want to be seated at the same
table. You can seat a group at any table that has enough empty seats for
them. If it's not possible to accommodate them, they're willing to wait.

Once they're seated, they can stay as long as they want and you cannot ask
them to move to another table (i.e. you cannot move them to make space for
another group). In terms of fairness of seating order: seat groups in the
order they arrive, but seat opportunistically. For example: a group of 6 is
waiting for a table and there are 4 empty seats at a table for 6; if a group
of 2 arrives you may put them at the table for 6 but only if you have
nowhere else to put them. This may mean that the group of 6 waits a long
time, possibly until they become frustrated and leave.

```java
public class Table {
    public final int size; //number of chairs around this table
}

public class CustomerGroup {
    public final int size; //number of people in the group
}

public class SeatingManager {
    /* Constructor */
    public SeatingManager(List tables);
    /* Group arrives and wants to be seated. */
    public void arrives(CustomerGroup group);
    /* Whether seated or not, the group leaves the restaurant. */
    public void leaves(CustomerGroup group);
    /* Return the table at which the group is seated, or null if
    they are not seated (whether they're waiting or already left). */
    public Table locate(CustomerGroup group);
}
```

Choose the data structures you need for the SeatingManager and then
implement the constructor and public methods (arrives, leaves, locate). You
may modify these classes in any way you like, and you may add other classes
as needed.