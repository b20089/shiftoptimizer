package oit.is.b20089.shiftoptimizer.model;

public class Employee {
  private Long employeeID;
  private String name;
  private int skillLevel;
  private double salary;


  // Getters and setters

  public Long getEmployeeID() {
    return employeeID;
  }

  public void setEmployeeID(Long employeeID) {
    this.employeeID = employeeID;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getSkillLevel() {
    return skillLevel;
  }

  public void setSkillLevel(int skillLevel) {
    this.skillLevel = skillLevel;
  }

  public double getSalary() {
    return salary;
  }

  public void setSalary(double salary) {
    this.salary = salary;
  }
}
