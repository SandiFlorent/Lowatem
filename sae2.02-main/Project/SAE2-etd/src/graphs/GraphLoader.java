package graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GraphLoader {

    public static Graph loadFromFile(String nodeFileName, String edgeFileName) {
        if (nodeFileName == null) {
            return null;
        }
        Graph g = new Graph();
        HashMap<Integer, Node> nodeMap = loadNodesFromFile(g, nodeFileName);

        if (edgeFileName != null) {
            loadEdgesFromFile(g, nodeMap, edgeFileName);
        }
        return g;
    }

    /**
     * This function will load all the nodes of a graph from files and add it to
     * a graph
     *
     * @param g the graph to which we add the edges
     * @param nodeFileName the name of the file containing the nodes
     * @return a Map of all the nodes indexed by their id's
     */
    private static HashMap<Integer, Node> loadNodesFromFile(Graph g, String nodeFileName) {
        Path pathToFile = Paths.get(nodeFileName);
        HashMap<Integer, Node> nodeMap = new HashMap<>();

        try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.US_ASCII)) {

            String line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(";");
                if (attributes.length != 2) {
                    System.err.println("Error while loading nodes : " + attributes.length + " column(s)");
                    continue;
                }
                int id = Integer.parseInt(attributes[0]);
                String[] coords = attributes[1].split(" ");
                if (coords.length != 2) {
                    System.err.println("Error while loading nodes : coordinates have " + coords.length + " dimensions");
                    continue;
                }
                double x = Double.parseDouble(coords[0]);
                double y = Double.parseDouble(coords[1]);

                Node n = g.addNode();
                g.setNodePosition(n, new Coord(x, y));
                nodeMap.put(id, n);
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return nodeMap;
    }

    /**
     * This function will load the edges of a graph from files and add it to a
     * graph
     *
     * @param g the graph to which we add the edges
     * @param nodeMap the nodeMap computed elsewhere
     * @param edgeFileName the name of the file containing the edges
     */
    private static void loadEdgesFromFile(Graph g, HashMap<Integer, Node> nodeMap, String edgeFileName) {
        Path pathToFile = Paths.get(edgeFileName);
        try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.US_ASCII)) {
            HashSet<Edge> a = new HashSet<>();

            String line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(";");
                if (attributes.length != 2) {
                    System.err.println("Error while loading edges : " + attributes.length + " column(s)");
                    continue;
                }

                int srcInt = Integer.parseInt(attributes[0]);
                int tgtInt = Integer.parseInt(attributes[1]);
                Node src = nodeMap.get(srcInt);
                Node tgt = nodeMap.get(tgtInt);
                if (!g.existEdge(src, tgt, false)) {
                    Edge finalEdge = g.addEdge(src, tgt);
                    ArrayList<Coord> bends = new ArrayList<>();
                    g.setEdgePosition(finalEdge, bends);
                }
                line = br.readLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    // TODO
}
