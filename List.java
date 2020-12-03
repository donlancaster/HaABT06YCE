
public class List {
    private int size;
    private Link first;

    public List() {
        first = null;
        size = 0;
    }

    void clear(){
        first = null;
        size =0;
    }

    boolean isEmpty() {
        return first == null;
    }

    public int length() {
        return size;
    }

    void insert(Path path) {
        Link newLink = new Link(path);
        if (isEmpty()) {
            first = newLink;
            size++;
        } else {
            Link current = first;
            Link previous = first;
            while (current != null) {
                if (current.next == null) {
                    current.next = newLink;
                    size++;
                    break;
                }
                if (current.path.length > newLink.path.length) {
                    newLink.next = current;
                    if (current == first) first = newLink;
                    else previous.next = newLink;
                    size++;
                    break;
                }
                current = current.next;
                previous = current;
            }
        }
    }

    Path getFirst() {
        return first.path;
    }

    Path getLast() {
        Link current = first;
        while (current.next != null) {
            current = current.next;
        }
        return current.path;
    }

    void displayList() {
        Link current = first;
        for (int i = 0; i < length(); i++) {
            System.out.printf("%d. %s, %d\n", i + 1, current.path.path, current.path.length);
            current = current.next;
        }
    }


}
