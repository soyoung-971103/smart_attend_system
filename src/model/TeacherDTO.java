package model;

public class TeacherDTO {
	int id;
	String depart_id;
	String kind;
	String uid;
	String pwd;
	String name;
	String tel;
	String phone;
	String email;
	String pic;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDepart_id() {
		return depart_id;
	}
	public void setDepart_id(String depart_id) {
		this.depart_id = depart_id;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
}
/*
 * 	create table teacher ( 
   id   int     not null    auto_increment,
   depart_id   int,
   kind   varchar(2),
   uid   varchar(20),
   pwd   varchar(20),
   name   varchar(20),
   tel   varchar(11),
   phone   varchar(11),
   email   varchar(50),
   pic   varchar(255),
   primary key(id)
);
 */