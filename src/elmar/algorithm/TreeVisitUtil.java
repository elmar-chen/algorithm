package elmar.algorithm;

import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class TreeVisitUtil {
	public static <T> void bfsVisit(TrackableTreeNode<T> root, NodeVisitor<T> visitor) {
		Deque<TrackableTreeNode<T>> queue = new LinkedList<>();
		queue.push(root);
		while (!queue.isEmpty()) {
			TrackableTreeNode<T> node = queue.removeFirst();
			if (!node.isVisited()) {
				node.setVisited(true);
				visitor.visit(node);
				Iterator<? extends TrackableTreeNode<T>> children = node.children();
				while (children.hasNext()) {
					TrackableTreeNode<T> child = children.next();
					queue.addLast(child);
				}
			}
		}
	}
	public static <T> void dfsVisit(TreeNode<T> root, NodeVisitor<T> visitor) {
		dfsVisit(root, visitor, true);
	}
	public static <T> void dfsVisit(TreeNode<T> root, NodeVisitor<T> visitor, boolean parentFirst) {
		
		Stack<Iterator<? extends TreeNode<T>>> stackIter = new Stack<>();
		Stack<TreeNode<T>> stackNode = new Stack<>();
		stackIter.push(root.children());
		stackNode.push(root);
		if(parentFirst) visitor.visit(root);
		while (!stackNode.isEmpty()) {
			Iterator<? extends TreeNode<T>> nodeIterator = stackIter.peek();
			if (nodeIterator.hasNext()) {
				TreeNode<T> next = nodeIterator.next();
				stackNode.push(next);
				stackIter.push(next.children());
				if(parentFirst)  visitor.visit(next);
			} else {
				TreeNode<T> node = stackNode.pop();
				stackIter.pop();
				if(!parentFirst) visitor.visit(node);
			}
		}
	}

	static class NumberTreeNode extends TrackableTreeNode<Integer> {
		List<NumberTreeNode> children = new ArrayList<>();

		public NumberTreeNode(int val, NumberTreeNode parent) {
			super(val);
			if (parent != null)
				parent.children.add(this);
		}

		@Override
		public Iterator<NumberTreeNode> children() {
			return children.iterator();
		}

	}

	public static void main(String[] args) {
		NumberTreeNode[] nodes = new NumberTreeNode[10];
		nodes[0] = new NumberTreeNode(0, null);
		nodes[1] = new NumberTreeNode(1, nodes[0]);
		nodes[2] = new NumberTreeNode(2, nodes[0]);
		nodes[3] = new NumberTreeNode(3, nodes[1]);
		nodes[4] = new NumberTreeNode(4, nodes[1]);
		nodes[5] = new NumberTreeNode(5, nodes[1]);
		nodes[6] = new NumberTreeNode(6, nodes[2]);
		nodes[7] = new NumberTreeNode(7, nodes[6]);
		nodes[8] = new NumberTreeNode(8, nodes[6]);
		nodes[9] = new NumberTreeNode(9, nodes[6]);

		NodeVisitor<Integer> visitor = new NodeVisitor<Integer>() {
			@Override
			public void visit(TreeNode<Integer> node) {
				// path.push(node.getValue());
				// System.out.println(path);
				System.out.print(node.getValue());
			}

		};
		bfsVisit(nodes[0], visitor);
		System.out.println();
		dfsVisit(nodes[0], visitor);
		System.out.println();
		dfsVisit(nodes[0], visitor,false);
		}

	
}
