package oit.is.b20089.shiftoptimizer.service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import oit.is.b20089.shiftoptimizer.model.Employee;
import oit.is.b20089.shiftoptimizer.model.EmployeeMapper;
import oit.is.b20089.shiftoptimizer.model.ShiftRequest;
import oit.is.b20089.shiftoptimizer.model.ShiftRequestMapper;
import oit.is.b20089.shiftoptimizer.model.OptimizedShift;
import oit.is.b20089.shiftoptimizer.model.OptimizedShiftMapper;

@Service
public class EmployeeService {
  @Autowired
  private EmployeeMapper employeeMapper;

  public List<Employee> getAllEmployees() {
    return employeeMapper.getAllEmployees();
  }

  public String getEmployeeNameByID(Long employeeID) {
    Employee employee = employeeMapper.getEmployeeById(employeeID);
    return (employee != null) ? employee.getName() : "Unknown"; // 名前がない場合のデフォルト値
  }
}
