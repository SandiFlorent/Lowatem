/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package graphs;

import java.util.ArrayList;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import org.junit.Test;

public class GraphTest {

    @Test
    public void testGetAllEdges() {
        System.out.println("GetAllEdges");
        Graph instance = new Graph();
        Node n1 = new Node(new Coord(1, 1));
        Node n2 = new Node(new Coord(2, 2));
        Edge e1 = new Edge(n1, n2);
        instance.addNode(n1);
        instance.addNode(n2);
        instance.addEdge(e1);
        ArrayList<Edge> result = instance.GetAllEdges();
        assertEquals(1, result.size());
        assertTrue(result.contains(e1));
    }

    @Test
    public void testGetAllNodes() {
        System.out.println("GetAllNodes");
        Graph instance = new Graph();
        Node n1 = new Node(new Coord(1, 1));
        Node n2 = new Node(new Coord(2, 2));
        instance.addNode(n1);
        instance.addNode(n2);
        ArrayList<Node> result = instance.GetAllNodes();
        assertEquals(2, result.size());
        assertTrue(result.contains(n1));
        assertTrue(result.contains(n2));
    }

    @Test
    public void testAddNode() {
        System.out.println("addNode");
        Node n = new Node(new Coord(1, 1));
        Graph instance = new Graph();
        instance.addNode(n);
        assertTrue(instance.GetAllNodes().contains(n));
    }

    @Test
    public void testAddEdge() {
        System.out.println("addEdge");
        Node n1 = new Node(new Coord(1, 1));
        Node n2 = new Node(new Coord(2, 2));
        Edge e = new Edge(n1, n2);
        Graph instance = new Graph();
        instance.addNode(n1);
        instance.addNode(n2);
        instance.addEdge(e);
        assertTrue(instance.GetAllEdges().contains(e));
    }

    @Test
    public void testAddEdge2() {
        System.out.println("addEdge");
        Node src = new Node(new Coord(1, 1));
        Node tgt = new Node(new Coord(2, 2));
        Graph instance = new Graph();
        instance.addNode(src);
        instance.addNode(tgt);
        Edge result = instance.addEdge(src, tgt);
        assertNotNull(result);
        assertTrue(instance.GetAllEdges().contains(result));
    }

    @Test
    public void testDelNode() {
        System.out.println("delNode");
        Node n = new Node(new Coord(1, 1));
        Graph instance = new Graph();
        instance.addNode(n);
        instance.delNode(n);
        assertFalse(instance.GetAllNodes().contains(n));
    }

    @Test
    public void testDelEdge() {
        System.out.println("delEdge");
        Node n1 = new Node(new Coord(1, 1));
        Node n2 = new Node(new Coord(2, 2));
        Edge e = new Edge(n1, n2);
        Graph instance = new Graph();
        instance.addNode(n1);
        instance.addNode(n2);
        instance.addEdge(e);
        instance.delEdge(e);
        assertFalse(instance.GetAllEdges().contains(e));
    }

    @Test
    public void testNumberOfNodes() {
        System.out.println("numberOfNodes");
        Graph instance = new Graph();
        Node n1 = new Node(new Coord(1, 1));
        Node n2 = new Node(new Coord(2, 2));
        instance.addNode(n1);
        instance.addNode(n2);
        int result = instance.numberOfNodes();
        assertEquals(2, result);
    }

    @Test
    public void testNumberOfEdges() {
        System.out.println("numberOfEdges");
        Graph instance = new Graph();
        Node n1 = new Node(new Coord(1, 1));
        Node n2 = new Node(new Coord(2, 2));
        Edge e1 = new Edge(n1, n2);
        instance.addNode(n1);
        instance.addNode(n2);
        instance.addEdge(e1);
        int result = instance.numberOfEdges();
        assertEquals(1, result);
    }

    @Test
    public void testGetNeighbors() {
        System.out.println("getNeighbors");
        Node n1 = new Node(new Coord(1, 1));
        Node n2 = new Node(new Coord(2, 2));
        Node n3 = new Node(new Coord(3, 3));
        Node n4 = new Node(new Coord(4, 4));
        Graph instance = new Graph();
        instance.addNode(n1);
        instance.addNode(n2);
        instance.addNode(n3);
        instance.addEdge(n1, n2);
        instance.addEdge(n1, n3);
        instance.addEdge(n1, n4);
        ArrayList<Node> result = instance.getNeighbors(n1);
        for(Node a : result){
            System.out.println(a);
        }
        assertTrue(result.contains(n2));
        assertTrue(result.contains(n3));
        assertTrue(result.contains(n4));
        assertEquals(3, result.size());
    }

    @Test
    public void testGetSuccesors() {
        System.out.println("getSuccesors");
        Node n1 = new Node(new Coord(1, 1));
        Node n2 = new Node(new Coord(2, 2));
        Graph instance = new Graph();
        instance.addNode(n1);
        instance.addNode(n2);
        instance.addEdge(n1, n2);
        ArrayList<Node> result = instance.getSuccesors(n1);
        assertTrue(result.contains(n2));
        assertEquals(1, result.size());
    }

    @Test
    public void testGetPredecessors() {
        System.out.println("getPredecessors");
        Node n1 = new Node(new Coord(1, 1));
        Node n2 = new Node(new Coord(2, 2));
        Graph instance = new Graph();
        instance.addNode(n1);
        instance.addNode(n2);
        instance.addEdge(n1, n2);
        ArrayList<Node> result = instance.getPredecessors(n2);
        assertTrue(result.contains(n1));
        assertEquals(1, result.size());
    }

    @Test
    public void testGetInOutEdges() {
        System.out.println("getInOutEdges");
        Node n1 = new Node(new Coord(1, 1));
        Node n2 = new Node(new Coord(2, 2));
        Edge e = new Edge(n1, n2);
        Graph instance = new Graph();
        instance.addNode(n1);
        instance.addNode(n2);
        instance.addEdge(e);
        ArrayList<Edge> result = instance.getInOutEdges(n1);
        assertTrue(result.contains(e));
        assertEquals(1, result.size());
    }

    @Test
    public void testGetInEdges() {
        System.out.println("getInEdges");
        Node n1 = new Node(new Coord(1, 1));
        Node n2 = new Node(new Coord(2, 2));
        Edge e = new Edge(n1, n2);
        Graph instance = new Graph();
        instance.addNode(n1);
        instance.addNode(n2);
        instance.addEdge(e);
        ArrayList<Edge> result = instance.getInEdges(n2);
        assertTrue(result.contains(e));
        assertEquals(1, result.size());
    }

    @Test
    public void testGetOutEdges() {
        System.out.println("getOutEdges");
        Node n1 = new Node(new Coord(1, 1));
        Node n2 = new Node(new Coord(2, 2));
        Edge e = new Edge(n1, n2);
        Graph instance = new Graph();
        instance.addNode(n1);
        instance.addNode(n2);
        instance.addEdge(e);
        ArrayList<Edge> result = instance.getOutEdges(n1);
        assertTrue(result.contains(e));
        assertEquals(1, result.size());
    }

    @Test
    public void testSource() {
        System.out.println("source");
        Node n1 = new Node(new Coord(1, 1));
        Node n2 = new Node(new Coord(2, 2));
        Edge e = new Edge(n1, n2);
        Graph instance = new Graph();
        Node result = instance.source(e);
        assertEquals(n1, result);
    }

    @Test
    public void testTarget() {
        System.out.println("target");
        Node n1 = new Node(new Coord(1, 1));
        Node n2 = new Node(new Coord(2, 2));
        Edge e = new Edge(n1, n2);
        Graph instance = new Graph();
        Node result = instance.target(e);
        assertEquals(n2, result);
    }

    @Test
    public void testInDegree() {
        System.out.println("inDegree");
        Node n1 = new Node(new Coord(1, 1));
        Node n2 = new Node(new Coord(2, 2));
        Graph instance = new Graph();
        instance.addNode(n1);
        instance.addNode(n2);
        instance.addEdge(n1, n2);
        int result = instance.inDegree(n2);
        assertEquals(1, result);
    }

    @Test
    public void testOutDegree() {
        System.out.println("outDegree");
        Node n1 = new Node(new Coord(1, 1));
        Node n2 = new Node(new Coord(2, 2));
        Graph instance = new Graph();
        instance.addNode(n1);
        instance.addNode(n2);
        instance.addEdge(n1, n2);
        int result = instance.outDegree(n1);
        assertEquals(1, result);
    }

    @Test
    public void testDegree() {
        System.out.println("degree");
        Node n1 = new Node(new Coord(1, 1));
        Node n2 = new Node(new Coord(2, 2));
        Graph instance = new Graph();
        instance.addNode(n1);
        instance.addNode(n2);
        instance.addEdge(n1, n2);
        int result = instance.degree(n1);
        assertEquals(1, result);
    }

    @Test
    public void testExistEdge() {
        System.out.println("existEdge");
        Node n1 = new Node(new Coord(1, 1));
        Node n2 = new Node(new Coord(2, 2));
        Graph instance = new Graph();
        instance.addNode(n1);
        instance.addNode(n2);
        instance.addEdge(n1, n2);
        boolean result = instance.existEdge(n1, n2, true);
        assertTrue(result);
    }

    @Test
    public void testGetEdge() {
        System.out.println("getEdge");
        Node n1 = new Node(new Coord(1, 1));
        Node n2 = new Node(new Coord(2, 2));
        Graph instance = new Graph();
        instance.addNode(n1);
        instance.addNode(n2);
        Edge expectedEdge = instance.addEdge(n1, n2);
        Edge result = instance.getEdge(n1, n2, true);
        assertEquals(expectedEdge, result);
    }

    @Test
    public void testGetNodePosition() {
        System.out.println("getNodePosition");
        Node n = new Node(new Coord(1, 1));
        Graph instance = new Graph();
        instance.addNode(n);
        Coord result = instance.getNodePosition(n);
        assertEquals(new Coord(1, 1), result);
    }

    @Test
    public void testSetNodePosition() {
        System.out.println("setNodePosition");
        Node n = new Node(new Coord(1, 1));
        Coord newCoord = new Coord(2, 2);
        Graph instance = new Graph();
        instance.addNode(n);
        instance.setNodePosition(n, newCoord);
        Coord result = instance.getNodePosition(n);
        assertEquals(newCoord, result);
    }

    @Test
    public void testGetBoundingBox() {
        System.out.println("getBoundingBox");
        Graph instance = new Graph();
        Node n1 = new Node(new Coord(1, 1));
        Node n2 = new Node(new Coord(2, 2));
        Node n3 = new Node(new Coord(3, 3));
        instance.addNode(n1);
        instance.addNode(n2);
        instance.addNode(n3);
        ArrayList<Coord> result = instance.getBoundingBox();
        assertEquals(2, result.size());
        assertEquals(new Coord(1, 1), result.get(0));
        assertEquals(new Coord(3, 3), result.get(1));
    }

    @Test
    public void testGetMinimumSpanningTree() {
        System.out.println("getMinimumSpanningTree");
        Graph instance = new Graph();
        // Add nodes and edges to instance
        Graph result = instance.getMinimumSpanningTree();
        assertNotNull(result);
        // Additional assertions based on the expected structure of the MST
    }
}