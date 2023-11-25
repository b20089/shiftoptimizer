package oit.is.b20089.shiftoptimizer.controller;

public class ShiftUpdateData {
  private Long employeeID;
  private String date;
  private String startTime;
  private String endTime;


  public Long getEmployeeID() {
    return employeeID;
  }

  public void setEmployeeID(Long employeeID) {
    this.employeeID = employeeID;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
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
