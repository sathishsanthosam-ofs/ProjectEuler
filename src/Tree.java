import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Tree {
	private Node root;
	private Map<String, Node> childNodeMap = new HashMap<String, Node>();
	public  Tree( Node root){
		this.root = root;
	}
	public void insertNode(String name , Node node){
		this.getChildNodeMap().put(name, node);
	}
	public Node getRoot() {
		return root;
	}
	public void setRoot(Node root) {
		this.root = root;
	}
	public Map<String, Node> getChildNodeMap() {
		return childNodeMap;
	}
	
}
