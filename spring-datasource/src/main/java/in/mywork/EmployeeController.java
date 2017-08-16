package in.mywork;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EmployeeController {

	@Autowired
	@Qualifier("dbDataSource")
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@RequestMapping(value = "/rest/emps", method = RequestMethod.GET)
	public @ResponseBody List<Employee> getAllEmployees() {

		List<Employee> empList = new ArrayList<Employee>();
		
		String query = "select empid, name from Employee";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		List<Map<String, Object>> empRows = jdbcTemplate.queryForList(query);

		for (Map<String, Object> empRow : empRows) {
			Employee emp = new Employee();
			emp.setId(Integer.parseInt(String.valueOf(empRow.get("empid"))));
			emp.setName(String.valueOf(empRow.get("name")));
			emp.setRole(String.valueOf(empRow.get("role")));
			empList.add(emp);
		}

		return empList;
	}

}
