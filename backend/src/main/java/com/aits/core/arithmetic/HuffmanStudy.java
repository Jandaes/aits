package com.aits.core.arithmetic;


import java.util.PriorityQueue;

/**
 * @author jared
 * 1、构建树结构，节点，叶子节点，树上要带有权值属性,节点上要有左子节点，右子节点
 */
public class HuffmanStudy {

    class HuffmanTree implements Comparable<HuffmanTree>{
        int freq;

        HuffmanTree(int freq) {
            this.freq = freq;
        }

        // compares on the frequency
        @Override
        public int compareTo(HuffmanTree tree) {
            return freq - tree.freq;
        }



    }

    class TreeNode extends HuffmanTree {
        HuffmanTree left, right;

        TreeNode(HuffmanTree left, HuffmanTree right) {
            super(left.freq + right.freq);
            this.left = left;
            this.right = right;
        }
    }

    class TreeLeaf extends HuffmanTree {
        int val;

        TreeLeaf(int freq, int val) {
            super(freq);
            this.val = val;
        }
    }

    public HuffmanTree buildTree(int[] charFreqs) {
        PriorityQueue<HuffmanTree> trees=new PriorityQueue<HuffmanTree>();
        for (int i = 0; i < charFreqs.length ; i++) {
            if(charFreqs[i]>0){
               trees.offer(new TreeLeaf(charFreqs[i],i));
            }
        }

        while (trees.size()>1){
           HuffmanTree left=trees.poll();
           HuffmanTree right=trees.poll();
           trees.offer(new TreeNode(left,right));
        }

        return trees.poll();
    }


    public static void printCodes(HuffmanTree tree, StringBuffer prefix) {
        assert tree != null;
        if (tree instanceof TreeLeaf) {
            TreeLeaf leaf = (TreeLeaf)tree;

            // print out character, frequency, and code for this leaf (which is just the prefix)
            System.out.println(leaf.val + "\t" + leaf.freq + "\t" + prefix);

        } else if (tree instanceof TreeNode) {
            TreeNode node = (TreeNode)tree;

            // traverse left

            prefix.append('0');
            printCodes(node.left, prefix);
            prefix.deleteCharAt(prefix.length()-1);

            // traverse right
            prefix.append('1');
            printCodes(node.right, prefix);
            prefix.deleteCharAt(prefix.length()-1);
        }
    }

    public void compress(){

    }

    public void uncompress(){

    }


    public static void main(String[] args) {
        byte[] b= DataSort.image2byte("C:\\\\Users\\\\Administrator\\\\Desktop\\\\aa.png");
        System.out.println("该图片二进制为:");
        StringBuilder sbr=new StringBuilder();
        for (int i = 0; i <b.length ; i++) {
            //   System.out.print(b[i]+" ");
            Integer in=new Integer(b[i]);
            sbr.append(in+128+",");
            String s=Integer.toBinaryString(in);
            // System.out.println();
            System.out.print(s+" ");
        }
        System.out.println();
        System.out.println(sbr);
        String  s=sbr.toString();
        int[] a=new int[256000];
        String sdata[]= s.split(",");

        for ( String c: sdata)
        {
            a[Integer.parseInt(c)+128]++;
        }
        HuffmanStudy huffmanStudy=new HuffmanStudy();
        HuffmanTree tree = huffmanStudy.buildTree(a);

        // print out results
        System.out.println("SYMBOL\tWEIGHT\tHUFFMAN CODE");
        printCodes(tree, new StringBuffer());
    }

}
