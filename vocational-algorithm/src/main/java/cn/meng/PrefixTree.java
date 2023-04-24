package cn.meng;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;


public class PrefixTree {
    public static void main(String[] args) {
        PrefixTree prefixTree = new PrefixTree();
        prefixTree.buildTree("hello");
//        System.out.println(prefixTree.root.getChildren());
//        System.out.println(prefixTree.root.getChildren().get(0).getChildren());
//        System.out.println(prefixTree.root.getChildren().get(0).getChildren().get(0).getChildren());
//        System.out.println(prefixTree.root.getChildren().get(0).getChildren().get(0).getChildren().get(0).getChildren());
//        System.out.println(prefixTree.root.getChildren().get(0).getChildren().get(0).getChildren().get(0).getChildren().get(0).getChildren().get(0));
//        System.out.println(prefixTree.root.getChildren().get(0).getChildren().get(0).getChildren().get(0).getChildren().get(0).getChildren().get(0).getContent());

        prefixTree.buildTree("hellow");

        System.out.println(prefixTree.retrieve("hello"));
        System.out.println(prefixTree.retrieve("hellow"));

    }

    Node root;
    public PrefixTree(){
        this.root = new Node(null);
    }
    public String buildTree(String word){
        if(word.isEmpty()){
            return "";
        }else{
            addNode(root,word,word);
        }
        return word;

    }

    public String retrieve(String key){

        return findContent(root,key);

    }

    private String  findContent(Node nodeC,String key) {
        if(key.length() == 1){
            for (Node child : nodeC.getChildren()) {
                if(child.element == key.charAt(0)){
                    if(!child.content.isEmpty()){
                        return child.content;
                    }else{
                        return "result is empty";
                    }
                }
            }
        }else{
            for (Node child : nodeC.getChildren()) {
                if(child.element == key.charAt(0)){
                    return findContent(child,key.substring(1));
                }
            }
        }
        return "result is empty";
    }

    private boolean addNode(Node nodeC, String rest,String word) {
        Node temp = new Node(rest.charAt(0));
        if(rest.length() == 1){
            boolean contains = nodeC.getChildren().contains(temp);
            if(!contains){
                temp.setContent(word);
                nodeC.setChild(temp);
            }

        }else{
            int index = nodeC.getChildren().indexOf(temp);
            if(index != -1){
                addNode(nodeC.getChildren().get(index),rest.substring(1),word);
            }else{
                nodeC.setChild(temp);
                addNode(temp,rest.substring(1),word);
            }
        }
        return true;
    }


    static class Node{

        Character element;
//        Node parent;
        List<Node> children;
        String content;
        Node(Character element){
            this.element = element;
            this.children = new LinkedList<>();
        }


        public Character getElement() {
            return element;
        }

        public void setElement(Character element) {
            this.element = element;
        }

      /*  public Node getPrevious() {
            return previous;
        }*/

       /* public void setPrevious(Node previous) {
            this.previous = previous;
        }*/

        public List<Node> getChildren() {
            return children;
        }

        public boolean setChild(Node child) {
            return addChild(child);

        }

        private boolean addChild(Node child) {
            LinkedList<Node> dupChildren = (LinkedList<Node>) children;
            int mark = dupChildren.size();
            if(dupChildren.size() == 0){
                children.add(child);
            }else if(child.element <= dupChildren.getFirst().element){
                dupChildren.addFirst(child);
            }else if(child.element >= dupChildren.getLast().element){
                dupChildren.addLast(child);
            }
            else{
                int left = 0;
                int right = dupChildren.size()-1;
                while (left < right){
                    int mid = (left + right)/2;
                    if(dupChildren.get(mid).element <= child.element){
                        if(dupChildren.get(mid+1).element > child.element){
                            dupChildren.add(mid+1,child);
                            break;
                        }else{
                            left = mid;
                        }
                    }else{
                        if(dupChildren.get(mid).element > child.element){
                            //bug expected without '='
                            if(dupChildren.get(mid-1).element <  child.element){
                                dupChildren.add(mid,child);
                                break;
                            }else{
                                right = mid;
                            }
                        }

                    }
                }
            }
            return mark != dupChildren.size();

        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        @Override
        public String toString() {
            if(element == null){
                return "null";
            }
            return element.toString();
        }

        @Override
        public boolean equals(Object o) {
            return this.element == ((Node)o).element;
        }


    }
}
