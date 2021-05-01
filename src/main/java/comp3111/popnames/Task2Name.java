package comp3111.popnames;

public class Task2Name {
	private String no = null;
	private String name = null;
	private String frequency = null;
	private String occurrences = null;
	private String percentage = null;
	
    public Task2Name() {
    }

    public Task2Name(String no,String name,String frequency,String occurrences,String percentage) {
    	this.no = no;
        this.name = name;
        this.frequency = frequency;
        this.occurrences = occurrences;
        this.percentage = percentage;
    }
    
    public String getNo() {
        return no;
    }
    
    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }
    
    public String getOccurrences() {
        return occurrences;
    }

    public void setOccurrences(String occurrences) {
        this.occurrences = occurrences;
    }
    
    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }
    
}
