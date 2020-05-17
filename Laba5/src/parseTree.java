public class parseTree {
    private static Node first;
    private static Node current;

    private static class Node {
        Node right;
        Node left;
        String value;
        Node parent;

        public Node(Node left, Node right, String value,Node parent) {
            this.left = left;
            this.right = right;
            this.value = value;
            this.parent = parent;
        }
    }

    public void addLeft(String value) {

    }

    public static void addRight(String value) {

    }

    public static void setFirst(String value) {
        Node first = new Node(null, null, value,null);
        current = first;
    }

    public static void makeTree(String input) {
        String[] in = input.split(" ");
        setFirst(in[0]);

        for (int i = 1; i < in.length-1; i++) {
            System.out.println(in[i]);
            if (current.left == null && !in[i].matches("[-+]?\\d+"))
                current.left = new Node(null, null, in[i],current);


            if (current.left != null && !in[i].matches("[-+]?\\d+")) {
                do {
                    current = current.left;
                } while (current.left != null);
                current.left = new Node(null, null, in[i],current);
            }


            if( current.left.value.matches("[-+]?\\d+") && current.right.value.matches("[-+]?\\d+")){
                do{
                    current=current.parent;
                }while (current.right==null);
                current.right=new Node(null, null, in[i],current);
            }


            if(current.value.matches("[-+]?\\d+")&&current.parent.left==null&&in[i].matches("[-+]?\\d+")){
                current.parent.left = new Node(null, null, in[i],current);
            }



        }
    }
}
