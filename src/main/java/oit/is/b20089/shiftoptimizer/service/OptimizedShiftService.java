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
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import oit.is.b20089.shiftoptimizer.controller.ShiftUpdateData;
import oit.is.b20089.shiftoptimizer.model.Employee;
import oit.is.b20089.shiftoptimizer.model.EmployeeMapper;
import oit.is.b20089.shiftoptimizer.model.ShiftRequest;
import oit.is.b20089.shiftoptimizer.model.ShiftRequestMapper;
import oit.is.b20089.shiftoptimizer.model.OptimizedShift;
import oit.is.b20089.shiftoptimizer.model.OptimizedShiftMapper;
import oit.is.b20089.shiftoptimizer.model.ShiftAddRequest;
import oit.is.b20089.shiftoptimizer.model.ShiftDeleteRequest;

@Service
public class OptimizedShiftService {
  @Autowired
  private OptimizedShiftMapper optimizedShiftMapper;

  public List<OptimizedShift> getAllOptimizedShifts() {
    return optimizedShiftMapper.getAllOptimizedShifts();
  }

  public List<Date> getUniqueDates() {
    return optimizedShiftMapper.getUniqueDates();
  }

  public List<OptimizedShift> getShiftByEmployeeID(int employeeID) {
    return optimizedShiftMapper.getShiftByEmployeeID(employeeID);
  }

  public void updateShift(ShiftUpdateData shiftUpdateData) {
    System.out.println("\nservice\n");
    System.out.println(shiftUpdateData.getStartTime());
    System.out.println(shiftUpdateData.getEndTime());
    System.out.println(shiftUpdateData.getEmployeeID());

    // SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
    // java.util.Date utilDate = s.parse(shiftUpdateData.getShiftDate());
    // java.sql.Date shiftDate = new Date(utilDate.getTime());
    optimizedShiftMapper.updateByemployeeId(shiftUpdateData);
  }

  public void insertOptimizedShift(ShiftAddRequest shiftAddRequest) {

    optimizedShiftMapper.insertOptimizedShift(shiftAddRequest);
  }

  public void deleteOptimizedShift(ShiftDeleteRequest shiftDeleteRequest) {

    optimizedShiftMapper.deleteOptimizedShift(shiftDeleteRequest);
  }
}
