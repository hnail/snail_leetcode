package com.snail.others.tree;
import java.util.*;

/**
 * Created by wangguowei on 16-3-16.
 * @Description
 * @modify by
 */
public class AVL {
    private static Node root = null;
    private static int count = 0;
    public static void main(String[] args)
    {
        System.out.println("Hello World!");
        int[] values = new int[]{53, 30, 72, 14, 39, 9, 23,15,88};
        //int[] values = createData();
        System.out.println("原始数组：" + Arrays.toString(values));
        createAVL(values);
        printAVL();
        //System.out.println(height(root.left));
        //System.out.println(height(root.right));
    }
    /*
    *   创建测试数据
    */
    public static int[] createData(){
        int size = new Random().nextInt(20);
        while(size == 0){
            size = new Random().nextInt(20);
        }
        int[] values = new int[size];
        for(int i = 0; i < size; i ++){
            values[i] = new Random().nextInt(100);
        }
        return values;
    }
    /*
    *   构造树
    */
    public static void createAVL(int[] values){
        for(int i = 0; i < values.length; i ++){
            addNode(values[i]);
        }
    }
    /*
    *   添加节点
    */
    public static void addNode(int key){
        Node<Integer> tmp = new Node(key);
        if(root == null){
            root = tmp;
        }else{
            insert(tmp);
            balance(tmp);
        }
    }
    /*
    *   插入节点
    */
    public static void insert(Node<Integer> tmp){
        Node<Integer> q = root;
        while(true){
            if(tmp.data == q.data){
                return;
            }else if(tmp.data < q.data){
                if(q.left == null){
                    q.left = tmp;
                    break;
                }
                q = q.left;
            }else{
                if(q.right == null){
                    q.right = tmp;
                    break;
                }
                q = q.right;
            }
        }
    }
    /*
    *   平衡节点
    *   由于插入tmp节点，树失去了平衡
    */
    public static void balance(Node<Integer> tmp){
        //p是离插入节点最近的非平衡节点
        Node<Integer> p = null;
        //q用来遍历AVL树
        Node<Integer> q = null;
        q = root;
        //1. 寻找离tmp最近的非平衡点q
        while(q != null){
            int hL = height(q.left);
            int hR = height(q.right);
            if(((hL - hR) == 2) || ((hL - hR)== -2)){
                //此节点是非平衡点,用p标记
                p = q;
            }

            if(root!=null && p!=null ){
                System.out.println("非平衡节点是否为root :"+root.data +" p: " + p.data );
            }

            ///一直顺着大小查找,直到找到使二叉树非平衡的点p
            if(tmp.data < q.data){
                q = q.left;
            }else if(tmp.data > q.data){
                q = q.right;
            }else{
                q = null;
            }
        }
        if(p == null){
            //不存在非平衡点
            return;
        }
        //2. 判断属于哪种情况
        //2.1 在p的左孩子的左子树中插入节点--  右旋
        //2.2 在p的左孩子的右子树中插入节点--  先左再右
        //2.3 在p的右孩子的左子树中插入节点--  先右再左
        //2.4 在p的右孩子的右子树中插入节点--  左旋
        if(tmp.data < p.data){
            if(tmp.data < p.left.data){
                //右旋
                singleRotate(p,false);
                //System.out.println("平衡之后：");
                //printAVL();
            }else{
                //左-右，先左旋后右旋
                //System.out.println("左-右");
                doubleRotate(p,true);
            }
        }else{
            if(tmp.data > p.right.data){
                //左旋
                    singleRotate(p,true);
            }else{
                //右-左
                //System.out.println("右-左");
                doubleRotate(p,false);
            }
        }
    }
    /*
    *   将以p为根的树单次旋转
    */
    public static void singleRotate(Node<Integer> p,boolean flag){
        //左旋
        if(flag){
            Node<Integer> r = p.right;
            Node tmp = Node.copy(p);
            tmp.right = r.left;
            p.right = r.right;
            p.data = r.data;
            p.left = tmp;
        }
        //右旋
        else{
            Node<Integer> left = p.left;
            Node tmp = Node.copy(p);
            tmp.left = left.right;
            p.left = left.left;
            p.data = left.data;
            p.right = tmp;
        }
    }
    /*
    *   以p为根的双旋
    */
    public static void doubleRotate(Node<Integer> p,boolean flag){
        //先左旋后右旋
        if(flag){
            Node<Integer> q = p.left;
            singleRotate(q,true);
            singleRotate(p,false);
        }
        //先右旋后左旋
        else{
            Node<Integer> q = p.right;
            singleRotate(q,false);
            singleRotate(p,true);
        }
    }
    /*
    *   二叉树的高度
    */
    public static int height(Node<Integer> p){
        if(p == null){
            return 0;
        }
        int hL = height(p.left);
        int hR = height(p.right);
        return (hL > hR ? hL: hR) + 1;
    }
    /*
    *   按层次输出树，空节点以"N"表示
    */
    public static void printAVL(){
        BTreePrinter.printNode(root);
    /*
        Queue<Node> queue = new LinkedList<Node>();
        System.out.print("[");
        if(root != null){
            queue.offer(root);
            Node<Integer> p = null;
            while(!queue.isEmpty()){
                p = queue.poll();
                if(p != null){
                    System.out.print(p.data+",");
                    if(p.left != null){
                        queue.offer(p.left);
                    }else{
                        queue.offer(null);
                    }
                    if(p.right != null){
                        queue.offer(p.right);
                    }else{
                        queue.offer(null);
                    }
                }
                else
                    System.out.print("N,");
            }
        }
        System.out.println("]");*/
    }
}

