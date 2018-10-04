 

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
        Scanner scan = new Scanner(expr);
        String postfix = "";
        StackByArray<Character> ops_stack = new StackByArray();
        ops_stack.push('#');
        for(String tmp: scan){
            char char_at = tmp.charAt(0);
            if(Scanner.isDigit(char_at)){
                postfix += tmp + " ";
            }
            else{
                while(!ops_stack.isEmpty() && act[ops_stack.top()][char_at] != Action.Push){
                    char temp_top = ops_stack.pop();
                    if(temp_top != '(' && temp_top != ')' && temp_top != '#'){
                        postfix += temp_top + " ";
                    }
                }
                ops_stack.push(char_at);
            }
        }
        return postfix;
    }
    
    public static double evalPostfixExpr(String expr) {
        Scanner scan = new Scanner(expr);
        StackByArray<Double> res_stack = new StackByArray();
        for(String tmp: scan){
            Character char_at = tmp.charAt(0);
            if(Scanner.isDigit(char_at)){
                res_stack.push(Double.parseDouble(tmp));
            }
            else if(!char_at.equals('$')){
                Double operandB = res_stack.pop();
                Double operandA = res_stack.pop();
                if(char_at.equals('+')){        
                    res_stack.push(operandA + operandB);
                }
                else if(char_at.equals('-')){
                    res_stack.push(operandA - operandB);
                }
                else if(char_at.equals('*')){
                    res_stack.push(operandA * operandB);
                }
                else if(char_at.equals('/')){
                    res_stack.push(operandA / operandB);
                }                  
            }
        }
        return res_stack.top();
    }    
}
