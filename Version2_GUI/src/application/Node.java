package application;

import java.util.LinkedList;
import java.util.HashSet;

public class Node {

    private int num;
    private String name;
    private int line;
    private Integer distance = Integer.MAX_VALUE;
    private HashSet<Edge> edges = new HashSet<>();
    private LinkedList<Node> shortestPath = new LinkedList<>();

    public Node(String name, int num, int line) {
        this.name = name;
        this.num = num;
        this.line = line;
    }

    public int getNum() {
        return num;
    }
    public void setNum(int num) { this.num = num; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getLine() { return line; }
    public void setLine(int line) { this.line = line; }

    public Integer getDistance() {
        return distance;
    }
    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public HashSet<Edge> getEdges() { return edges; }
    public Edge getEdge(Node arr){
        for (Edge edge : edges) {
            if(edge.getArr().equals(arr))
                return edge;
        }
        return null;
    }
    public void setEdges(HashSet<Edge> edges) { this.edges = edges; }

    public LinkedList<Node> getShortestPath() {
        return shortestPath;
    }
    public void setShortestPath(LinkedList<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public void addEdge(Edge edge) { edges.add(edge); }

    public void reset() {
        this.setDistance(Integer.MAX_VALUE);
        this.shortestPath.clear();
    }
}