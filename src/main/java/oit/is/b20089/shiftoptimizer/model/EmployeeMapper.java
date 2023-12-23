package oit.is.b20089.shiftoptimizer.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.xml.crypto.Data;

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

  @Select("SELECT * FROM EMPLOYEES, OPTIMIZEDSHIFT  where EMPLOYEES .EMPLOYEEID  = OPTIMIZEDSHIFT .EMPLOYEEID  and SHIFTDATE = #{shiftDate} ORDER BY SHIFTDATE ")
  List<Employee> getNameByDate(Date shiftDate);

  @Select("SELECT * FROM EMPLOYEES WHERE name = #{name}")
  Employee getEmployeeByName(String name);

  // 他のクエリメソッド
}
