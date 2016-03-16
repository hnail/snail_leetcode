package com.snail.others.tree;

import java.util.*;
/**
 * Created by wangguowei on 16-3-16.
 *      字典树
 *   1. 统计词频
 *   2. 字符串排序
 *   3. 最长公共前缀
 * @Description
 * @modify by
 */
public class Trie {

    private TrieNode root;
    public Trie() {
        root = new TrieNode();
    }
    public static void main(String[] args)
    {
        System.out.println("Hello World!");
    }
    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode p = root;
        for(int i = 0; i < word.length();i++){
            char c = word.charAt(i);
            if(p.nodes[c-'a'] == null){
                TrieNode tmp = new TrieNode(c);
                p.nodes[c - 'a'] = tmp;
                p = tmp;
            }else{
                p = p.nodes[c-'a'];
            }
        }
        p.flag = 1;
    }
    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode p = root;
        for(int i = 0; i < word.length();i++){
            char c = word.charAt(i);
            if(p.nodes[c-'a'] == null){
                return false;
            }else{
                p = p.nodes[c-'a'];
            }
        }
        if(p.flag == 1){
            return true;
        }else{
            return false;
        }
    }
    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode p = root;
        for(int i = 0; i < prefix.length();i++){
            char c = prefix.charAt(i);
            if(p.nodes[c-'a'] == null){
                return false;
            }else{
                p = p.nodes[c-'a'];
            }
        }
        return true;
    }
}
class TrieNode {
    // Initialize your data structure here.
    public char key;
    public TrieNode[] nodes;
    //flag == 1 表示是字符串
    //flag == 0 表示是前缀
    public int flag;
    //public int  count;统计词频
    public TrieNode() {
        key='\0';
        nodes = new TrieNode[26];
    }
    public TrieNode(char c){
        key = c;
        nodes = new TrieNode[26];
        flag = 0;
    }
}
