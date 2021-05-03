package comp3111.popnames;

public class Task3Name {
	private String occurrences = null;
	private String percentage = null;
	private String year = null;
	private String rank = null;
	
	public Task3Name() {}
	
	public Task3Name(String occurrences,String percentage, String year, String rank ) 
	{
		this.occurrences=occurrences;
		this.percentage = percentage;
		this.rank = rank;
		this.year = year;
	}
	
	public String getOccurrences() {return occurrences;}
	
	public String getPercentage() {return percentage;}
	
	public String getYear() {return year;}
	
	public String getRank() {return rank;}
	
	public void setOccurrences(String occurrences) {this.occurrences=occurrences;}
	
	public void setPercentage(String percentage) {this.percentage=percentage;}
	
	public void setYear(String year) {this.year=year;}
	
	public void setRank(String rank) {this.rank=rank;}
	

}
