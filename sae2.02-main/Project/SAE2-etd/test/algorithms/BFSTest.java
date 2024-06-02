/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package algorithms;


import graphs.Coord;
import graphs.Edge;
import graphs.Graph;
import graphs.Node;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class BFSTest {

    private Graph graph;
    private Node node1, node2, node3, node4, node5;

    @Before
    public void setUp() {
        // Create a new graph
        graph = new Graph();

        // Create nodes with their coordinates
        node1 = new Node(new Coord(0, 0));
        node2 = new Node(new Coord(1, 1));
        node3 = new Node(new Coord(2, 2));
        node4 = new Node(new Coord(3, 3));
        node5 = new Node(new Coord(4, 4));

        // Add nodes to the graph
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);
        graph.addNode(node5);

        // Create edges with weights
        Edge edge1 = new Edge(node1, node2);
        Edge edge2 = new Edge(node1, node3);
        Edge edge3 = new Edge(node2, node3);
        Edge edge4 = new Edge(node2, node4);
        Edge edge5 = new Edge(node3, node4);
        Edge edge6 = new Edge(node3, node5);
        Edge edge7 = new Edge(node4, node5);

        // Add edges to the graph
        graph.addEdge(edge1);
        graph.addEdge(edge2);
        graph.addEdge(edge3);
        graph.addEdge(edge4);
        graph.addEdge(edge5);
        graph.addEdge(edge6);
        graph.addEdge(edge7);
    }

    @Test
    public void testBFSShortestPath() {
        BFS bfsAlgorithm = new BFS();
        ArrayList<Coord> path = bfsAlgorithm.bfsShortestPath(graph, node1, node5);
        System.out.println(" oui" +path);

        // Check that the path found is correct
        ArrayList<Coord> expectedPath = new ArrayList<>();
        expectedPath.add(node3.coordinates); 

        assertEquals(expectedPath, path);
    }

    @Test
    public void testBFSNoPath() {
        // Create a new disconnected graph
        Graph disconnectedGraph = new Graph();

        Node nodeA = new Node(new Coord(0, 0));
        Node nodeB = new Node(new Coord(1, 1));
        Node nodeC = new Node(new Coord(10, 10));
        Node nodeD = new Node(new Coord(11, 11));

        disconnectedGraph.addNode(nodeA);
        disconnectedGraph.addNode(nodeB);
        disconnectedGraph.addNode(nodeC);
        disconnectedGraph.addNode(nodeD);

        Edge edgeAB = new Edge(nodeA, nodeB);
        disconnectedGraph.addEdge(edgeAB);

        BFS bfsAlgorithm = new BFS();
        ArrayList<Coord> path = bfsAlgorithm.bfsShortestPath(disconnectedGraph, nodeA, nodeD);

        // Check that no path has been found
        assertTrue(path.isEmpty());
    }
}
