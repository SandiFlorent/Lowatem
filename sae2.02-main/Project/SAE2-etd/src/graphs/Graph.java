/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphs;

import algorithms.BFS;
import algorithms.Prim;
import java.util.ArrayList;

/**
 *
 * @author ludevaux
 */
public class Graph implements IGraph {

    /**
     * This ArrayList contains all the Edges of the graph
     */
    private ArrayList<Edge> allEdges = new ArrayList<>();

    /**
     * This ArrayList contains all the Nodes of the graph
     */
    private ArrayList<Node> allNodes = new ArrayList<>();

    /**
     * This function give access to all the edges of the graph
     *
     * @return allEdges, an ArrayList containing all the edges of the graph
     */
    public ArrayList<Edge> GetAllEdges() {
        return allEdges;
    }

    /**
     * This function give access to all the Nodes of the graph
     *
     * @return allNodes, an ArrayList containing all the nodes of the graph
     */
    public ArrayList<Node> GetAllNodes() {
        return allNodes;
    }

    @Override
    public Node addNode() {
        Node aNode = new Node();
        allNodes.add(aNode);
        return aNode;
    }

    @Override
    public Node addNode(Node n) {
        if (!allNodes.contains(n)) {
            allNodes.add(n);
        }
        return n;
    }

    @Override
    public Edge addEdge(Edge e) {
        if (!allEdges.contains(e)) {
            allEdges.add(e);
        }
        //When we add an edge, its nodes are naturally neighbours
        e.getSource().addNeighBours(e.getTarget());
        e.getTarget().addNeighBours(e.getSource());

        return e;
    }

    @Override
    public Edge addEdge(Node src, Node tgt) {
        Edge tempEdge = new Edge(src, tgt);
        allEdges.add(tempEdge);

        return tempEdge;
    }

    @Override
    public void delNode(Node n) {
        allNodes.remove(n);
        //When we remove a node, its neighbours are no longer neighbours to it.
        for (Node node : n.getNeighbours()) {
            node.removeNeighbours(n);
        }
    }

    @Override
    public void delEdge(Edge e) {
        allEdges.remove(e);
        //When we delete an edge, its nodes are no longer neighbours
        //Obviously, we consider there's no duplicates edges
        e.getSource().removeNeighbours(e.getTarget());
        e.getTarget().removeNeighbours(e.getSource());
    }

    @Override
    public int numberOfNodes() {
        return allNodes.size();
    }

    @Override
    public int numberOfEdges() {
        return allEdges.size();
    }

    @Override
    public ArrayList<Node> getNeighbors(Node n) {
        return n.getNeighbours();
    }

    @Override
    public ArrayList<Node> getSuccesors(Node n) {
        ArrayList<Node> allNeighbors = new ArrayList<>();
        for (Edge edge : allEdges) {
            if (edge.getSource().equals(n)) {
                allNeighbors.add(edge.getTarget());
            }
        }
        return allNeighbors;
    }

    @Override
    public ArrayList<Node> getPredecessors(Node n) {
        ArrayList<Node> allNeighbors = new ArrayList<>();
        for (Edge edge : allEdges) {
            if (edge.getTarget().equals(n)) {
                allNeighbors.add(edge.getSource());
            }
        }
        return allNeighbors;
    }

    @Override
    public ArrayList<Edge> getInOutEdges(Node n) {
        ArrayList<Edge> inOutEdges = new ArrayList<>();
        inOutEdges.addAll(getInEdges(n));
        inOutEdges.addAll(getOutEdges(n));
        return inOutEdges;
    }

    @Override
    public ArrayList<Edge> getInEdges(Node n) {
        ArrayList<Edge> inEdges = new ArrayList<>();
        for (Edge edge : allEdges) {
            if (edge.getTarget() == n) {
                inEdges.add(edge);
            }
        }
        return inEdges;
    }

    @Override
    public ArrayList<Edge> getOutEdges(Node n) {
        ArrayList<Edge> outEdges = new ArrayList<>();
        for (Edge edge : allEdges) {
            if (edge.getSource() == n) {
                outEdges.add(edge);
            }
        }
        return outEdges;
    }

    @Override
    public ArrayList<Node> getNodes() {
        return allNodes;
    }

    @Override
    public ArrayList<Edge> getEdges() {
        return allEdges;
    }

    @Override
    public Node source(Edge e) {
        return e.getSource();
    }

    @Override
    public Node target(Edge e) {
        return e.getTarget();
    }

    @Override
    public int inDegree(Node n) {
        return getInEdges(n).size();
    }

    @Override
    public int outDegree(Node n) {
        return getOutEdges(n).size();
    }

    @Override
    public int degree(Node n) {
        return getInEdges(n).size() + getOutEdges(n).size();
    }

    @Override
    public boolean existEdge(Node src, Node tgt, boolean oriented) {
        for (Edge edge : this.allEdges) {
            if (oriented) {
                // For the directed graph, we have to check if it has the same source and target
                if (edge.getSource().equals(src) && edge.getTarget().equals(tgt)) {
                    return true;
                }
            } else {
                // For the undirected graph, as long as it has both nodes it's the same edge
                if ((edge.getSource().equals(src) && edge.getTarget().equals(tgt))
                        || (edge.getSource().equals(tgt) && edge.getTarget().equals(src))) {
                    return true;
                }
            }
        }
        return false; // If it hasn't been found, then we return false
    }

    @Override
    public Edge getEdge(Node src, Node tgt, boolean oriented) {
        if (existEdge(src, tgt, oriented)) {
            System.out.println("oui");
            System.out.println(allEdges.size());
            for (Edge edge : allEdges) {
                System.out.println(edge);
                if (edge.equals(new Edge(src, tgt))) {
                    return edge;
                }
            }
        }
        return null;
    }

    @Override
    public Coord getNodePosition(Node n) {
        return n.coordinates;
    }

    @Override
    public ArrayList<Coord> getEdgePosition(Edge e) {
        return e.getEdgePositions();
    }

    @Override
    public void setNodePosition(Node n, Coord c) {
        n.coordinates = c;
    }

    @Override
    public void setEdgePosition(Edge e, ArrayList<Coord> bends) {
        e.setEdgePositions(bends);
    }

    @Override
    public void setAllNodesPositions(Coord c) {
        for (Node node : allNodes) {
            setNodePosition(node, c);
        }
    }

    @Override
    public void setAllEdgesPositions(ArrayList<Coord> bends) {
        for (Edge edge : allEdges) {
            setEdgePosition(edge, bends);
        }
    }

    @Override
    public ArrayList<Coord> getBoundingBox() {
        double miny = allNodes.get(0).coordinates.getY();
        double maxy = allNodes.get(0).coordinates.getY();
        double maxx = allNodes.get(0).coordinates.getX();
        double minx = allNodes.get(0).coordinates.getX();
        ArrayList<Coord> BoxCoord = new ArrayList<>();
        for (Node a : allNodes) {
            if (minx > a.coordinates.getX()) {
                minx = a.coordinates.getX();
            }
            if (maxx < a.coordinates.getX()) {
                maxx = a.coordinates.getX();
            }
            if (miny > a.coordinates.getY()) {
                miny = a.coordinates.getY();
            }
            if (maxy < a.coordinates.getY()) {
                maxy = a.coordinates.getY();
            }

        }
        Coord pointA = new Coord(minx, miny);
        Coord pointB = new Coord(maxx, maxy);
        BoxCoord.add(pointA);
        BoxCoord.add(pointB);
        return BoxCoord;
    }

    @Override
    public Graph getMinimumSpanningTree() {
        Prim prim = new Prim();
        return prim.prim(this);
    }

    @Override
    public void bundle() {
        //We instantiate prim and BFS algorithms
        Prim prim = new Prim();
        BFS bfs = new BFS();
        //We make a copy of the graph to avoid any issues
        Graph copy = this;
        //We compute the MST
        Graph mst = prim.prim(this);
        //We remove all the MST's edges from the copy in order to only have 
        //the longest edges that needs the shortest path to be added as its bends
        copy.allEdges.removeAll(mst.allEdges);
        ArrayList<Coord> bends;
        //Then for each remaining edge in the copy, we'll define the bends thanks to the BFS 
        //Taking the MST as a guide
        for (Edge edgeA : copy.allEdges) {
            edgeA.getSource().removeNeighbours(edgeA.getTarget());
            edgeA.getTarget().removeNeighbours(edgeA.getSource());
        }
        for (Edge edgeA : copy.allEdges) {
            bends = bfs.bfsShortestPath(mst, edgeA.getSource(), edgeA.getTarget());
            this.setEdgePosition(edgeA, bends);
        }
        //Finally we add the MST's edges to make the graph whole again
        this.allEdges.addAll(mst.allEdges);
    }
}
