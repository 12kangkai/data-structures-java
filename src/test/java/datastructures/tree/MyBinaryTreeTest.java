package datastructures.tree;

import datastructures.tree.examples.MyBinaryTree;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MyBinaryTreeTest {

    @Test

    void testAddAndTraverse() {

        MyBinaryTree<String> tree = buildTree();

        assertEquals(
                List.of("A", "B", "D", "E", "C", "F", "G"),
                tree.preorder()
        );

        assertEquals(
                List.of("D", "B", "E", "A", "F", "C", "G"),
                tree.inorder()
        );

        assertEquals(
                List.of("D", "E", "B", "F", "G", "C", "A"),
                tree.postorder()
        );

        assertEquals(
                List.of("A", "B", "C", "D", "E", "F", "G"),
                tree.levelOrder()
        );

    }

    @Test

    void testAddLeftAndRight() {

        MyBinaryTree<String> tree = new MyBinaryTree<>("A");

        MyBinaryTree.Node<String> root = tree.getRoot();

        MyBinaryTree.Node<String> b = tree.addLeft(root, "B");

        MyBinaryTree.Node<String> c = tree.addRight(root, "C");

        assertNotNull(b);

        assertNotNull(c);

        assertEquals("B", root.getLeft().getValue());

        assertEquals("C", root.getRight().getValue());

    }

    @Test

    void testAddLeftFailedWhenLeftAlreadyExists() {

        MyBinaryTree<String> tree = new MyBinaryTree<>("A");

        MyBinaryTree.Node<String> root = tree.getRoot();

        MyBinaryTree.Node<String> b = tree.addLeft(root, "B");

        MyBinaryTree.Node<String> failed = tree.addLeft(root, "X");

        assertNotNull(b);

        assertNull(failed);

        assertEquals("B", root.getLeft().getValue());

    }

    @Test

    void testDfsFind() {

        MyBinaryTree<String> tree = buildTree();

        assertNotNull(tree.dfsFind("E"));

        assertEquals("E", tree.dfsFind("E").getValue());

        assertNull(tree.dfsFind("X"));

    }

    @Test

    void testBfsFind() {

        MyBinaryTree<String> tree = buildTree();

        assertNotNull(tree.bfsFind("G"));

        assertEquals("G", tree.bfsFind("G").getValue());

        assertNull(tree.bfsFind("X"));

    }

    @Test

    void testUpdate() {

        MyBinaryTree<String> tree = buildTree();

        MyBinaryTree.Node<String> node = tree.dfsFind("E");

        assertTrue(tree.update(node, "X"));

        assertNull(tree.dfsFind("E"));

        assertNotNull(tree.dfsFind("X"));

        assertEquals("X", node.getValue());

    }

    @Test

    void testUpdateNullNode() {

        MyBinaryTree<String> tree = buildTree();

        assertFalse(tree.update(null, "X"));

    }

    @Test

    void testRemoveLeafNode() {

        MyBinaryTree<String> tree = buildTree();

        MyBinaryTree.Node<String> g = tree.dfsFind("G");

        assertTrue(tree.remove(g));

        assertNull(tree.dfsFind("G"));

        assertEquals(
                List.of("A", "B", "D", "E", "C", "F"),
                tree.preorder()
        );

    }

    @Test

    void testRemoveSubtree() {

        MyBinaryTree<String> tree = buildTree();

        MyBinaryTree.Node<String> c = tree.dfsFind("C");

        assertTrue(tree.remove(c));

        assertNull(tree.dfsFind("C"));

        assertNull(tree.dfsFind("F"));

        assertNull(tree.dfsFind("G"));

        assertEquals(
                List.of("A", "B", "D", "E"),
                tree.preorder()
        );

    }

    @Test

    void testRemoveRoot() {

        MyBinaryTree<String> tree = buildTree();

        assertTrue(tree.remove(tree.getRoot()));

        assertNull(tree.getRoot());

        assertEquals(List.of(), tree.preorder());

        assertEquals(List.of(), tree.inorder());

        assertEquals(List.of(), tree.postorder());

        assertEquals(List.of(), tree.levelOrder());

    }

    @Test

    void testRemoveNullNode() {

        MyBinaryTree<String> tree = buildTree();

        assertFalse(tree.remove(null));

    }

    @Test

    void testDuplicateValuesCanStillUseNodeReference() {

        MyBinaryTree<String> tree = new MyBinaryTree<>("A");

        MyBinaryTree.Node<String> root = tree.getRoot();

        MyBinaryTree.Node<String> leftB = tree.addLeft(root, "B");

        MyBinaryTree.Node<String> rightB = tree.addRight(root, "B");

        assertNotSame(leftB, rightB);

        assertEquals(List.of("A", "B", "B"), tree.preorder());

        assertTrue(tree.remove(rightB));

        assertEquals(List.of("A", "B"), tree.preorder());

        assertSame(leftB, root.getLeft());

        assertNull(root.getRight());

    }

    private MyBinaryTree<String> buildTree() {

        MyBinaryTree<String> tree = new MyBinaryTree<>("A");

        MyBinaryTree.Node<String> root = tree.getRoot();

        MyBinaryTree.Node<String> b = tree.addLeft(root, "B");

        MyBinaryTree.Node<String> c = tree.addRight(root, "C");

        tree.addLeft(b, "D");

        tree.addRight(b, "E");

        tree.addLeft(c, "F");

        tree.addRight(c, "G");

        return tree;

    }


}
