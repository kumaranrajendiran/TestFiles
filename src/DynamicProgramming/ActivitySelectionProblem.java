package DynamicProgramming;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ActivitySelectionProblem {
	public int computeProfit(int[] startTime, int[] finishTime, int[] profit,
			int numActivities) {
		int ASP[][] = new int[numActivities + 1][(2 * numActivities) + 2];
		int maxProfit = 0;
		int numExcessShortActivities = 0;
		for (int i = 0; i < numActivities; i++) {
			for (int j = 0; j <= (2 * numActivities) + 1; j++) {
				ASP[i][j] = Integer.MIN_VALUE;
			}
		}
		ASP[0][numActivities] = 0;
		for (int i = 1; i <= numActivities; i++) {
			for (int k = 1; k <= (2 * numActivities); k++) {

				ASP[i][k] = ASP[i - 1][k];
				int j = i - 1;

				while (j > 0 && finishTime[j - 1] > startTime[i - 1]) {
					j = j-1;

				}

				if (finishTime[i - 1] - startTime[i - 1] > 4) {
					ASP[i][k] = Math.max(ASP[i][k], profit[i - 1]
							+ ASP[j][k + 1]);
				} else
					ASP[i][k] = Math.max(ASP[i][k], profit[i - 1]
							+ ASP[j][k - 1]);
			}
		}

		for (int i = 1; i <= numActivities; i++) {
			for (int k = numActivities+1; k <= 2 * numActivities; k++) {
				if (maxProfit < ASP[i][k]) {
					maxProfit = ASP[i][k];
					numExcessShortActivities = k-numActivities;
				}
			}
		}

		System.out.println("Maximum Profit is  " + maxProfit+"\nExcess Short Activities "+numExcessShortActivities);
		return maxProfit;
	}

	public ArrayList<Activity> getActivities(String fileName) throws Exception {
		File inputFile = new File(fileName);
		BufferedReader bReader = new BufferedReader(new FileReader(inputFile));
		int numActivities = Integer.parseInt(bReader.readLine());
		ArrayList<Activity> activities = new ArrayList<Activity>();
		for (int i = 0; i < numActivities; i++) {
			String line = bReader.readLine();
			String[] input = line.split(" ");
			Activity activity = new Activity();
			activity.startTime = Integer.parseInt(input[0]);
			activity.finishTime = Integer.parseInt(input[1]);
			activity.profit = Integer.parseInt(input[2]);
			activities.add(activity);
		}

		Collections.sort(activities, new Comparator<Activity>() {
			@Override
			public int compare(Activity a1, Activity a2) {
				int result = a1.finishTime - a2.finishTime;
				if (result == 0) {
					result = a1.startTime - a2.startTime;
				}
				return result;
			}
		});
		bReader.close();
		return activities;
	}

	public static void main(String[] args) {
		try {
			ActivitySelectionProblem asp = new ActivitySelectionProblem();
			ArrayList<Activity> activities = asp
					.getActivities("/Users/kumaran/Desktop/companies4.txt");
			int numActivities = activities.size();
			int[] startTime = new int[numActivities];
			int[] finishTime = new int[numActivities];
			int[] profit = new int[numActivities];
			for (int i = 0; i < numActivities; i++) {
				startTime[i] = activities.get(i).startTime;
				finishTime[i] = activities.get(i).finishTime;
				profit[i] = activities.get(i).profit;
			}

			System.out.println("MaxProfit "
					+ asp.computeProfit(startTime, finishTime, profit,
							numActivities));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
