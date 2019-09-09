package model;

public class TeacherLectureAllDTO extends StudentLectureInfoDTO{
	int iattend;
	int ixhour;
	int ilate;

	public int getIattend() {
		return iattend;
	}
	public void setIattend(int iattend) {
		this.iattend = iattend;
	}
	public int getIxhour() {
		return ixhour;
	}
	public void setIxhour(int ixhour) {
		this.ixhour = ixhour;
	}
	public int getIlate() {
		return ilate;
	}
	public void setIlate(int ilate) {
		this.ilate = ilate;
	}
}