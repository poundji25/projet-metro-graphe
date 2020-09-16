package application;

import java.util.ArrayList;

public class Graph {

    private final ArrayList<Node> graph = new ArrayList<>();

    public void addNode(Node nodeA) {
        graph.add(nodeA);
    }

    public Node getNodes(String name) throws Exception{
        for (Node node: graph) {
            if(node.getName().equals(name))
                return node;
        }
        throw new Exception("Station non trouvée : " + name);
    }

    public Node getNodes(int num) {
        for (Node node: graph)
            if(node.getNum() == num) return node;
        return null;
    }

    public ArrayList<String> getList() {
        ArrayList<String> list = new ArrayList<>();
        for (Node node : graph) {
            if(!list.contains(node.getName()))
                list.add(node.getName());
        }
        return list;
    }

    public void reset(){
        for (Node node : graph) {
            node.reset();
        }
    }

    public String ligneB(int ligne) {
        if(ligne <= 14)
            return String.format("%s", ligne);
        //Ligne 70 -> 7bis
        //Ligne 30 -> 3bis
        return String.format("%sbis", ligne/10);
    }

    public String getPath(Node start, Node arr){
        reset();
        StringBuilder path = new StringBuilder();
        Dijkstra.calculateShortestPathFromSource(start);
        path.append(String.format(("Vous êtes à %s\n"), start.getName()));
        arr.getShortestPath().add(arr);

        //
        Node nodeA = arr.getShortestPath().get(0);
        Node nodeB = arr.getShortestPath().get(1);
        Edge e = nodeA.getEdge(nodeB);
        int ligne = nodeA.getLine();
        Node term = nodeA.getEdge(nodeB).getTerminus();
        int i = 1;

        //
        if(nodeA.getName().equals(nodeB.getName())) {
            nodeA = arr.getShortestPath().get(1);
            nodeB = arr.getShortestPath().get(2);
            e = nodeA.getEdge(nodeB);
            ligne = nodeA.getLine();
            term = nodeA.getEdge(nodeB).getTerminus();
            i = 2;
        }

        path.append(String.format("Prenez la ligne, %s direction %s.\n",
                ligneB(ligne), e.getTerminus().getName()));

        //
        for (;i < arr.getShortestPath().size()-1; i++) {
            nodeA = arr.getShortestPath().get(i);
            nodeB = arr.getShortestPath().get(i+1);
            e = nodeA.getEdge(nodeB);
            Node t = nodeA.getEdge(nodeB).getTerminus();
            if(t != term && !nodeA.getName().equals(nodeB.getName())) {

                path.append(String.format("À %s, prenez la ligne %s, direction %s.\n", nodeA.getName(),
                        ligneB(nodeA.getLine()), e.getTerminus().getName()));
                term = t;
            }
        }
        path.append(String.format("Vous devriez arriver à %s dans ", arr.getName()));
        if(arr.getDistance()/60 != 0) {
            path.append(String.format("%d minutes ", arr.getDistance() / 60));
        }
        path.append(String.format("%d secondes.\n", arr.getDistance() % 60));

        return path.toString();
    }

    public String getPath(String start, String arr) {
        try {
            Node nodeA = getNodes(start);
            Node nodeB = getNodes(arr);
            return getPath(nodeA, nodeB);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}