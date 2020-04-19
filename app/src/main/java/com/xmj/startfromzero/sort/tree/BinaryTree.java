package com.xmj.startfromzero.sort.tree;

/**
 * 二叉树
 */
public class BinaryTree {

    Node<String> root;

    public BinaryTree() {
        root = new Node<>("A", null, null);
        createTree();
    }

    public void createTree(){
        Node nodeG = new Node("G", null, null);
        Node nodeH = new Node("H", null, null);
        Node nodeI = new Node("I", null, null);
        Node nodeD = new Node("D", nodeG, nodeH);
        Node nodeE = new Node("E", null, nodeI);
        Node nodeF = new Node("F", null, null);
        Node nodeB = new Node("B", nodeD, null);
        Node nodeC = new Node("C", nodeE, nodeF);
        root.left = nodeB;
        root.right = nodeC;
    }

    /**
     * 节点
     * @param <E>
     */
    public class Node<E>{
        private E e;
        private Node<E> left;
        private Node<E> right;

        public Node(E e, Node<E> left, Node<E> right) {
            this.e = e;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 中序遍历
     * @param root
     */
    public void lDR(Node root){
        if (root == null){
            return;
        }
        lDR(root.left);
        System.out.print(" " + root.e);
        lDR(root.right);
    }

    /**
     * 前序遍历
     * @param root
     */
    public void dLR(Node root){
        if (root == null){
            return;
        }
        System.out.print(" " + root.e);
        dLR(root.left);
        dLR(root.right);
    }

    /**
     * 后序遍历
     * @param root
     */
    public void lRD(Node root){
        if (root == null){
            return;
        }
        lRD(root.left);
        lRD(root.right);
        System.out.print(" " + root.e);
    }
}
