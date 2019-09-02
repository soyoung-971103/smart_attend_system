package model;

public class SubjectDTO {

	int id;
	DepartDTO depart;
	String code;
	int yyyy;
	byte grade;
	byte term;
	String ismajor;
	String ischoice;
	String ispractice;
	String name;
	float ipoint;
	byte ihour;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public DepartDTO getDepart() {
		return depart;
	}
	public void setDepart(DepartDTO depart) {
		this.depart = depart;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getYyyy() {
		return yyyy;
	}
	public void setYyyy(int yyyy) {
		this.yyyy = yyyy;
	}
	public byte getGrade() {
		return grade;
	}
	public void setGrade(byte grade) {
		this.grade = grade;
	}
	public byte getTerm() {
		return term;
	}
	public void setTerm(byte term) {
		this.term = term;
	}
	public String getIsmajor() {
		return ismajor;
	}
	public void setIsmajor(String ismajor) {
		this.ismajor = ismajor;
	}
	public String getIschoice() {
		return ischoice;
	}
	public void setIschoice(String ischoice) {
		this.ischoice = ischoice;
	}
	public String getIspractice() {
		return ispractice;
	}
	public void setIspractice(String ispractice) {
		this.ispractice = ispractice;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getIpoint() {
		return ipoint;
	}
	public void setIpoint(float ipoint) {
		this.ipoint = ipoint;
	}
	public byte getIhour() {
		return ihour;
	}
	public void setIhour(byte ihour) {
		this.ihour = ihour;
	}
	
}
