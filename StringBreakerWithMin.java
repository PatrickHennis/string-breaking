import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.LinkedList;
//import java.util.List;

public class StringBreakerWithMin {
	private static int[][] memo;
	private static int min = 0;
	private static int result;

	public static int helper(ArrayList<Integer> breakpoint, int sum, int start, int end) {
		int stringLength = end - start;
		int breakPos = 0;

		if (breakpoint.size() > 1) {
			if (memo[start][end] == -1) {
				for (int i = 0; i < breakpoint.size() - 1; i++) {
					breakPos = breakpoint.get(i);
					breakpoint = removeElements(breakpoint, breakPos);
					// ^ this needs to be moved elsewhere or something
					sum = stringLength + helper(breakpoint, sum, 0, breakPos)
							+ helper(breakpoint, sum, breakPos + 1, stringLength);
				}

				memo[start][end] = stringLength;
			} else {
				sum = memo[start][end];
			}

			return sum;
		} else {
			memo[start][end] = stringLength;
			return stringLength;
		}
	}

	public static int[][] initArray(int rows, int cols) {
		int[][] memo = new int[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				memo[i][j] = -1;
			}
		}

		return memo;
	}
	
	public static ArrayList<Integer> removeElements(ArrayList<Integer> breakpoint, Integer breakPos) {
		ArrayList<Integer> result = new ArrayList<Integer>(breakpoint);
		
		result.remove(result.indexOf(breakPos));
		
		for (int i = 0; i < breakpoint.size(); i++) {
			if (breakpoint.get(i) > breakPos) {
				result.remove(result.indexOf(breakpoint.get(i)));
			}
		}
		
		return result;
	}

	public static void main(String args[]) {
		int stringLength = 20;
		ArrayList<Integer> breakpoint = new ArrayList<Integer>();
		breakpoint.add(2); breakpoint.add(4); breakpoint.add(8);
		int sum = 0;
		memo = initArray(stringLength, stringLength + 1);
		sum = helper(breakpoint, 0, 0, stringLength);
		System.out.println("Final sum: " + sum);
	}
}