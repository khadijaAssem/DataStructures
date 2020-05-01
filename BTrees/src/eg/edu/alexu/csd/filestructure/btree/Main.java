package eg.edu.alexu.csd.filestructure.btree;

import org.junit.Assert;

import java.util.HashSet;

public class Main {
	public static void main(String[] args) {
		IBTree<Integer, Integer> btree = new BTree<Integer, Integer>(2);
		int[] a = new int[]{
				1 ,
				17
				,24 ,34,
				31,31
				,36 ,37 ,38 ,30 ,20 ,2 ,38 ,32 ,33 ,37 ,9 ,16 ,14 ,36 ,1,4 ,0, 6, 37 ,14 ,5 ,11,4 ,22 ,37 ,6 ,39 ,25 ,20 ,5,35 ,29 ,13,
				23,
		};
		int[] b = new int[]{
				1,31,38,16,4,39,5,29,13
		};
		for(int i=0;i<a.length;i++) btree.insert(a[i],a[i]);
//		traverse(btree.getRoot());
//		HashSet<Integer> deleteSet = new HashSet<>();
		for (int i=0;i<b.length;i++){
			System.out.println("Deleted "+b[i]+" "+btree.delete(b[i]));
			print(btree.getRoot(),0);
//			deleteSet.add(b[i]);
		}
//		for(int i=0;i<a[i];i++) deleteSet.add(a[i]);
//		for (Integer i : deleteSet) {
//			System.out.println(btree.delete(i));
//		}
//		traverse(btree.getRoot());

	}
	public static void print(IBTreeNode node, int j){
		if(node.getNumOfKeys()>0) {
			System.out.println(j + ":" + node.getKeys());
			j++;
			for(int i=0; i<node.getChildren().size(); i++)
				print((IBTreeNode) node.getChildren().get(i), j);
		}
	}

}