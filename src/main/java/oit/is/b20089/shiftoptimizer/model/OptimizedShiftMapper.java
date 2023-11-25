package oit.is.b20089.shiftoptimizer.model;

import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import oit.is.b20089.shiftoptimizer.controller.ShiftUpdateData;

import org.apache.ibatis.annotations.Delete;

@Mapper
public interface OptimizedShiftMapper {
  @Select("SELECT * FROM OptimizedShift")
  List<OptimizedShift> getAllOptimizedShifts();

  @Select("SELECT DISTINCT shiftDate FROM OptimizedShift order by shiftDate")
  List<Date> getUniqueDates();

  @Select("SELECT * FROM OPTIMIZEDSHIFT where employeeid = #{employeeID} order by shiftdate")
  List<OptimizedShift> getShiftByEmployeeID(int employeeID);

  @Update("UPDATE OPTIMIZEDSHIFT SET STARTTIME=#{startTime}, ENDTIME=#{endTime} WHERE EMPLOYEEID = #{employeeID} AND SHIFTDATE = #{date}")
  void updateByemployeeId(ShiftUpdateData shift);

  @Insert("INSERT INTO OPTIMIZEDSHIFT (shiftDate, employeeID, startTime, endTime) VALUES (#{shiftDate}, #{employeeID}, #{startTime}, #{endTime});")
  @Options(useGeneratedKeys = true, keyColumn = "shiftID", keyProperty = "shiftID")
  void insertOptimizedShift(ShiftAddRequest shift);

  @Delete("DELETE FROM OPTIMIZEDSHIFT WHERE EMPLOYEEID = #{employeeID} AND SHIFTDATE = #{date}")
  void deleteOptimizedShift(ShiftDeleteRequest shift);
}

// private Long shiftID;
// private Date shiftDate;
// private int shiftType;
// private Long employeeID;
// // private String workingHours;
// private Time startTime; // 開始時間を表すフィールド
// private Time endTime;
// // 終了時間を表すフィールド

// private String employeeName;
// private String employeeID;
// private String shiftDate;
// private String startTime;
// private String endTime;

// UPDATE OPTIMIZEDSHIFT SET STARTTIME='18:30', ENDTIME='23:30' WHERE EMPLOYEEID
// = 1 AND SHIFTDATE = '2023-09-03';
