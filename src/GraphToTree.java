import java.util.List;
import java.util.Map;
import java.util.Set;


public class GraphToTree {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Vertex a = new Vertex("a");
		Vertex b = new Vertex("b");
		Vertex c = new Vertex("c");
		Vertex d = new Vertex("d");
		Vertex e = new Vertex("e");
		Vertex f = new Vertex("f");	
		Vertex h = new Vertex("h");	
		Vertex i = new Vertex("i");	
		a.addChildVertex(b);
		a.addChildVertex(c);
		a.addChildVertex(d);
		a.addChildVertex(e);
		c.addChildVertex(b);
		c.addChildVertex(e);
		e.addChildVertex(b);
		b.addChildVertex(f);
		b.addChildVertex(h);
		c.addChildVertex(i);
		Graph graph = new Graph(a);
		GraphToTree g = new GraphToTree();
		Tree tree = g.convert(graph);
		Map<String, Node> childMap = tree.getChildNodeMap();
		Set<String> ketSet = childMap.keySet();
		for (String string : ketSet) {
			Node node = childMap.get(string);
			System.out.println(node);
		}
	}
	public Tree convert(Graph graph){
		Vertex root = graph.getRoot();
		Node rootNode = new Node("a",null);
		Tree tree = new Tree(rootNode);
		getChildNodes(root, rootNode, tree);
		return tree;
	}
	public void getChildNodes(Vertex root,Node parent, Tree tree){
		List<Vertex> childVertexs = root.getChildVertex();
		Node node = null;
		Node previousNode = null;
		for (Vertex vertex : childVertexs) {			
			 node = new Node(vertex.getName(),parent);
			 if(previousNode != null){
				 previousNode.setNext(node); 
			 }
			if(!(tree.getChildNodeMap().containsKey(node.getName()))){
				tree.insertNode(node.getName(),node);
			}
			previousNode = node;
		}
		for (int i = 0; i < childVertexs.size(); i++) {			
			getChildNodes(childVertexs.get(i),tree.getChildNodeMap().get(childVertexs.get(i).getName()),tree);
		}	
	}
}
