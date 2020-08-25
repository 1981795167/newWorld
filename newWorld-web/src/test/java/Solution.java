import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Xavier.liu
 * @date 2020/6/16 1:13
 */

    class Solution {
        public int romanToInt(String s) {
            int sum = 0;
            char b ;
            for(int i = 0; i< s.length() ;i++){
                char a = s.charAt(i);
                if(i != s.length() - 1)
                    b = s.charAt(i+1);
                else
                    b = 'A';

                if ((a == 'I' && (b == 'V' || b == 'X')) || (a == 'X' && (b == 'L' || b == 'C'))
                        || (a == 'C' && (b == 'D' || b == 'M'))){
                    sum -=  caseToInt(a);
                }else {
                    sum +=  caseToInt(a);
                }

            }
            return sum;
        }

        public int caseToInt(char s){

            switch (s){
                case 'I': return 1;
                case 'V': return 5;
                case 'X': return 10;
                case 'L': return 50;
                case 'C': return 100;
                case 'D': return 500;
                case 'M': return 1000;
                default: return 0;

            }

        }
    }
    class MainClass {
        public static String stringToString(String input) {
            if (input == null) {
                return "null";
            }
            return input;
        }

        public static void main(String[] args) throws IOException {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            String line;
            while ((line = in.readLine()) != null) {
                String s = stringToString(line);

                int ret = new Solution().romanToInt(s);

                String out = String.valueOf(ret);

                System.out.print(out);
            }
        }
    }

