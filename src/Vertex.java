import java.util.ArrayList;
import java.util.List;


public class Vertex {
	private String name;
	private List<Vertex> childVertex = new ArrayList<Vertex>();
	
	public Vertex(String name){
		this.name = name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public List<Vertex> getChildVertex() {
		return childVertex;
	}
	public void addChildVertex(Vertex vertex){
		this.getChildVertex().add(vertex);
	}
}
