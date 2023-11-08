package oit.is.b20089.shiftoptimizer.model;

import java.sql.*;

public class ShiftRequest extends Shift{
  private Long requestID;
  private Long employeeID;
  private Date shiftDate;
  private int shiftType;
  private Time startTime;
  private Time endTime;

  private String otherRequestDetails;
  // Getters and setters

  public Long getRequestID() {
    return requestID;
  }

  public void setRequestID(Long requestID) {
    this.requestID = requestID;
  }

  public Long getEmployeeID() {
    return employeeID;
  }

  public void setEmployeeID(Long employeeID) {
    this.employeeID = employeeID;
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

  public String getOtherRequestDetails() {
    return otherRequestDetails;
  }

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

  public void setOtherRequestDetails(String otherRequestDetails) {
    this.otherRequestDetails = otherRequestDetails;
  }

}
