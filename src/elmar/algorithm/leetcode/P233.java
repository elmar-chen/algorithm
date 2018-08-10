package elmar.algorithm.leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import elmar.algorithm.DynamicProgramProblem;

public class P233 {
	// 234: firstDigit->2, left-> 200, rightâ€?>34
	static class NumberParts {
		int firstDigit;
		int left;
		int right;
	}

	static class CountDigitOne extends DynamicProgramProblem<Integer, Long> {

		int[] powerOf10s = new int[100];

		@Override
		protected void init() {
			long k = 1;
			int i = 0;
			for (; k < Integer.MAX_VALUE; i++, k *= 10) {
				powerOf10s[i] = (int) k;
			}
			powerOf10s[i] = Integer.MAX_VALUE;
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
		public Long resolveWithDependencies(Integer input) {
			if (input < 10) {
				return input < 0 ? 0L : 1L;
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
			while (i < powerOf10s.length && powerOf10s[i] <= input && powerOf10s[i] < Integer.MAX_VALUE) {
				i++;
			}
			parts.firstDigit = input / powerOf10s[i - 1];
			parts.right = input % powerOf10s[i - 1];
			parts.left = input - parts.right;
			return parts;
		}

	}

}
