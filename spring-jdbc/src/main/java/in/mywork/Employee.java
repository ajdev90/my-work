package in.mywork;

public class Employee {

	private int empid;
	private String name;

	public int getId() {
		return empid;
	}

	public void setId(int empid) {
		this.empid = empid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "{empid=" + empid + ",Name=" + name + "}";
	}

}
