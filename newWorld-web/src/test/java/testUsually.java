import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Xavier.liu
 * @date 2020/6/15 10:51
 */
public class testUsually {
    public static void main(String[] args) {
//        int a = Integer.MAX_VALUE ;
//        System.out.println(a * 10);
//        System.out.println(Integer.MIN_VALUE);
        System.out.println(aa(1));
    }

    int reverse(int x){
        // pop = x%10 temp = x/10  rev= rev * 10 + pop
        int rev = 0;
        while (x != 0){
            int pop = x % 10;
//            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE/10 && pop > Integer.MAX_VALUE % 10)) {
            //运算会直接生成区间内的，所以只能单个变量比较
            if (rev * 10 + pop > Integer.MAX_VALUE) {
                return 0;
            }
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE/10 && pop < Integer.MIN_VALUE % 10)) {
                return 0;
            }
            rev = rev * 10 + pop;
            x = x / 10;
        }
        return rev;
    }

    static int aa(int x){
        try{
            if (x == 1){
                return 1;
            }else {
                x ++;
            }
        }catch (Exception e){

        }finally {
            System.out.println("不管是否return，总会执行finally");
        }
        return x;
    }

//    public boolean releaseLock_with_lua(String key,String value) {
//        String luaScript = "if redis.call('get',KEYS[1]) == ARGV[1] then " +
//                "return redis.call('del',KEYS[1]) else return 0 end";
//        return jedis.eval(luaScript, Collections.singletonList(key), Collections.singletonList(value)).equals(1L);
//    }

    public int strStr(String haystack, String needle) {
        if (("").equals(needle) || ("").equals(haystack)) return 0;
        int i = 0;
        for (; i< haystack.length(); i++){
            if (haystack.charAt(i) == needle.charAt(0)){
                int j = 1;
                for (;j < needle.length(); j++){
                    if (haystack.charAt(i + j) != needle.charAt(j)){
                        break;
                    }
                }
                if (j == needle.length()){
                    return i;
                }
            }
        }
        //遍历结束没有在haystack
        return 0;
    }

    /**
     * [-2,1,-3,4,-1,2,1,-5,4],

       -2;
       -2+1 1
       -2+1+-3 1+-3 -3

     [-2,1,-3,4,-1,2,1,-5,4]
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        if (nums.length < 1) throw  new IllegalArgumentException();
        int maxSub = nums[0];
        for (int i = 1; i < nums.length; i++) {
            maxSub += nums[i];
            if (nums[i] > 0){

            }
            if (maxSub < nums[i])
                maxSub = nums[i];
        }

        return maxSub;
    }

    /**
     *
     *  0; 1,2 ; 3,4,5,6;   7,8,9,10,11,12,13,14;
     *     [1, 2,2, 3,4,4,3, 5,6,7,8,8,7,6,5]
     *     1 2 4 8 16
     *     2^4 -1
     *
     */
    /**
     * Definition for a binary tree node.
    */
    class TreeNode {
         int val;
          TreeNode left;
          TreeNode right;
         TreeNode(int x) { val = x; }
    }
    class Solution {
        public boolean isSymmetric(TreeNode root) {
            return  check(root,root);
        }
        //递归
        private boolean check(TreeNode node1,TreeNode node2){
            if (node1  == null && node2  == null){
                return true;
            }
            if (node1  == null || node2  == null){
                return false;
            }
            return node1.val == node2.val && check(node1.left,node2.right) && check(node1.right,node2.left);
        }
        //迭代. 引入队列通常是递归转迭代的方式。 [1,2,2,null,3,null,3]
        private boolean check2(TreeNode node1,TreeNode node2){
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(node1);
            queue.offer(node2);
            while (!queue.isEmpty()){

                TreeNode p = queue.poll();
                TreeNode q = queue.poll();
                if(p == null && q == null)
                    return true;
                if(p  == null || q == null)
                    return false;
                if (p.val != q.val) return false;

                queue.offer(p.left);
                queue.offer(q.right);

                queue.offer(p.right);
                queue.offer(q.left);
            }
            return true;
        }
    }
//    public void judge(int[] a){
//        if((a.length+1)/2)
//        for (int i = 0;i<a.length;i=2^i -1){
//
//        }
//    }
}
