package application;

import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ParseMetro {

    public static void initGraph(Graph graph) throws Exception {

        URL metroURL = ParseMetro.class.getClassLoader().getResource("application/resources/metro.txt");
        assert metroURL != null;
        InputStreamReader metro = new InputStreamReader(metroURL.openStream(), StandardCharsets.UTF_8);

        Scanner sc = new Scanner(metro);
        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] s = line.split(" ");

            if(line.startsWith("V")) {
                String name = line.substring(line.indexOf(s[3]));
                int num = Integer.parseInt(s[1]);
                int l = Integer.parseInt(s[2]);

                Node newNode = new Node(name, num, l);
                graph.addNode(newNode);
            }

            if(line.startsWith("E")) {

                int src  = Integer.parseInt(s[1]);
                int des = Integer.parseInt(s[2]);
                int time = Integer.parseInt(s[3]);
                int srcTodes = Integer.parseInt(s[4]);
                int desTosrc = Integer.parseInt(s[5]);

                Node node_src = graph.getNodes(src);
                Node node_des = graph.getNodes(des);


                //Sens direct
                if(srcTodes != 9999) {
                    Node node_srcTodes = graph.getNodes(srcTodes);
                    node_src.addEdge(new Edge(node_des, time, node_srcTodes));
                }
                //Boucle
                else {
                    node_src.addEdge(new Edge(node_des, time, node_des));
                }

                //Arc orienté
                if(desTosrc == -1)  continue;

                //Sens indirect
                if(desTosrc != 9999) {
                    Node node_desTosrc = graph.getNodes(desTosrc);
                    node_des.addEdge(new Edge(node_src, time, node_desTosrc));
                }
                //Boucle
                else {
                    node_des.addEdge(new Edge(node_src, time, node_src));
                }
            }
        }
    }
}