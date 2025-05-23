package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Graph {

    private int nNodes;
    private List<Pair<Integer>>[] edges;

    @SuppressWarnings("unchecked")
    public Graph(int nNodes){
        this.edges = new LinkedList[nNodes];
        this.nNodes = nNodes;
    }

    private void initializeList(int node){
        if (this.edges[node] == null)
            this.edges[node] = new LinkedList<>();
    }

    public int nNodes(){
        return this.nNodes;
    }

    public List<Pair<Integer>>[] edges(){
        return this.edges;
    }

    public void addEdge(int node1, int node2, int value){
        this.initializeList(node1);
        Pair<Integer> new_pair = new Pair<>(node2, value);
        this.edges[node1].add(new_pair);
    }

    public List<Pair<Integer>> findSuccessors(int node){
        this.initializeList(node);
        return this.edges[node];
    }

}
