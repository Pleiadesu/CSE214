 

public class Parser {
    private static class Node {
        public Integer num;
        public Character opr;
        public Node(Integer num) { this.num = num; }
        public Node(Character opr) { this.opr = opr; }
        public String toString() {
            return num != null ? num.toString()
                :  opr != null ? opr.toString()
                :  ""
                ;
        }
    }
    
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
        act['#']['/'] = act['(']['/'] = act['+']['/'] = act['-']['/'] =                                 Action.Push;
                                                                        act['*']['/'] = act['/']['/'] = Action.Pop;
        act['#']['$'] = act['(']['$'] = act['+']['$'] = act['-']['$'] = act['*']['$'] = act['/']['$'] = Action.Pop;
    }
    
    public static BinaryTree<Node> parseExpr(String expr) {
        Scanner scan = new Scanner(expr);
        Stack<LinkedBinaryTree<Node>> tree = new StackByArray<LinkedBinaryTree<Node>>();
        Stack<Character> opr = new StackByArray<Character>();
        String postfix = "";
        opr.push('#');
        for(String tmp: scan){
            char char_at = tmp.charAt(0);
            if(Scanner.isDigit(char_at)){
                postfix += tmp + " ";
            }
            else{
                while(!opr.isEmpty() && act[opr.top()][char_at] != Action.Push){
                    char temp_top = opr.pop();
                    if(temp_top != '(' && temp_top != ')' && temp_top != '#'){
                        postfix += temp_top + " ";
                    }
                }
                opr.push(char_at);
            }
        }
        Scanner scan_post = new Scanner(postfix);
        for(String s: scan_post){
            char char_at = s.charAt(0);
            LinkedBinaryTree temp = new LinkedBinaryTree();
            if(Scanner.isDigit(char_at)){
                temp.addRoot(s);
                tree.push(temp);
            }
            else if(char_at != '$'){
                temp.addRoot(char_at);
                LinkedBinaryTree right_child = tree.pop();
                LinkedBinaryTree left_child = tree.pop();
                temp.attach(temp.root(), left_child, right_child);
                tree.push(temp);
            }
        }
        return tree.top();
        //TODO: - parseExpr will be similar to evalExpr function that evaluates
        //        infix expressions.
        //      - Here, instead of using the operand stack, we push/pop subtrees of
        //        the parse tree to/from the tree stack.  
        //      - When popping an operator, pop two parse-trees from the tree stack; 
        //        build a parse-tree rooted at the operator; and push the resulting tree
        //        onto the tree stack
    }
    public static double evalExpr(BinaryTree<Node> tree) {
        LinkedBinaryTree<Node> parseTree = (LinkedBinaryTree<Node>) tree;
        Stack<Double> num = new StackByArray<Double>();
        for(Object o: parseTree.postorder()){
            Position p = (Position)o;
            Character char_at = ("" + p.getElement()).charAt(0);
            if(Scanner.isDigit(char_at)){
                num.push(Double.parseDouble((String)p.getElement()));
            }
            else if(!char_at.equals('$') && !char_at.equals('#')){
                Double operandB = num.pop();
                Double operandA = num.pop();
                if(char_at.equals('+')){        
                    num.push(operandA + operandB);
                }
                else if(char_at.equals('-')){
                    num.push(operandA - operandB);
                }
                else if(char_at.equals('*')){
                    num.push(operandA * operandB);
                }
                else if(char_at.equals('/')){
                    num.push(operandA / operandB);
                }                  
            }
        }
        return num.top();
        //TODO: - evalExpr will be similar to evalPostfixExpr function that evaluates
        //        postfix expressions.
        //      - While traversing the nodes of the parseTree in the post-order,
        //        evaluate the expression by pushing/popping operands to/from the stack num 
    }
    
    public static String infixToPrefix(String expr) {
        String strExp = "";
        LinkedBinaryTree<Node> parseTree = (LinkedBinaryTree<Node>) parseExpr(expr);
        for(Position<Node> p: parseTree.preorder())
            strExp += p.getElement() + " ";
        return strExp;
    }

    public static String infixToPostfix(String expr) {
        String strExp = "";
        LinkedBinaryTree<Node> parseTree = (LinkedBinaryTree<Node>) parseExpr(expr);
        for(Position<Node> p: parseTree.postorder())
            strExp += p.getElement() + " ";
        return strExp;
    }
    
    public static String infixToInfix(String expr) {
        String strExp = "";
        LinkedBinaryTree<Node> parseTree = (LinkedBinaryTree<Node>) parseExpr(expr);
        for(Position<Node> p: parseTree.inorder())
            strExp += p.getElement() + " ";
        return strExp;
    }
}
