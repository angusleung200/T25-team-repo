package comp3111.popnames;

public class Task5Name {
	private String no = null;
	private String name = null;
    public Task5Name() {
    }

    public Task5Name(String no,String name) {
    	this.no = no;
        this.name = name;
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

}
