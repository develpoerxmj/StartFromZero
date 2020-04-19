package com.xmj.startfromzero.sort.tree;

import java.util.NoSuchElementException;


/**
 * 二叉查找树
 */
public class BinarySearchTree {

    public TreeNode root;

    /**
     * 增加节点
     * @param data
     * @return
     */
    public TreeNode putNode(int data){
        if (root == null){
            root = new TreeNode(data);
            return root;
        }
        TreeNode node = root;
        TreeNode parent = null;
        while (node != null){
            parent = node;
            if (node.data > data){
                node = node.leftChild;
            }else if (node.data < data){
                node = node.rightChild;
            }else {
                return node;
            }
        }
        TreeNode newNode = new TreeNode(data);
        if (data < parent.data){
            parent.leftChild = newNode;
        }else {
            parent.rightChild = newNode;
        }
        newNode.parent = parent;
        return newNode;
    }

    /**
     * 删除节点
     * @param data
     * @return
     */
    public void deleteNode(int data){
        TreeNode node = searchNode(data);
        if (node == null){
            throw new NoSuchElementException();
        }
        TreeNode parent = node.parent;
        if (node.leftChild == null && node.rightChild == null){//没有左右节点1、根节点，2、叶子
            if (parent == null){
                //此节点为根节点
                root = null;
            }else {
                //此节点为叶
                if (parent.leftChild == node){
                    parent.leftChild = null;
                }else {
                    parent.rightChild = null;
                }
            }
            node.parent = null;
        }else if (node.leftChild != null && node.rightChild == null){//只有左节点
            if (parent == null){
                //此节点为根节点
                TreeNode leftNode = node.leftChild;
                leftNode.parent = null;
                root = leftNode;
            }else {
                if (parent.leftChild == node){//删除节点为其父节点的左节点
                    parent.leftChild = node.leftChild;
                }else {
                    parent.rightChild = node.leftChild;
                }
                node.leftChild.parent = parent;
            }
            node.leftChild = null;
            node.parent = null;
        }else if (node.leftChild == null && node.rightChild != null){//只有右节点
            if (parent == null){
                //此节点为根节点
                TreeNode rightNode = node.rightChild;
                rightNode.parent = null;
                root = rightNode;
            }else {
                if (parent.leftChild == node){//删除节点为其父节点的左节点
                    parent.leftChild = node.rightChild;
                }else {
                    parent.rightChild = node.rightChild;
                }
                node.rightChild.parent = parent;
            }
            node.rightChild = null;
            node.parent = null;
        }else {//左右节点都有，删除此节点右节点补上
            if (node.rightChild.leftChild == null){//删除的节点的右节点的左节点为空，直接补上
                TreeNode rightNode = node.rightChild;
                rightNode.leftChild = node.leftChild;
                if (parent == null){
                    rightNode.parent = null;
                    root = rightNode;
                }else {
                    if (parent.leftChild == node){
                        parent.leftChild = rightNode;
                    }else {
                        parent.rightChild = rightNode;
                    }
                    rightNode.parent = parent;
                }
            }else {
                //获取最小左节点
                TreeNode leftNode = getMinLeftTreeNode(node.rightChild);
                //最小左节点的左节点为删除节点的左节点，删除节点的父节点为最小左节点
                leftNode.leftChild = node.leftChild;
                node.leftChild.parent = leftNode;

                //将最小左节点的右节点变为最小左节点的父节点的左节点，最小左节点的右节点的父节点变为最小左节点的父节点
                TreeNode parentNode = leftNode.parent;
                parentNode.leftChild = leftNode.rightChild;
                leftNode.rightChild.parent = parentNode;

                //将最小左节点的右节点变为删除节点的右节点，删除节点的右节点的父节点变为最小左节点
                leftNode.rightChild = node.rightChild;
                node.rightChild.parent = leftNode;

                if (parent == null){
                    leftNode.parent = null;
                    root = leftNode;
                }else {
                    if (parent.leftChild == node){
                        parent.leftChild = leftNode;
                    }else {
                        parent.rightChild = leftNode;
                    }
                    leftNode.parent = parent;
                }
            }
            node.parent = null;
            node.leftChild = null;
            node.rightChild = null;
        }
    }

    /**
     * 获取当前节点最小的左节点
     * @param node
     * @return
     */
    private TreeNode getMinLeftTreeNode(TreeNode node) {
        TreeNode curNode = null;
        if (node == null){
            return null;
        }
        curNode = node;
        while (curNode.leftChild != null){
            curNode = curNode.leftChild;
        }
        return curNode;
    }

    /**
     * 修改节点
     * @param data
     * @return
     */
    public TreeNode changeNode(int key, int data){
        //找到节点，删除，再添加新的节点
        return null;
    }

    /**
     * 查询节点
     * @param data
     * @return
     */
    public TreeNode searchNode(int data){
        if (root == null){
            return null;
        }
        TreeNode node = root;
        while (node != null){
            if (node.data == data){
                return node;
            }else if (node.data > data){
                node = node.leftChild;
            }else {
                node = node.rightChild;
            }
        }
        return null;
    }

    /**
     * 中序遍历
     * @param root
     */
    public void midOrderTraverse(TreeNode root){
        if (root == null){
            return;
        }
        midOrderTraverse(root.leftChild);
        System.out.print(" " + root.data);
        midOrderTraverse(root.rightChild);
    }

    public class TreeNode{
        int data;
        TreeNode leftChild;
        TreeNode rightChild;
        TreeNode parent;

        public TreeNode(int data) {
            this.data = data;
            leftChild = null;
            rightChild = null;
            parent = null;
        }
    }

}
