package oit.is.b20089.shiftoptimizer.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;

@Mapper
public interface OptimizedShiftMapper {
  @Select("SELECT * FROM OptimizedShift")
  List<OptimizedShift> getAllOptimizedShifts();
}
