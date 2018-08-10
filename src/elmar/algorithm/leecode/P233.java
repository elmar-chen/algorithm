package elmar.algorithm.leecode;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import elmar.algorithm.DynamicProgramProblem;

public class P233 {
	// 234: firstDigit->2, left-> 200, right—>34
	static class NumberParts {
		int firstDigit;
		int left;
		int right;
	}

	static class CountDigitOne extends DynamicProgramProblem<Integer, Integer> {

		int[] powerOf10s = new int[100];

		@Override
		protected void init() {
			long k = 1;
			for (int i = 0; k < Integer.MAX_VALUE; i++, k *= 10) {
				powerOf10s[i] = (int) k;
			}
		}

		@Override
		public List<Integer> getDependenciesOf(Integer input) {
			if (input < 10) {
				return Collections.emptyList();
			}
			NumberParts parts = splitNumber(input);

			if (parts.right == 0) {
				return Collections.singletonList(parts.left - 1);
			} else {
				return Arrays.asList(parts.right, parts.left - 1);
			}
		}

		@Override
		public Integer resolveWithDependencies(Integer input) {
			if (input < 10) {
				return 1;
			}

			NumberParts parts = splitNumber(input);

			if (parts.right == 0) {
				return resolvedProblems.get(parts.left - 1) + (parts.firstDigit == 1 ? 1 : 0);
			} else {
				return (parts.firstDigit == 1 ? parts.right + 1 : 0) + resolvedProblems.get(parts.right)
						+ resolvedProblems.get(parts.left - 1);
			}

		}

		private NumberParts splitNumber(Integer input) {
			NumberParts parts = new NumberParts();
			int i = 1;
			while (powerOf10s[i] <= input) {
				i++;
			}
			parts.firstDigit = input / powerOf10s[i - 1];
			parts.right = input % powerOf10s[i - 1];
			parts.left = input - parts.right;
			return parts;
		}

	}

	public static void main(String[] args) {
		CountDigitOne problem = new CountDigitOne();
		problem.resolve(11);
		int numOfOne = 0;
		for (int i = 1; i < 1000; i++) {
			numOfOne += (i + "").replaceAll("[^1]", "").length();
			Integer resolved = problem.resolve(i);
			if (resolved != numOfOne) {
				System.out.println(i + "---" + resolved + "--" + numOfOne);
			}
		}
	}

}
