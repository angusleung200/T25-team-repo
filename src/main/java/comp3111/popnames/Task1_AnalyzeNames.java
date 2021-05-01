package comp3111.popnames;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import edu.duke.FileResource;

public class Task1_AnalyzeNames {
	
	public static CSVParser getFileParser(int year) {
	     FileResource fr = new FileResource(String.format("dataset/yob%s.csv", year));
	     return fr.getCSVParser(false);
		}
	 
		
		public static String getSummary(int year) {
			String oReport = "";	
			int totalBirths = 0;
			int totalBoys = 0;
			int totalGirls = 0;
			int totalNames = 0;
			int uniqueBoys = 0;
			int uniqueGirls = 0;
						
			for (CSVRecord rec : getFileParser(year)) {
				int numBorn = Integer.parseInt(rec.get(2));
				totalBirths += numBorn;
				totalNames += 1;
				if (rec.get(1).equals("M")) {
					totalBoys += numBorn;
					uniqueBoys++;
				}
				else {
					totalGirls += numBorn;
					uniqueGirls++;
				}
			}
			
			oReport = String.format("Summary (Year of %d):\n", year);
			oReport += String.format("Total Births = %,d\n", totalBirths);
			oReport += String.format("***Baby Girls = %,d\n", totalGirls);
			oReport += String.format("***Baby Boys = %,d\n", totalBoys);
			
			oReport += String.format("Total Number of Unique Names = %,d\n", totalNames);
			oReport += String.format("***Unique Names (baby girls) = %,d\n", uniqueGirls);
			oReport += String.format("***Unique Names (baby boys) = %,d\n", uniqueBoys);
			
			return oReport;
		}
	
}
