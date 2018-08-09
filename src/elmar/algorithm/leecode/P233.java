package elmar.algorithm.leecode;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import elmar.algorithm.DynamicProgramProblem;

public class P233 {
	static class CountDigitOne extends DynamicProgramProblem<Integer, Integer>{
		
		int[] powerOf10s = new int[100];
		
		@Override
		protected void init() {
			long k = 1;
			for(int i=0;k<Integer.MAX_VALUE;i++,k*=10) {
				powerOf10s[i] =(int) k;
			}
		}
		
		private int numOfDigits(int num) {
			if(num==0)return 1;
			int i=0;
			while(num>=powerOf10s[i]) {
				i++;
			}
			return i;
				
		}
		@Override
		public List<Integer> getDependenciesOf(Integer input) {
			if(input<10) return Collections.emptyList();
			int left = 0, right = 0;
			for(int i=1;i<powerOf10s.length; i++) {
				if(powerOf10s[i]>input) {
					right = input%powerOf10s[i-1];
					left = input - right;
					break;
				}
			}
			
			if(right==0)
				return Collections.singletonList(left-1);
			else return Arrays.asList(right,left-1);
		}

		@Override
		public Integer resolveWithDependencies(Integer input) {
			if(input<10) return 1;

			int firstDigit = 0, left = 0, right = 0;
			for(int i=1;i<powerOf10s.length; i++) {
				if(powerOf10s[i]>input) {
					firstDigit = input/powerOf10s[i-1];
					right = input%powerOf10s[i-1];
					left = input - right;
					break;
				}
			}
			
			if(right==0) {
				return resolvedProblems.get(left - 1) + (firstDigit==1 ? 1 : 0);
			}
			else {
				return (firstDigit==1 ? right + 1: 0 ) + resolvedProblems.get(right)+resolvedProblems.get(left-1);
			}
			
		}
		
	}
	

	
	public static void main(String[] args) {
		CountDigitOne problem = new CountDigitOne();
		problem.resolve(11);
		int numOfOne = 0;
		for(int i=1; i<1000;i++) {
			numOfOne+=countOne(i);
			Integer resolved = problem.resolve(i);
			//if(resolved!=numOfOne)
				System.out.println(i+"---"+resolved+"--"+numOfOne);
		}
	}



	private static int countOne(int i) {
		return (i+"").replaceAll("[^1]", "").length();
	}
}
