package oit.is.b20089.shiftoptimizer.model;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LogMapper {

  @Insert("INSERT INTO logs (timestamp, message) VALUES (#{timestamp}, #{message})")
  void insertLog(Log log);

  @Select("SELECT * FROM logs")
  List<Log> getAllLogs();
}
