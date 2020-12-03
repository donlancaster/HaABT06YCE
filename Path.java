
public class Path {
    public String path;
    public int length;
    Path(String path, int length){
        this.path = path;
        this.length = length;
    }
    void add(char c, int weight) {
        path += c + "->";
        length += weight;
    }
    void addLast(char c, int weight){
        path += c;
        length += weight;
    }
}
