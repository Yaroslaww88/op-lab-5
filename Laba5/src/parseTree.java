public class parseTree {
    private static Node first;
    private static Node current;

    private static class Node {
        Node right;
        Node left;
        String value;

        public Node(Node left, Node right, String value) {
            this.left = left;
            this.right = right;
            this.value = value;
        }
    }

    public void addLeft(String value) {

    }

    public static void addRight(String value) {

    }

    public static void setFirst(String value) {
        Node first = new Node(null, null, value);
        current = first;
    }

    public static void makeTree(String input) {
        String[] in = input.split(" ");
        setFirst(in[0]);

        for (int i = 1; i < in.length-1; i++) {
            System.out.println(in[i]);
            if (current.left == null && !in[i].matches("[-+]?\\d+"))
                current.left = new Node(null, null, in[i]);
            if (current.left != null && !in[i].matches("[-+]?\\d+")) {
                do {
                    current = current.left;
                } while (current.left != null);
                current.left = new Node(null, null, in[i]);
            }

            if(current.left == null && in[i].matches("[-+]?\\d+") && !in[i+1].matches("[-+]?\\d+")){
              current = first;
              if(current.left.right!=null) {
                  do {
                      current = current.left;
                  } while (current.left.right != null);
              }
              current.right = new Node(null, null, in[i]);
            }
            if(current.left != null && current.left != null&&in[i].matches("[-+]?\\d+")&& in[i+1].matches("[-+]?\\d+"))
                current.right = new Node(null, null, in[i]);
                current.left = new Node(null, null, in[i+1]);
        }
    }
}
