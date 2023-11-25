package oit.is.b20089.shiftoptimizer.model;

public class ShiftAddRequest {
  private Long shiftID;
  private String employeeName;
  private String employeeID;
  private String shiftDate;
  private String startTime;
  private String endTime;

  public Long getShiftID() {
    return shiftID;
  }

  public void setShiftID(Long shiftID) {
    this.shiftID = shiftID;
  }

  public String getEmployeeName() {
    return employeeName;
  }

  public void setEmployeeName(String employeeName) {
    this.employeeName = employeeName;
  }

  public String getEmployeeID() {
    return employeeID;
  }

  public void setEmployeeID(String employeeID) {
    this.employeeID = employeeID;
  }

  public String getShiftDate() {
    return shiftDate;
  }

  public void setShiftDate(String shiftDate) {
    this.shiftDate = shiftDate;
  }

  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }
}
