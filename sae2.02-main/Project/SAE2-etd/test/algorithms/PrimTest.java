package algorithms;

import graphs.Coord;
import graphs.Edge;
import graphs.Graph;
import graphs.Node;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PrimTest {

    private Graph graph;
    private Node node1, node2, node3, node4, node5;

    @Before
    public void setUp() {
        // Create a new grapg 
        graph = new Graph();

        // Create nodes with coordinates
        node1 = new Node(new Coord(0, 0));
        node2 = new Node(new Coord(1, 1));
        node3 = new Node(new Coord(2, 2));
        node4 = new Node(new Coord(3, 3));
        node5 = new Node(new Coord(4, 4));

        // Adding nodes to the graph
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);
        graph.addNode(node5);

        // Creating edges with weights
        Edge edge1 = new Edge(node1, node2);
        Edge edge2 = new Edge(node1, node3);
        Edge edge3 = new Edge(node2, node3);
        Edge edge4 = new Edge(node2, node4);
        Edge edge5 = new Edge(node3, node4);
        Edge edge6 = new Edge(node3, node5);
        Edge edge7 = new Edge(node4, node5);

        // Adding edges to the graph
        graph.addEdge(edge1);
        graph.addEdge(edge2);
        graph.addEdge(edge3);
        graph.addEdge(edge4);
        graph.addEdge(edge5);
        graph.addEdge(edge6);
        graph.addEdge(edge7);
    }

    @Test
    public void testPrim() {
        // Run Prim's algorithm
        Prim primAlgorithm = new Prim();
        Graph mst = primAlgorithm.prim(graph);

        // Check the number of nodes and edges in the MST
        assertEquals(5, mst.numberOfNodes());
        assertEquals(4, mst.numberOfEdges());

        // Check that the MST contains the correct edges
        assertTrue(mst.existEdge(node1, node2, false) ||
                   mst.existEdge(node2, node1, false));
        assertTrue(mst.existEdge(node2, node3, false) ||
                   mst.existEdge(node3, node2, false));
        assertTrue(mst.existEdge(node3, node4, false) ||
                   mst.existEdge(node4, node3, false));
        assertTrue(mst.existEdge(node4, node5, false) ||
                   mst.existEdge(node5, node4, false));
    }
}
