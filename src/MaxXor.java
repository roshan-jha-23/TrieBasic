import java.util.*;

public class MaxXor {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of elements: ");
        int n = scanner.nextInt();
        int[] nums = new int[n];

        System.out.println("Enter the elements:");
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        int result = xor(nums);
        System.out.println("Maximum XOR value among pairs: " + result);
    }

    static int xor(int[] nums) {
        Trie5 trie = new Trie5();
        for (int x : nums) {
            trie.insert(x);
        }
        int maxXor = 0;
        for (int x : nums) {
            maxXor = Math.max(maxXor, trie.getMax(x));
        }
        return maxXor;
    }
    static int[] maximizeXor(int[] nums, int[][] queries){
        Trie5 trie=new Trie5();
        Arrays.sort(nums);
        ArrayList<ArrayList<Integer>>oq=new ArrayList<>();
        for(int i=0;i<queries.length;i++){
            ArrayList<Integer>temp=new ArrayList<>();
            temp.add(queries[i][1]);
            temp.add(queries[i][0]);
            temp.add(i);
            oq.add(temp);
        }
        Collections.sort(oq, (o1, o2) -> Integer.compare(o1.get(0), o2.get(0)));
        int m=queries.length;
        int ind=0;
        int[]ans=new int[m];
        for(int i=0;i<m;i++){
            int ai=oq.get(i).get(0);
            int xi=oq.get(i).get(1);
            int qid=oq.get(i).get(2);
            while (ind<nums.length && nums[ind]<=ai){
                trie.insert(nums[ind]);
                ind++;
            }
            if(ind==0)ans[qid]=-1;
            else ans[qid]=trie.getMax(xi);
        }
        return ans;

    }
}

class XorNode {
    private static final int BITSNO = 2;
    XorNode[] links = new XorNode[BITSNO];

    boolean containsKey(int ind) {
        return links[ind] != null;
    }

    XorNode get(int ind) {
        return links[ind];
    }

    void put(int ind, XorNode node) {
        links[ind] = node;
    }
}

class Trie5 {
    private XorNode root;

    Trie5() {
        root = new XorNode();
    }

    public void insert(int num) {
        XorNode node = root;
        for (int i = 31; i >= 0; i--) {
            int bit = (num >> i) & 1;
            if (!node.containsKey(bit)) {
                node.put(bit, new XorNode());
            }
            node = node.get(bit);
        }
    }

    public int getMax(int num) {
        XorNode node = root;
        int maxNum = 0;
        for (int i = 31; i >= 0; i--) {
            int bit = (num >> i) & 1;
            if (node.containsKey(1 - bit)) {
                maxNum = maxNum | (1 << i);
                node = node.get(1 - bit);
            } else {
                node = node.get(bit);
            }
        }
        return maxNum;
    }
}
