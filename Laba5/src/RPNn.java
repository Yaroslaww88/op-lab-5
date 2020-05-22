import java.util.ArrayList;
import java.util.Stack;

public class RPNn {
    private static Boolean mayUnary = true;
    public static ArrayList<String> getRPN(String tokens) throws Exception {
      ArrayList<String> ans = new ArrayList<>();
      String[] input = tokens.split(" ");
      Stack<String> stack = new Stack<>();
      for(int i=0;i<input.length;i++){
          System.out.println(stack);
          System.out.println(input[i]);
          if(getToken(input[i])>0) {
              if (ans.size() != 0 && !stack.isEmpty() && getToken(stack.peek()) >= getToken(input[i]) && !input[i].equals("(")){
                  ans.add(stack.pop());
              stack.add(input[i]);
          }
              else
                  stack.add(input[i]);
          }
          if(getToken(input[i])==0){
            ans.add(input[i]);
          }
          if(getToken(input[i])==-1){
            do{
                ans.add(stack.pop());
            }while(!stack.peek().equals("("));
            stack.pop();
          }
      }
      do{
          ans.add(stack.pop());
      }while (!stack.isEmpty());
        System.out.println("1:"+ans);

        return ans;
    }


    private static int getToken(String next) {
        if (next.equals("*") || next.equals("/"))
            return 3;
        else if (next.equals("+") || next.equals("-"))
            return 2;
       else if (next.equals("("))
            return 1;
        else if (next.equals(")"))
            return -1;
        else if (next.equals("^"))
            return 3;
        else return 0;
    }

}
