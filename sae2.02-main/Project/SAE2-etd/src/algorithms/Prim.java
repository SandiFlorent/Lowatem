/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algorithms;

import graphs.Edge;
import graphs.Node;
import graphs.Graph;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Random;

/**
 *
 * @author mflorent
 */
public class Prim {

    Graph tree = new Graph();

    public Graph prim(Graph g) {
        int numberOfNodes = g.getNodes().size();

        // If the graph has no nodes, then there's no tree
        if (numberOfNodes == 0) {
            return tree;
        }

        //We first choose a random node
        Node InitialNode = g.getNodes().get(new Random().nextInt(numberOfNodes));

        HashSet<Node> knownNodes = new HashSet<>();
        HashSet<Node> unknownNodes = new HashSet<>();

        //It will be our first known node
        knownNodes.add(InitialNode);
        unknownNodes.addAll(g.GetAllNodes());
        unknownNodes.removeAll(knownNodes);

        //The priority queue will compare every Edges' Weight in order to find the "cheaper" one
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(Comparator.comparingDouble(Edge::getWeight));
        priorityQueue.addAll(g.getInOutEdges(InitialNode));

        //We'll continue as long as we didn't compared all the Edges to find cheaper ones
        // and while we still didn't reached all nodes
        while (knownNodes.size() < numberOfNodes && !priorityQueue.isEmpty()) {
            //We first take the cheapest Edge
            Edge minEdge = priorityQueue.poll();

            //Then we check if we can add this cheapest Edge
            Node newNode = chooseNode(knownNodes, minEdge);

            //If we can, we'll add the unknown Node of the Edge to the tree and the Edge itself
            //We'll also add all of the node's incident Edge to the priorityQueue in case
            //It contains the next minEdge
            if (newNode != null) {
                knownNodes.add(newNode);
                tree.addNode(minEdge.getSource());
                tree.addNode(minEdge.getTarget());
                tree.addEdge(minEdge);
                unknownNodes.remove(newNode);
                priorityQueue.addAll(g.getInOutEdges(newNode));

            } //If we can't, then we first check if it's just because the edge wasn't 
            // in agreement with our conditions or if it's because it's not a connected graph
            else if (priorityQueue.size() == 0 && unknownNodes.size() > 0) {
                // If it's not a connected graph, we'll add random unknown node to the 
                //knownNodes Set, clear and refill the priorityQueue with this node and
                //continue with the algorithm.
                Node randomNodeFromAnotherPieceOfTheGraph = unknownNodes.iterator().next();
                numberOfNodes = unknownNodes.size();
                unknownNodes.remove(randomNodeFromAnotherPieceOfTheGraph);
                knownNodes.clear();
                knownNodes.add(randomNodeFromAnotherPieceOfTheGraph);
                priorityQueue.clear();
                priorityQueue.addAll(g.getInOutEdges(randomNodeFromAnotherPieceOfTheGraph));
            }
        }
        
        return tree;
    }

    /**
     * This function determines if the proposed cheapest Edge is in agreement
     * with the Prim's algorithm's conditions. That is to say that it determines
     * if only one of the two Edge's nodes is known.
     *
     * @param knownNodes the Set of knownNodes
     * @param minEdge the proposed cheapest Edge by the algorithm
     * @return the unknown node of the minEde, null if it's known
     */
    private Node chooseNode(HashSet<Node> knownNodes, Edge minEdge) {
        if (knownNodes.contains(minEdge.getSource()) && !knownNodes.contains(minEdge.getTarget())) {
            return minEdge.getTarget();
        } else if (knownNodes.contains(minEdge.getTarget()) && !knownNodes.contains(minEdge.getSource())) {
            return minEdge.getSource();
        }
        return null;
    }
}
