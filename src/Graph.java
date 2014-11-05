import java.util.List;


public class Graph {
	private Vertex root;
	
	public  Graph( Vertex root){
		this.root = root;
	}
	public void insertVertex(Vertex vertex,Vertex parent){
		if(!parent.getChildVertex().contains(vertex))
		parent.addChildVertex(vertex);
	}
	
//	public Node getNode(String nodeName){
//		Node parent = root;
//		while(!parent.getName().equals(nodeName)){
//			List<Node> childNodes = parent.getChildNodes();
//			for (Node node : childNodes) {
//				
//			}
//		}
//		return parent;
//	}
	public Vertex getRoot() {
		return root;
	}

	public void setRoot(Vertex root) {
		this.root = root;
	}
	
	
}
