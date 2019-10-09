package model;

public class ASRemoveDTO extends TeacherLectureDTO{
	String Grade;
	String depart;
	
	public String getDepart() {
		return depart;
	}

	public void setDepart(String depart) {
		this.depart = depart;
	}

	public String getGrade() {
		return Grade;
	}

	public void setGrade(String grade) {
		Grade = grade;
	}
	
}
