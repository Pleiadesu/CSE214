 

public class Expression {
    private static enum Action { Push, Pop }
    private static Action[][] act;
    
    static {
        act = new Action[128][128]; //[stack top][input token]
        act['#']['('] = act['(']['('] = act['+']['('] = act['-']['('] = act['*']['('] = act['/']['('] = Action.Push;
                        act['('][')'] = act['+'][')'] = act['-'][')'] = act['*'][')'] = act['/'][')'] = Action.Pop;
        act['#']['+'] = act['(']['+'] =                                                                 Action.Push;
                                        act['+']['+'] = act['-']['+'] = act['*']['+'] = act['/']['+'] = Action.Pop;
        act['#']['-'] = act['(']['-'] =                                                                 Action.Push;
                                        act['+']['-'] = act['-']['-'] = act['*']['-'] = act['/']['-'] = Action.Pop;
        act['#']['*'] = act['(']['*'] = act['+']['*'] = act['-']['*'] =                                 Action.Push;
                                                                        act['*']['*'] = act['/']['*'] = Action.Pop;
        act['#']['/'] = act['(']['/'] = act['+']['/'] = act['-']['*'] =                                 Action.Push;
                                                                        act['*']['/'] = act['/']['/'] = Action.Pop;
        act['#']['$'] = act['(']['$'] = act['+']['$'] = act['-']['$'] = act['*']['$'] = act['/']['$'] = Action.Pop;
    }
    
    public static String infixToPostfix(String expr) {
        //TODO: implement this method
        String postfix = "";
        StackByArray<Character> ops_stack = new StackByArray();
        ops_stack.push('#');
        String INT_LIST = "1234567890";
        for(int i = 0; i < expr.length(); i++){
            char char_at = expr.charAt(i);
            System.out.println("char_at: "+char_at);
            if(INT_LIST.contains(Character.toString(char_at))){
                postfix = postfix + Character.toString(char_at);
            }
            else{
                while(!ops_stack.isEmpty() && act[ops_stack.top()][char_at] != Action.Push){
                    char temp_top = ops_stack.pop();
                    if(temp_top != '(' && temp_top != ')' && temp_top != '#'){
                        postfix = postfix + temp_top;
                    }
                }
                ops_stack.push(char_at);
            }
        }
        return postfix;
    }
    
    public static double evalPostfixExpr(String expr) {
        //TODO: implement this method
        StackByArray<Double> res_stack = new StackByArray();
        String INT_LIST = "1234567890";
        for(int i = 0; i < expr.length(); i++){
            String char_at = Character.toString(expr.charAt(i));
            System.out.println("char_at: "+char_at);
            if(INT_LIST.contains(char_at)){
                res_stack.push(Double.parseDouble(char_at));
            }
            else{
                System.out.println("res_stack: "+res_stack);
                Double operandB = res_stack.pop();
                Double operandA = res_stack.pop();
                System.out.println("operandA: "+operandA+"\noperandB: "+operandB);
                if(char_at.equals("+")){        
                    System.out.println("result: "+(operandA + operandB));
                    res_stack.push(operandA + operandB);
                }
                else if(char_at.equals("-")){
                    System.out.println("result: "+(operandA - operandB));
                    res_stack.push(operandA - operandB);
                }
                else if(char_at.equals("*")){
                    System.out.println("result: "+(operandA * operandB));
                    res_stack.push(operandA * operandB);
                }
                else if(char_at.equals("/")){
                    System.out.println("result: "+(operandA / operandB));
                    res_stack.push(operandA / operandB);
                }
                    
            }
        }
        return res_stack.top();
    }    
}
