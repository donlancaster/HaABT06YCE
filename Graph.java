

public class Graph {
    private final int MAX_VERTS = 20;
    private Vertex[] vertexList; // Список вершин
    private int[][] adjacencyMatrix; // Матрица смежности
    public int nVerts; // Текущее количество вершин

    public int size() {
        return nVerts;
    }

    public Graph() {
        vertexList = new Vertex[MAX_VERTS];
        adjacencyMatrix = new int[MAX_VERTS][MAX_VERTS];
        nVerts = 0;
        for (int j = 0; j < MAX_VERTS; j++) {
            for (int k = 0; k < MAX_VERTS; k++) {
                adjacencyMatrix[j][k] = 100000;
            }
        }
    }


    public void addVertex(char ch) {
        vertexList[nVerts++] = new Vertex(ch);
    }

    private int getVertexIndex(char ch) {
        int res = -1;
        for (int i = 0; i < nVerts; i++) {
            if (vertexList[i].label == ch) {
                res = i;
                break;
            }
        }
        return res;
    }

    public void addEdge(char start, char end, int weight) {
        adjacencyMatrix[getVertexIndex(start)][getVertexIndex(end)] = weight;
    }

    List path(char start, char end) {
        List paths = new List();
        path(getVertexIndex(start), getVertexIndex(end), paths, Character.toString(start), 0);
        return paths;
    }


    private void path(int current, int end, List paths, String path, int currentLength) {
        vertexList[current].checked = true;
        for (int i = 0; i < nVerts; i++) {
            if (!vertexList[i].checked && adjacencyMatrix[current][i] != 100000) {
                currentLength += adjacencyMatrix[current][i];
                if (i == end) {
                    paths.insert(new Path(path + "->" + (char) (i + 65), currentLength));
                } else {
                    path(i, end, paths, path + "->" + (char) (i + 65), currentLength);
                }
                currentLength -= adjacencyMatrix[current][i];
            }
        }
        vertexList[current].checked = false;
    }
    char center(){
        int [][]arr = new int[nVerts][nVerts];
        for (int i = 0; i < nVerts; i++){
            for (int j = 0; j < nVerts; j++) {

                arr[i][j] = adjacencyMatrix[i][j];
            }
        }
        for (int k = 0; k < nVerts; k++){
            for (int i = 0; i < nVerts; i++){
                for (int j = 0; j < nVerts; j++){
                    if (arr[i][k] + arr[k][j] < arr[i][j]) {
                        arr[i][j] = arr[i][k] + arr[k][j];
                    }
                }
            }
        }
        for (int i = 0; i < nVerts; i++) arr[i][i] = 0;
        System.out.print(" ");
        for (int i = 0; i < nVerts; i++) {
            System.out.printf("%7c", vertexList[i].label);
        }
        System.out.print("\n");
        for (int i = 0; i < nVerts; i++){
            System.out.print((char) vertexList[i].label);
            for (int j = 0; j < nVerts; j++){
                System.out.printf("%7d",arr[i][j]);
            }
            System.out.println();
        }
        int center = 0;
        int min = 100000;
        int max = 0;
        for (int j = 0; j < nVerts; j++){
            for (int i = 0; i < nVerts; i++){
                if (arr[i][j] > max) max = arr[i][j];
            }
            if (min > max) {
                min = max;
                center = j;
            }
            max = 0;
        }
        return vertexList[center].label;
    }
//B A
    public void showAdjMatrix() {
        System.out.print(" ");
        for (int i = 0; i < nVerts; i++) {
            System.out.printf("%4c", vertexList[i].label);
        }
        System.out.println();
        for (int i = 0; i < nVerts; i++) {
            System.out.print((char) vertexList[i].label);
            for (int j = 0; j < nVerts; j++) {
                if (adjacencyMatrix[i][j] == 100000) {
                    System.out.printf("%4s", ".");
                } else
                    System.out.printf("%4d", adjacencyMatrix[i][j]);
            }
            System.out.println();
        }
    }


    public void removeVertex(char ch) {
        Vertex[] newVertexList = new Vertex[MAX_VERTS];
        int[][] newAdjacencyMatrix = new int[MAX_VERTS][MAX_VERTS];
        int ignore = getVertexIndex(ch);
        for (int i = 0; i < nVerts; i++) {
            if (i == ignore) {
                newVertexList[i] = vertexList[i + 1];
                continue;
            }
            newVertexList[i] = vertexList[i];
        }
        vertexList = newVertexList;
        for (int j = 0; j < MAX_VERTS; j++) {
            for (int k = 0; k < MAX_VERTS; k++) {
                if (j == ignore && k == ignore) {
                    newAdjacencyMatrix[j][k] = adjacencyMatrix[j + 1][k + 1];
                    continue;
                }
                if (j == ignore) {
                    newAdjacencyMatrix[j][k] = adjacencyMatrix[j + 1][k];
                    continue;
                }
                if (k == ignore) {
                    newAdjacencyMatrix[j][k] = adjacencyMatrix[j][k + 1];
                    continue;
                }
                newAdjacencyMatrix[j][k] = adjacencyMatrix[j][k];
            }
        }
        adjacencyMatrix = newAdjacencyMatrix;
        nVerts--;
    }
}
