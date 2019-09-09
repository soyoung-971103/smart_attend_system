package model;

public class DepartDTO {
	
	int id;
	String name;
	byte classnum;
	byte gradesystem;
	String abbreviation;
	
	
	public String getAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public byte getClassnum() {
		return classnum;
	}
	public void setClassnum(byte classnum) {
		this.classnum = classnum;
	}
	public byte getGradesystem() {
		return gradesystem;
	}
	public void setGradesystem(byte gradesystem) {
		this.gradesystem = gradesystem;
	}

}