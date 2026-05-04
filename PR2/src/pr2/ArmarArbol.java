package pr2;

public class ArmarArbol {
    public static TreeNode armarArbolDesdeString(String s, TreeNode nodo) { // (10,(11,2))  (((22,21),20),19)
        TreeNode node = new TreeNode();
        if(s == null || s.isEmpty() || s.startsWith("X")) {
            return null;
        }
        char[] chars = s.toCharArray();
        
        int i=0;
        while (i<chars.length && s.startsWith("X") == false) {
            if(chars[i]!=')'){
                i++;
            }
            else{
                int j=i;
                while(chars[j]!='('){
                    j--;
                }
                String subArbol = s.substring(j,i+1);
                if(subArbol.contains("X")){
                    if(subArbol.charAt(1)!='X'){
                        String numbers = subArbol.replace("(", "").replace(")", "");
                        String[] arr = numbers.split(",");
                        int[] numArr = new int[arr.length];
                        numArr[0] = Integer.parseInt(arr[0]);
                        node.left = nodo;
                        node.right = new TreeNode(numArr[0]);
                        s=s.substring(0,j)+"X"+s.substring(i+1);
                        armarArbolDesdeString(s, node);
                    }
                    else if(subArbol.charAt(subArbol.length()-1)!='X'){
                        String numStr = subArbol.replace("(", "").replace(")", "");
                        String[] arr = numStr.split(",");
                        int[] numArr = new int[arr.length];
                        numArr[1] = Integer.parseInt(arr[1]);
                        node.left = new TreeNode(numArr[1]);
                        node.right = nodo;
                        s=s.substring(0,j)+"X"+s.substring(i+1);
                        armarArbolDesdeString(s, node);
                    }
                }
                else{
                    String numbers = subArbol.replace("(", "").replace(")", "");
                    String[] arr = numbers.split(",");
                    int[] numArr = new int[arr.length];
                    for(int k = 0; k < numArr.length; k++){
                        numArr[k] = Integer.parseInt(arr[k]);
                        System.out.println(numArr[k]);
                    }
                    node.left = new TreeNode(numArr[0]);
                    node.right = new TreeNode(numArr[1]);  
                    s=s.substring(0,j)+"X"+s.substring(i+1);
                    chars = s.toCharArray();
                    System.out.println(node.left.peso);
                    System.out.println(node.right.peso);
                    armarArbolDesdeString(s, node);
                }
                
            }
            
        }
        i++;
        
        return node;
    }
}