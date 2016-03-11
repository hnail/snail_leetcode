package com.snail.others.tree;

import com.sun.org.apache.xpath.internal.WhitespaceStrippingElementMatcher;

import java.util.Stack;

/**
 * Created by wangguowei on 16-3-10.
 */
@SuppressWarnings("unchecked")
public class BinaryTree {
    private char date;
    private BinaryTree lchild;
    private BinaryTree rchild;

    public BinaryTree(char c) {
        date = c;
    }

    // 先序遍历递归
    public static void preOrder(BinaryTree t) {
        if (t == null) {
            return;
        }
        System.out.print(t.date);
        preOrder(t.lchild);
        preOrder(t.rchild);
    }

    // 中序遍历递归
    public static void InOrder(BinaryTree t) {
        if (t == null) {
            return;
        }
        InOrder(t.lchild);
        System.out.print(t.date);
        InOrder(t.rchild);
    }

    // 后序遍历递归
    public static void PostOrder(BinaryTree t) {
        if (t == null) {
            return;
        }
        PostOrder(t.lchild);
        PostOrder(t.rchild);
        System.out.print(t.date);
    }

    // 先序遍历非递归
    public static void preOrder2(BinaryTree t) {
        Stack<BinaryTree> s = new Stack<BinaryTree>();
        while (t != null || !s.empty()) {
            while (t != null) {
                System.out.print(t.date);
                s.push(t);
                t = t.lchild;
            }
            if (!s.empty()) {
                t = s.pop();
                t = t.rchild;
            }
        }
    }

    //自己练习非递归前序遍历二叉树
    public static void myPreOrder2(BinaryTree t) {
        Stack<BinaryTree> stack = new Stack<BinaryTree>();
        while (t!=null || !stack.empty()) {
            //先向左探索,同时将路径压入栈
            while (t != null) {
                System.out.println(t.date);
                stack.push(t);
                t = t.lchild;
            }
            if (t == null && !stack.empty()){// 如果无左子树了,则栈顶弹出父节点来获取右子树,继续左序遍历
                t= stack.pop();
                t = t.rchild;
            }
        }
    }

    public static void myPreasd(BinaryTree t) {
        Stack<BinaryTree> s = new Stack<BinaryTree>();
       while (!s.isEmpty()){
           while (t!=null){
               System.out.println(t.date);
               s.push(t);
               t=t.lchild;
           }
           if(t==null && !s.empty()){
               t = s.pop();
               t=t.rchild;
           }
       }
    }


    // 中序遍历非递归
    public static void InOrder2(BinaryTree t) {
        Stack<BinaryTree> s = new Stack<BinaryTree>();
        while (t != null || !s.empty()) {
            while (t != null) {
                s.push(t);
                t = t.lchild;
            }
            if (!s.empty()) {
                t = s.pop();
                System.out.print(t.date);
                t = t.rchild;
            }
        }
    }

    // 后序遍历非递归
    public static void PostOrder2(BinaryTree t) {
        Stack<BinaryTree> s = new Stack<BinaryTree>();
        Stack<Integer> s2 = new Stack<Integer>();
        Integer i = 1;
        while (t != null || !s.empty()) {
            while (t != null) {
                s.push(t);
                s2.push(0);
                t = t.lchild;
            }
            while (!s.empty() && s2.peek().equals(i)) {
                s2.pop();
                System.out.print(s.pop().date);
            }

            if (!s.empty()) {
                s2.pop();
                s2.push(1);
                t = s.peek();
                t = t.rchild;
            }
        }
    }

    public static void main(String[] args) {
        BinaryTree b1 = new BinaryTree('a');
        BinaryTree b2 = new BinaryTree('b');
        BinaryTree b3 = new BinaryTree('c');
        BinaryTree b4 = new BinaryTree('d');
        BinaryTree b5 = new BinaryTree('e');

        /**
         *      a
         *     / /
         *    b   c
         *   / /
         *  d   e
         */
        b1.lchild = b2;
        b1.rchild = b3;
        b2.lchild = b4;
        b2.rchild = b5;

        BinaryTree.preOrder(b1);
        System.out.println();
        BinaryTree.preOrder2(b1);
        System.out.println();
        BinaryTree.InOrder(b1);
        System.out.println();
        BinaryTree.InOrder2(b1);
        System.out.println();
        BinaryTree.PostOrder(b1);
        System.out.println();
        BinaryTree.PostOrder2(b1);
    }
}
