package comp3111.popnames;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import edu.duke.FileResource;

public class Task6Name {
	
	public static CSVParser getFileParser(int year) {
	     FileResource fr = new FileResource(String.format("dataset/yob%s.csv", year));
	     return fr.getCSVParser(false);
		}
	
	public static String t6x1_func(String iName, String iMateName) 
	{
		double oScore;
		if(iName.length()==iMateName.length()) 
		{
			oScore= 100;
		}
		else 
		{
			oScore = 0;
		}
		return String.format("oScore: %.2f%%", oScore);
	}
	
	public static String t6x2_func(String iName, String iMateName, String iGender, String iMateGender, int iYOB, String iPreference) 
	{
		if(iMateName.equals(iName)) 
		{
			
			return "0";
		}
		if(iYOB>2002) 
		{
			
			return "0";
			
		}
		double oScore=0;
		int counter=0;
		for (CSVRecord rec : getFileParser(iYOB)) 
		{
			if(rec.get(1).equals(iMateGender)) 
			{counter++;}
		}
		int number =1;
		int onumber =0;
		for (CSVRecord rec : getFileParser(iYOB)) 
		{
			if(rec.get(1).equals(iMateGender)) 
			{
				
				if(rec.get(0).equals(iMateName)) 
				{
					onumber=number;
					break;
				}
				number++;
			}
		}
		if(onumber == 0) 
		{
			
			return "0";
		}
		else 
		{
			oScore = onumber/counter*50;
		}
		
		if(iPreference.equals("Y")&&iYOB<2002) 
		{
			int rankMateY = Task3Name.getMostPopRank(iYOB,iYOB+10,iMateName,iMateGender);
			
			int irank = Task3Name.getRank(iYOB,iName,iGender);
			oScore +=  (rankMateY-irank) / (rankMateY+irank)*50;
		}
		
		if(iPreference.equals("O")&&iYOB>1890) 
		{
			int rankMateO = Task3Name.getMostPopRank(iYOB-10,iYOB,iMateName,iMateGender);
			
			int irank = Task3Name.getRank(iYOB,iName,iGender);
			oScore +=  (rankMateO-irank)/(rankMateO+irank) *50;
		}
		
		return String.format("oScore: %.2f%%", oScore);
		
			
	}
		
	

}
