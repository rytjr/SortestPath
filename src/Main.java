import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.InputStreamReader;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
import java.util.PriorityQueue;

// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    static class Node implements Comparable<Node> {
        int vertex, weight;

        Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    static List<Node>[] graph;
    static final int INF = Integer.MAX_VALUE;
    static int[] value;
    static int start;
    static int N,M;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String[] Number = bf.readLine().split(" ");
        N = Integer.parseInt(Number[0]);
        M = Integer.parseInt(Number[1]);
        start = Integer.parseInt(bf.readLine());

        value = new int[N + 1];
        graph = new ArrayList[N + 1];

        Arrays.fill(value, INF);

        for(int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++) {
            String[] str = bf.readLine().split(" ");
            int a = Integer.parseInt(str[0]);
            int b = Integer.parseInt(str[1]);
            int c = Integer.parseInt(str[2]);
            graph[a].add(new Node(b,c));
        }

        dijkstra(start);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (value[i] == INF) {
                sb.append("INF\n");
            } else {
                sb.append(value[i]).append("\n");
            }
        }
        System.out.print(sb);
    }

    public static void dijkstra(int start) {
        PriorityQueue<Node> pri = new PriorityQueue<>();
        pri.offer(new Node(start,0));
        value[start] = 0;

        while(!pri.isEmpty()) {
            Node no = pri.poll();
            int x = no.vertex;
            int y = no.weight;

            if(y > value[x]) {
                continue;
            }

            for(Node i : graph[x]) {
                int xx = i.vertex;
                int yy = i.weight + y;

                if(yy < value[xx]) {
                    value[xx] = yy;
                    pri.offer(new Node(xx, yy));
                }

            }
        }
    }

}