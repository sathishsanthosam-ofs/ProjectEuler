import java.util.ArrayList;
import java.util.List;


public class Node {
	private String name;
	private Node child;
	private Node parent;
	private Node next;
	
	public Node(String name,Node parent){
		this.name = name;
		this.parent = parent;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Node getChild() {
		return child;
	}
	public void setChild(Node child) {
		this.child = child;
	}
	public Node getParent() {
		return parent;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}
	public Node getNext() {
		return next;
	}
	public void setNext(Node next) {
		this.next = next;
	}
	public String toString(){
		return "Node name : "+this.name + " Parent Name : " + this.parent.name; 
//		+ "Child Name : " + this.child.name + " Next : " + this.next.name;
	}

}
