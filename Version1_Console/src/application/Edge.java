package application;

public class Edge {

    private Node arr;
    private int distance;
    private Node teminus;

    public Edge(Node arr, int distance, Node terminus) {
        this.arr = arr;
        this.distance = distance;
        this.teminus = terminus;
    }
    public Node getArr() { return arr; }
    public void setArr(Node arr) { this.arr = arr; }

    public int getDistance() { return distance; }
    public void setDistance(int distance) { this.distance = distance; }

    public Node getTeminus() { return teminus; }
    public void setTeminus(Node teminus) { this.teminus = teminus; }

}