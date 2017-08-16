package in.mywork;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class EmployeeDAOImpl implements EmployeeDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void save(Employee employee) {
		String query = "insert into Employee (empid, name) values (?,?)";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, employee.getId());
			ps.setString(2, employee.getName());

			int out = ps.executeUpdate();
			if (out != 0) {
				System.out
						.println("Employee saved with id=" + employee.getId());
			} else
				System.out.println("Employee save failed with id="
						+ employee.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public Employee getById(int id) {
		String query = "select name from Employee where empid = ?";
		Employee emp = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				emp = new Employee();
				emp.setId(id);
				emp.setName(rs.getString("name"));

				System.out.println("Employee Found::" + emp);
			} else {
				System.out.println("No Employee found with empid=" + id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return emp;
	}

	public void update(Employee employee) {
		String query = "update Employee set name=? where empid=?";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, employee.getName());

			ps.setInt(2, employee.getId());
			int out = ps.executeUpdate();
			if (out != 0) {
				System.out.println("Employee updated with empid="
						+ employee.getId());
			} else
				System.out.println("No Employee found with empid="
						+ employee.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void deleteById(int empid) {
		String query = "delete from Employee where empid=?";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, empid);
			int out = ps.executeUpdate();
			if (out != 0) {
				System.out.println("Employee deleted with id=" + empid);
			} else
				System.out.println("No Employee found with id=" + empid);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public List<Employee> getAll() {
		String query = "select empid, name from Employee";
		List<Employee> empList = new ArrayList<Employee>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getInt("empid"));
				emp.setName(rs.getString("name"));

				empList.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return empList;
	}

}
