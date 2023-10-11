package oit.is.b20089.shiftoptimizer.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;

@Mapper
public interface EmployeeMapper {
  @Select("SELECT * FROM Employees")
  List<Employee> getAllEmployees();
  

  @Select("SELECT * FROM Employees WHERE employeeID = #{employeeID}")
  Employee getEmployeeById(Long employeeID);

  // 他のクエリメソッド
}
