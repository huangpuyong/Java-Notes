package code_13_graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 图的广度优先遍历
 */
public class BreadthFirstPaths {
    //标记顶点是否被访问过
    private boolean[] visited;
    //记录路径
    private int[] edgeTo;
    //起点
    private int s;

    public BreadthFirstPaths(Graph graph, int s){
        visited=new boolean[graph.V()];
        edgeTo=new int[graph.V()];
        for(int i=0;i<graph.V();i++){
            edgeTo[i]=-1;
        }
        this.s=s;
        bfs(graph,this.s);
    }

    //深度优先搜索
    private void bfs(Graph graph,int s){
        visited[s]=true;
        Queue<Integer> queue=new LinkedList<>();
        queue.add(s);
        while (!queue.isEmpty()){
            int v=queue.poll();
            for(int w:graph.adj(v)){
                if(!visited[w]){
                    visited[w]=true;
                    edgeTo[w]=v;
                    queue.add(w);
                }
            }
        }
    }

    //是否有到顶点v的路径
    private boolean hasPathTo(int v){
        return visited[v];
    }

    //获取从 s->v的路径
    public Iterable<Integer> pathTo(int v){
        if(!hasPathTo(v)){
            return null;
        }
        Stack<Integer> path=new Stack<>();
        for(int i=v;i!=s;i=edgeTo[i]){
            path.push(i);
        }
        path.push(s);
        return path;
    }

    public void showPath(int v){
        Stack<Integer> path= (Stack<Integer>) pathTo(v);

        StringBuffer sb=new StringBuffer();
        sb.append("[");
        while(!path.isEmpty()){
            int w=path.peek();
            if(path.size()==1){
                sb.append(w);
                path.pop();
            }else{
                sb.append(w+"-->");
                path.pop();
            }
        }
        sb.append("]");
        System.out.println(sb.toString());
    }
}