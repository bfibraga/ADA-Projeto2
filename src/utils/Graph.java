package utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Graph {

    private List<Integer>[] edges;

    @SuppressWarnings("unchecked")
    public Graph(int nNodes){
        this.edges = new LinkedList[nNodes];
    }

    private void initializeList(int node){
        if (this.edges[node] == null)
            this.edges[node] = new LinkedList<>();
    }

    public void addEdge(int node1, int node2){
        this.initializeList(node1);
        this.edges[node1].add(node2);
    }

    public List<Integer> findDescendants(int node){
        this.initializeList(node);
        return this.edges[node];
    }

    public List<Integer> findAncestors(int node){
        List<Integer> result = new ArrayList<>();
        for (int v = 0; v < edges.length; v++) {
            if (this.edges[v] != null){
                for (int curr_node: this.edges[v]) {
                    if (curr_node == node) result.add(v);
                }
            }
        }
        return result;
    }
}
