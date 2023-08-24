
import java.util.LinkedList;

public class AVLTree<T extends Comparable <T>>{
    private AVLNode<T> root;
    
    public AVLNode<T> getRoot (){
        return this.root;
    }

    public void setRoot(AVLNode<T> root){
        this.root = root;
    }

    public void put(T key){
        AVLNode<T> searchNode = this.root;
        AVLNode<T> newNode = new AVLNode<T>(key);
        if(searchNode ==null){
            this.root = newNode;
        }else{
            positionToPut(newNode,searchNode);
        }
    }

    private void positionToPut (AVLNode<T> newNode, AVLNode<T> searchNode) {
        if(searchNode == null){
            return;
        }
        if(searchNode.getInfo().compareTo(newNode.getInfo()) > 0){
            positionToPut(newNode, searchNode.getRight());
            searchNode.setRight(newNode);
            
        }else{
             positionToPut(newNode, searchNode.getLeft());
             searchNode.setLeft(newNode);
             
        }
    }

    public LinkedList<T> passeioEmOrdem(){
        AVLNode<T> searchNode = this.root;
        LinkedList<T> results = new LinkedList<T>();
        results = passeioEmOrdemOrganizar(searchNode, results);
        return results;
    }

    private LinkedList<T> passeioEmOrdemOrganizar(AVLNode<T> searchNode, LinkedList<T> results){
        if(searchNode.getLeft() != null){
            passeioEmOrdemOrganizar(searchNode.getLeft(), results);
        }
         results.addLast(searchNode.getInfo());
         if(searchNode.getRight() != null){
             passeioEmOrdemOrganizar(searchNode.getRight(), results);
         }
         return results;
    }

    public LinkedList<T> passeioPorNivel(){
        LinkedList<AVLNode<T>> searchQueue = new LinkedList<AVLNode<T>>();
        LinkedList<T> resultQueue = new LinkedList<T>();
        searchQueue.addLast(this.root);
        while(!searchQueue.isEmpty()){
            AVLNode<T> node = searchQueue.getFirst();
            resultQueue.addLast(node.getInfo());
            if(node.getLeft() != null){
                searchQueue.addLast(node.getLeft());
            }
             if(node.getRight() != null){
                searchQueue.addLast(node.getRight());
            }
        }
        return resultQueue;

    }
}
