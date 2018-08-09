package elmar.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class DynamicProgramProblem<Input, Output> {
	private class DynamicProgramProblemNode extends TreeNode<Input> {
		public DynamicProgramProblemNode(Input val) {
			super(val);
		}

		@Override
		public Iterator<? extends TreeNode<Input>> children() {
			List<Input> dependencies = DynamicProgramProblem.this.getDependenciesOf(getValue());
			List<TreeNode<Input>> unresolvedDeps = new ArrayList<>();
			for (Input dep : dependencies) {
				if (!DynamicProgramProblem.this.resolvedProblems.containsKey(dep)) {
					unresolvedDeps.add(new DynamicProgramProblemNode(dep));
				}
			}
			return unresolvedDeps.iterator();
		}

	}

	private class DynamicaProblemNodeVisitor implements NodeVisitor<Input> {

		@Override
		public void visit(TreeNode<Input> node) {
			Input input = node.getValue();
			Output anwser = resolveWithDependencies(input);
			resolvedProblems.put(input, anwser);
		}

	}

	protected Map<Input, Output> resolvedProblems = new HashMap<>();

	public abstract List<Input> getDependenciesOf(Input input);

	public abstract Output resolveWithDependencies(Input input);

	protected void init() {
	};

	public Output resolve(Input input) {
		init();
		TreeVisitUtil.dfsVisit(new DynamicProgramProblemNode(input), new DynamicaProblemNodeVisitor(), false);
		return resolvedProblems.get(input);
	}

	public static void main(String[] args) {
		DynamicProgramProblem<Integer, Integer> problem = new DynamicProgramProblem<Integer, Integer>() {
			private int[] prices = { 0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30 };

			@Override
			public List<Integer> getDependenciesOf(Integer input) {
				List<Integer> deps = new ArrayList<>();
				for (int i = 1; i < input; i++) {
					deps.add(i);
				}
				return deps;
			}

			@Override
			public Integer resolveWithDependencies(Integer input) {
				int max = input < prices.length ? prices[input] : 0;
				for (int i = 1; i < input; i++) {
					int price = resolvedProblems.get(i) + resolvedProblems.get(input - i);
					if (price > max)
						max = price;
				}
				return max;
			}

		};
		for (int i = 0; i < 100; i++) {
			System.out.println(problem.resolve(i));
		}
	}

}
