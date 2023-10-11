package oit.is.b20089.shiftoptimizer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;

import java.io.IOException;
import java.util.List;
import java.util.HashMap; // importが必要
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import oit.is.b20089.shiftoptimizer.model.Employee;
import oit.is.b20089.shiftoptimizer.model.EmployeeMapper;
import oit.is.b20089.shiftoptimizer.model.ShiftRequest;
import oit.is.b20089.shiftoptimizer.model.ShiftRequestMapper;
import oit.is.b20089.shiftoptimizer.model.OptimizedShift;
import oit.is.b20089.shiftoptimizer.model.OptimizedShiftMapper;
import oit.is.b20089.shiftoptimizer.model.Shift;
import oit.is.b20089.shiftoptimizer.service.*;

//@RequestMapping("/shifts")
@Controller
public class ShiftController {
  @Autowired
  private ShiftRequestService shiftRequestService;
  @Autowired
  private OptimizedShiftService optimizedShiftService;

  @Autowired
  ShiftRequestMapper shiftRequestMapper;
  @Autowired
  OptimizedShiftMapper optimizedShiftMapper;
  @Autowired
  EmployeeService employeeService;

  private Map<Long, String> getEmployeeNames(List<? extends Shift> shifts) {
    Map<Long, String> employeeNames = new HashMap<>();
    for (Shift shift : shifts) {
      Long employeeID = shift.getEmployeeID();
      String employeeName = employeeService.getEmployeeNameByID(employeeID);
      employeeNames.put(employeeID, employeeName);
    }
    return employeeNames;
  }

  @GetMapping("/list")
  public String viewEmployeeList(ModelMap model) {
    List<Employee> employees = employeeService.getAllEmployees();
    model.addAttribute("employees", employees);
    return "employee.html";
  }

  @GetMapping("/shifts")
  public String viewShifts(ModelMap model) {
    // ユーザ管理DB追加予定

    return "shifts.html";
  }

  @GetMapping("/requests")
  public String viewShiftRequests(ModelMap model) {
    List<ShiftRequest> shiftRequests = shiftRequestService.getAllShiftRequests();
    Map<Long, String> employeeNames = getEmployeeNames(shiftRequests);
    model.addAttribute("shiftRequests", shiftRequests);
    model.addAttribute("employeeNames", employeeNames);
    return "shift_requests.html"; // Viewの名前
  }

  @GetMapping("/optimized")
  public String viewOptimizedShifts(ModelMap model) {
    List<OptimizedShift> optimizedShifts = optimizedShiftService.getAllOptimizedShifts();
    Map<Long, String> employeeNames = getEmployeeNames(optimizedShifts);
    model.addAttribute("optimizedShifts", optimizedShifts);
    model.addAttribute("employeeNames", employeeNames);
    return "optimized_shifts.html"; // Viewの名前
  }


  //private final Logger logger = LoggerFactory.getLogger(ShiftController.class);

  // 非同期処理記述

  // 戻る
  @GetMapping("/back")
  public String back(/* @RequestParam Integer userid, ModelMap model */) {

    /*
     * ユーザをDBで管理する際にいじる部分の一つ
     *
     * Users user1 = new Users();
     *
     * user1 = userMapper.selectById(userid);
     * userMapper.updateZeroById(user1, 0);
     *
     * model.addAttribute("user1", user1);
     */

    return "shifts.html";
  }

}
