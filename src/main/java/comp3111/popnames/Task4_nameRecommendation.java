package comp3111.popnames;
import java.util.Random;

public class Task4_nameRecommendation {
	
	public static String t4x1_func(int dadYOB, int momYOB, String dadName, String momName) {
		String[][] topN_M = Task1_AnalyzeNames.getTopNnames(dadYOB, 1, "M");
		String[][] topN_F = Task1_AnalyzeNames.getTopNnames(momYOB, 1, "F");
		String oReport = "";
		
		oReport = String.format("%s is the most popular male name in the year", topN_M[0][0]);
		oReport += String.format("%d.\n", dadYOB);
		
		oReport += String.format("%s is the most popular female name in the year", topN_F[0][0]);
		oReport += String.format("%d.\n", momYOB);
		
		return oReport;	
	}

	public static String t4x2_func(int dadYOB, int momYOB, String dadName, String momName) {
		String[][] topN_M = Task1_AnalyzeNames.getTopNnames(dadYOB, 10, "M");
		String[][] topN_F = Task1_AnalyzeNames.getTopNnames(momYOB, 10, "F");
		String boyName = "";
		String girlName = "";
		int boyNamePlace = 0;
		int girlNamePlace = 0;
		
		for(int i = 0; i < 10; ++i) {
			Random rand = new Random();
			int randomTopN = rand.nextInt(10);
			if(topN_M[randomTopN][0] != dadName) {
				boyName = topN_M[randomTopN][0];
				boyNamePlace = randomTopN;
				break;
			}
		}
		
		for(int j = 0; j < 10; ++j) {
			Random rand = new Random();
			int randomTopN = rand.nextInt(10);
			if(topN_F[randomTopN][0] != momName) {
				girlName = topN_F[randomTopN][0];
				girlNamePlace = randomTopN;
				break;
			}
		}
		
		String oReport = "";
		oReport = String.format("%s is the ", boyName);
		oReport += String.format("%s-th popular male name in the year ", boyNamePlace);
		oReport += String.format("%d.\n", dadYOB);
		
		oReport += String.format("%s is the ", girlName);
		oReport += String.format("%s-th popular female name in the year ", girlNamePlace);
		oReport += String.format("%d.\n", momYOB);
		
		return oReport;	
	}
}
