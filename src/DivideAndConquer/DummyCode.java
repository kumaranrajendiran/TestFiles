package DivideAndConquer;

public class DummyCode {
	public int computeProfit(int[] startTime, int[] finishTime, int[] profit,
			int numActivities) {
		int ASP[][][] = new int[numActivities + 1][numActivities + 1][numActivities + 1];
		int maxProfit = Integer.MIN_VALUE;
		int excessShortJobs = 0;

		for (int s = 0; s <= numActivities; s++) {
			for (int l = 0; l <= numActivities; l++) {
				for (int i = 0; i <= numActivities; i++) {
					ASP[i][s][l] = Integer.MIN_VALUE;
				}
			}
		}
		ASP[0][0][0] = 0;

		for (int s = 1; s <= numActivities; s++) {
			for (int l = 0; l <= numActivities; l++) {
				for (int i = 1; i <= numActivities; i++) {
					if ((s + l) <= i) {
						ASP[i][s][l] = ASP[i - 1][s][l];
						int j = i - 1;
						while (j > 0 && finishTime[j - 1] > startTime[i - 1]) {
							j = j - 1;
						}
						if (finishTime[i - 1] - startTime[i - 1] <= 4) {

							ASP[i][s][l] = Math.max(ASP[i][s][l], profit[i - 1]
									+ ASP[j][s - 1][l]);
						} else {
							if (l != 0) {
								ASP[i][s][l] = Math.max(ASP[i][s][l],
										profit[i - 1] + ASP[j][s][l - 1]);
							}
						}
					} else {
						ASP[i][s][l] = Integer.MIN_VALUE;
					}
				}
			}
		}
		for (int i = 1; i <= numActivities; i++) {
			for (int s = 0; s <= numActivities; s++) {
				for (int l = 0; l < s; l++) {
					// System.out.print("ASP[" + i + "][" + s + "][" + l + "] ="
					// + ASP[i][s][l] + "\t");
					if (maxProfit < ASP[i][s][l] && l < s) {
						maxProfit = ASP[i][s][l];
						excessShortJobs = s - l;
					}
				}
				// System.out.println();
			}
		}
		System.out.println("Excess Short Jobs " + excessShortJobs);
		return maxProfit;
	}
}
