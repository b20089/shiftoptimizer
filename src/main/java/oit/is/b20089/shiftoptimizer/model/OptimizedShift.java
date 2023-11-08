package oit.is.b20089.shiftoptimizer.model;

import java.sql.*;
//import oit.is.b20089.shiftoptimizer.model.Shift;

public class OptimizedShift extends Shift {
  private Long shiftID;
  private Date shiftDate;
  private int shiftType;
  private Long employeeID;
  //private String workingHours;
  private Time startTime; // 開始時間を表すフィールド
  private Time endTime; // 終了時間を表すフィールド
  // Getters and setters

  public Long getShiftID() {
    return shiftID;
  }

  public void setShiftID(Long shiftID) {
    this.shiftID = shiftID;
  }

  public Date getShiftDate() {
    return shiftDate;
  }

  public void setShiftDate(Date shiftDate) {
    this.shiftDate = shiftDate;
  }

  public int getShiftType() {
    return shiftType;
  }

  public void setShiftType(int shiftType) {
    this.shiftType = shiftType;
  }

  public Long getEmployeeID() {
    return employeeID;
  }

  public void setEmployeeID(Long employeeID) {
    this.employeeID = employeeID;
  }

  // public String getWorkingHours() {
  //   return workingHours;
  // }

  // public void setWorkingHours(String workingHours) {
  //   this.workingHours = workingHours;
  // }

  public Time getStartTime() {
    return startTime;
  }

  public void setStartTime(Time startTime) {
    this.startTime = startTime;
  }

  public Time getEndTime() {
    return endTime;
  }

  public void setEndTime(Time endTime) {
    this.endTime = endTime;
  }
}
