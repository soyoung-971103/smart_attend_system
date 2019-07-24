package model;

public class RoomDTO {

	int id;
	int building_id;
	byte floor;
	String ho;
	int depart_id;
	String name;
	String kind;
	int area;
	BuildingDTO building;
	DepartDTO depart;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBuilding_id() {
		return building_id;
	}
	public void setBuilding_id(int building_id) {
		this.building_id = building_id;
	}
	public byte getFloor() {
		return floor;
	}
	public void setFloor(byte floor) {
		this.floor = floor;
	}
	public String getHo() {
		return ho;
	}
	public void setHo(String ho) {
		this.ho = ho;
	}
	public int getDepart_id() {
		return depart_id;
	}
	public void setDepart_id(int depart_id) {
		this.depart_id = depart_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public int getArea() {
		return area;
	}
	public void setArea(int area) {
		this.area = area;
	}
	public BuildingDTO getBuilding() {
		return building;
	}
	public void setBuilding(BuildingDTO building) {
		this.building = building;
	}
	public DepartDTO getDepart() {
		return depart;
	}
	public void setDepart(DepartDTO depart) {
		this.depart = depart;
	}		
	
}
