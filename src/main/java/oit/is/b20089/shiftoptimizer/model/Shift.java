package oit.is.b20089.shiftoptimizer.model;

import java.sql.Date;
//ゲッタセッタは自動生成可能
//このクラスはShiftRequestとOptimizedShiftの共通のスーパークラス
public class Shift {
  private Long shiftID;
  private Date shiftDate;
  private int shiftType;
  private Long employeeID;
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

  // getters and setters
}
