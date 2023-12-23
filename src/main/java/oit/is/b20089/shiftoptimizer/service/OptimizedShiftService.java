package oit.is.b20089.shiftoptimizer.service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
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

  boolean dbUpdated = false;
  List<String> log = new ArrayList<>();
  private final Logger logger = LoggerFactory.getLogger(OptimizedShiftService.class);

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
    System.out.println("\nupdating..........\n");
    System.out.println(shiftUpdateData.getStartTime());
    System.out.println(shiftUpdateData.getEndTime());
    System.out.println(shiftUpdateData.getEmployeeID());

    // SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
    // java.util.Date utilDate = s.parse(shiftUpdateData.getShiftDate());
    // java.sql.Date shiftDate = new Date(utilDate.getTime());

    optimizedShiftMapper.updateByemployeeId(shiftUpdateData);
    // 非同期でDB更新したことを共有する際に利用する
    this.dbUpdated = true;

    log.add(shiftUpdateData.toString());
  }

  /**
   * dbUpdatedがtrueのときのみブラウザにDBからフルーツリストを取得して送付する
   *
   * @param emitter
   */
  @Async
  public void asyncUpdate(SseEmitter emitter) {
    dbUpdated = true;
    try {
      while (true) {// 無限ループ
        // DBが更新されていなければ0.5s休み
        if (false == dbUpdated) {
          TimeUnit.MILLISECONDS.sleep(500);
          continue;
        }
        // DBが更新されていれば更新後のフルーツリストを取得してsendし，1s休み，dbUpdatedをfalseにする

        emitter.send(1);
        TimeUnit.MILLISECONDS.sleep(1000);
        dbUpdated = false;
        System.out.println("asyncUpdate complete");
        // System.out.println(emitter);
      }
    } catch (Exception e) {
      // 例外の名前とメッセージだけ表示する
      // System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
      logger.warn("Exception:" + e.getClass().getName() + ":" + e.getMessage());
    } finally {
      emitter.complete();
      // System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
    }

  }

  /**
   *
   * @param emitter
   */
  @Async
  public void asyncLog(SseEmitter emitter) {
    try {
      while (true) {// 無限ループ
        // DBが更新されていなければ0.5s休み
        if (false == dbUpdated) {
          TimeUnit.MILLISECONDS.sleep(500);
          continue;
        }
        // DBが更新されていれば更新後のフルーツリストを取得してsendし，1s休み，dbUpdatedをfalseにする

        emitter.send(log);
        TimeUnit.MILLISECONDS.sleep(1000);
        System.out.println("asyncLog complete");
        // System.out.println(emitter);
      }
    } catch (Exception e) {
      // 例外の名前とメッセージだけ表示する
      // System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
      logger.warn("Exception:" + e.getClass().getName() + ":" + e.getMessage());
    } finally {

      emitter.complete();
      // System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
    }
  }

  public void insertOptimizedShift(ShiftAddRequest shiftAddRequest) {
    System.out.println("\nupdating..........\n");
    // 非同期でDB更新したことを共有する際に利用する
    this.dbUpdated = true;
    optimizedShiftMapper.insertOptimizedShift(shiftAddRequest);
  }

  public void deleteOptimizedShift(ShiftDeleteRequest shiftDeleteRequest) {
    System.out.println("\nupdating..........\n");
    // 非同期でDB更新したことを共有する際に利用する
    this.dbUpdated = true;
    optimizedShiftMapper.deleteOptimizedShift(shiftDeleteRequest);
  }
}
