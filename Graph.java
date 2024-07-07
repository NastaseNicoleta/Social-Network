package graph;

import java.util.*;

public class Graph {
    private int nr_vf; // No. of varfuri in graph

    private LinkedList<Integer>[] adj; // Adjacency List
    ArrayList<ArrayList<Integer> > components = new ArrayList<>();

   public Graph(int nr_vf)
    {
        this.nr_vf = nr_vf;
        adj = new LinkedList[nr_vf];

        for (int i = 0; i < nr_vf; i++)
            adj[i] = new LinkedList();
    }

    public void addEdge(int u, int w)
    {
        adj[u].add(w);
    }

    public void DFSUtil(int v, boolean[] visited, ArrayList<Integer> al)
    {
        visited[v] = true;
        al.add(v);
        //System.out.print(v + " ");
        Iterator<Integer> it = adj[v].iterator();

        while (it.hasNext()) {
            int n = it.next();
            if (!visited[n])
                DFSUtil(n, visited, al);
        }
    }

    public void DFS()
    {
        boolean[] visited = new boolean[nr_vf];

        for (int i = 0; i < nr_vf; i++) {
            ArrayList<Integer> al = new ArrayList<>();
            if (!visited[i]) {
                DFSUtil(i, visited, al);
                components.add(al);
            }
        }
    }

    public int ConnecetedComponents() { return components.size(); }
}
