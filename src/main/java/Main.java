import java.util.Scanner;

public class Main {
	private static Scanner sc;

	public static void main(String[] args) {
		sc = new Scanner(System.in);

		int n = sc.nextInt();
		Node first = new Node(1);
		Node pNode = first;
		for (int i = 2; i <= n; i++) {
			pNode.node = new Node(i);
			pNode = pNode.node;
		}
		pNode.node = first;
		Node tem = first;
		int j = 1;
		while (tem != null) {
			if (tem.node.num == tem.num) {
				System.out.println(tem.num);
				break;
			}
			if (j == 3) {
				tem.node = tem.node.node;
				j = 1;
			} else {
				j++;
			}
			tem = tem.node;

		}
	}

}

class Node {
	int num;
	Node node;

	public Node(int num) {
		this.num = num;
	}
}
