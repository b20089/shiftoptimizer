package oit.is.b20089.shiftoptimizer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;

import java.sql.Date;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap; // importが必要
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.fasterxml.jackson.databind.ObjectMapper;

import oit.is.b20089.shiftoptimizer.model.Employee;
import oit.is.b20089.shiftoptimizer.model.EmployeeMapper;
import oit.is.b20089.shiftoptimizer.model.ShiftRequest;
import oit.is.b20089.shiftoptimizer.model.ShiftRequestMapper;
import oit.is.b20089.shiftoptimizer.model.OptimizedShift;
import oit.is.b20089.shiftoptimizer.model.OptimizedShiftMapper;
import oit.is.b20089.shiftoptimizer.model.Shift;
import oit.is.b20089.shiftoptimizer.model.ShiftAddRequest;
import oit.is.b20089.shiftoptimizer.model.ShiftDeleteRequest;
import oit.is.b20089.shiftoptimizer.service.*;

//@RequestMapping("/shifts")
@Controller
public class ShiftController {
  @Autowired
  private ShiftRequestService shiftRequestService;
  @Autowired
  private OptimizedShiftService optimizedShiftService;

  private final SseController sseController;

  public ShiftController(SseController sseController) {
    this.sseController = sseController;
  }

  @Autowired
  ShiftRequestMapper shiftRequestMapper;
  @Autowired
  OptimizedShiftMapper optimizedShiftMapper;
  @Autowired
  EmployeeService employeeService;

  private Map<Long, String> getEmployeeNames(List<? extends Shift> shifts) {
    Map<Long, String> employeeNames = new HashMap<>();
    for (Shift shift : shifts) {
      Long employeeID = shift.getEmployeeID();
      String employeeName = employeeService.getEmployeeNameByID(employeeID);
      employeeNames.put(employeeID, employeeName);
    }
    return employeeNames;
  }

  @GetMapping("/list")
  public String viewEmployeeList(ModelMap model) {
    List<Employee> employees = employeeService.getAllEmployees();
    model.addAttribute("employees", employees);
    return "employee.html";
  }

  @GetMapping("/shifts")
  public String viewShifts(ModelMap model) {
    // ユーザ管理DB追加予定

    return "shifts.html";
  }

  @GetMapping("/requests")
  public String viewShiftRequests(ModelMap model) {
    List<ShiftRequest> shiftRequests = shiftRequestService.getAllShiftRequests();
    Map<Long, String> employeeNames = getEmployeeNames(shiftRequests);
    model.addAttribute("shiftRequests", shiftRequests);
    model.addAttribute("employeeNames", employeeNames);
    return "shift_requests.html"; // Viewの名前
  }

  @GetMapping("/optimized")
  public String viewOptimizedShifts(ModelMap model) {
    // 変数宣言
    List<Employee> employees = employeeService.getAllEmployees();//
    List<OptimizedShift> optimizedShifts = optimizedShiftService.getAllOptimizedShifts();

    // List<String> workingHours =
    // List<List<String>> names = new ArrayList<>();
    // Map<Long, String> employeeNames = getEmployeeNames(optimizedShifts);
    // 日付初期値設定
    List<java.sql.Date> shiftDate = new ArrayList<>(); // = java.sql.Date.valueOf("2023-09-01");
    // 日付操作のためのカレンダークラス作成
    Calendar c = Calendar.getInstance();// カレンダーインスタンス
    java.sql.Date d = java.sql.Date.valueOf("2023-09-01");// 開始日設定

    // 終了日
    Calendar targetCalendar = Calendar.getInstance();
    targetCalendar.setTime(Date.valueOf("2023-09-30"));

    // hh:mm:ss[.nnnnnnnnn] からhh:mmに変更したい
    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

    c.setTime(d);// カレンダーに開始日を設定
    shiftDate.add(new java.sql.Date(c.getTimeInMillis()));// 日付リストに格納
    // 日付を1ずつ増やし、その日に働く従業員を抽出
    while (!(c.compareTo(targetCalendar) == 0)) {// 終了日とカレンダーを比較
      // names.add(employeeService.getNameByDate(d));//使ってない
      c.add(Calendar.DATE, 1);// カレンダーを一日進める
      shiftDate.add(new java.sql.Date(c.getTimeInMillis())); // 日付を更新
    }

    // HTMLで整理するのではなく，ここで整理して新たなListに格納使用としたやつ
    List<List<OptimizedShift>> employeeShifts = new ArrayList<>();// 従業員ごとにOptimizedShiftを抽出するためのリストのリスト
    for (Employee employee : employees) {// 従業員毎に以下を繰り返す
      List<OptimizedShift> employeeShiftData = new ArrayList<>();// 従業員一人分(シフト表における一行分)のシフト
      for (java.sql.Date date : shiftDate) {// 日付毎（30）に以下を繰り返す
        List<OptimizedShift> shiftsForDate = optimizedShifts.stream()
            .filter(shift -> shift.getShiftDate().equals(date) && shift.getEmployeeID() == employee.getEmployeeID())
            .collect(Collectors.toList());
        employeeShiftData.addAll(shiftsForDate);
      }
      employeeShifts.add(employeeShiftData);
    }

    // model.addAttribute("employeeShifts", employeeShifts);

    // ViewでJavaScriptを使用するためここでデータを整理（OptimizedShiftのEmployeeID＝＝EmployeesのEmployeeID）
    List<List<String>> tableData = new ArrayList<>();// 従業員ごとにその従業員がシフトに入っている日付
    for (Employee employee : employees) { // 従業員毎に見ながら以下を繰り返す
      List<String> rowData = new ArrayList<>();// 各従業員がそれぞれ持つリスト（表における一行を表わす）
      rowData.add(employee.getName());// 各行の一列目は従業員名を格納する
      for (java.sql.Date date : shiftDate) {// カレンダーで作成した日付分，以下を繰り返す(9/30日までなら30回になる)
        List<OptimizedShift> shiftsForDate = optimizedShifts.stream()
            .filter(shift -> shift.getShiftDate().equals(date) && shift.getEmployeeID() == employee.getEmployeeID())
            .collect(Collectors.toList());
        // 各日付に対して、その日に働く従業員のシフト情報をフィルタリングし、shiftsForDateリストに格納する．
        // つまり，今見ている従業員がこの日付にどのくらい働くかという対比表リストを作成しておくということ，（一人の従業員が一日に二種類のシフトがある可能性も考慮できる．）
        if (!shiftsForDate.isEmpty()) {
          StringBuilder workingHours = new StringBuilder();
          for (OptimizedShift shift : shiftsForDate) {
            workingHours.append(dateFormat.format(shift.getStartTime())).append(" - ")
                .append(dateFormat.format(shift.getEndTime())).append(" ");
          }
          // 関連づけた対比表において，それが空じゃない（つまりその日付に今見ている従業員のシフトが一つ以上存在している）なら
          // workingHours，勤務時間オブジェクトを作成して，その日付の勤務時間を保持させる．
          rowData.add(workingHours.toString());// 今見ている行に勤務時間を追加する．
        } else {// 対比表が一切ないなら
          rowData.add(" ");// 今見ている行に空白を追加する
        }
      }
      tableData.add(rowData);// 従業員一人分の行をテーブルに追加する．
    }

    // var tableData = /*[[${tableData}]]*/[];
    // var shiftDates = /*[[${shiftdate}]]*/[];
    // var employees = /*[[${employees}]]*/[];
    model.addAttribute("employees", employees);// 従業員テーブルをそのままViewへ送る
    model.addAttribute("tableData", tableData);// 行：従業員の名前毎，内容，その従業員のシフト日付リスト
    model.addAttribute("shiftdate", shiftDate);// カレンダークラスで作成したひと月ぶんの日付リスト
    List<Date> dates = optimizedShiftService.getUniqueDates();
    model.addAttribute("dates", dates);
    // model.addAttribute("optimizedShifts", optimizedShifts);
    // model.addAttribute("employeeNames", employeeNames);

    // model.addAttribute("names", names);

    return "optimized_shifts.html"; // Viewの名前
  }

  // SSEでクライアントからのイベントを受信するエンドポイント
  @PostMapping("/shifts/update")
  public ResponseEntity<String> handleShiftUpdate(@RequestBody String eventData) {
    // eventDataにはクライアントから送られたJSONデータが含まれています
    try {
      // JSONデータをパースして必要な情報を取得
      ObjectMapper objectMapper = new ObjectMapper();
      ShiftUpdateData shiftUpdateData = objectMapper.readValue(eventData, ShiftUpdateData.class);

      // 受信したデータを使ってデータベースを更新
      optimizedShiftService.updateShift(shiftUpdateData);
      System.out.println(shiftUpdateData.getStartTime());
      System.out.println(shiftUpdateData.getEndTime());
      System.out.println(shiftUpdateData.getEmployeeID());
      //  データ更新後SSEを使用してクライアントに通知
      sseController.sendUpdateEvent(eventData);
      // 更新が成功したことをクライアントに通知
      return ResponseEntity.ok("Shift updated successfully");
    } catch (Exception e) {
      // 更新が失敗したことをクライアントに通知
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update shift");
    }

  }

  @PostMapping("/shifts/add")
  public ResponseEntity<String> addShift(@RequestBody ShiftAddRequest shiftAddRequest) {
    // shiftAddRequestにはJSONボディがデシリアライズされたデータが格納されています

    // shiftAddRequestから必要な情報を取り出し、データベースにシフトを追加する処理を行います
    System.out.println("Received shift addition request: " + shiftAddRequest.toString());
    optimizedShiftService.insertOptimizedShift(shiftAddRequest);
    System.out.println(shiftAddRequest.getEmployeeID());
    System.out.println(shiftAddRequest.getStartTime());
    System.out.println(shiftAddRequest.getEndTime());
    System.out.println(shiftAddRequest.getShiftDate());
    logger.debug("Received shift addition request: {}", shiftAddRequest.toString());
    logger.debug("Employee ID: {}", shiftAddRequest.getEmployeeID());
    logger.debug("Start Time: {}", shiftAddRequest.getStartTime());
    logger.debug("End Time: {}", shiftAddRequest.getEndTime());
    logger.debug("Shift Date: {}", shiftAddRequest.getShiftDate());

    // 成功した場合はHTTPステータス200 OKを返す
    return ResponseEntity.ok("Shift added successfully");
  }

  @PostMapping("/shifts/delete")
  public ResponseEntity<String> deleteShift(@RequestBody ShiftDeleteRequest shiftdeleteRequest) {
    System.out.println(shiftdeleteRequest.getEmployeeID());
    System.out.println(shiftdeleteRequest.getDate());
    logger.debug("Employee ID: {}", shiftdeleteRequest.getEmployeeID());
    logger.debug("Shift Date: {}", shiftdeleteRequest.getDate());
    optimizedShiftService.deleteOptimizedShift(shiftdeleteRequest);
    // 成功した場合はHTTPステータス200 OKを返す
    return ResponseEntity.ok("Shift delete successfully");
  }

  private final Logger logger = LoggerFactory.getLogger(ShiftController.class);

  // 非同期処理記述

  // private Date StringToDate(String string) {

  // return (Date) string;
  // }

  // 戻る
  @GetMapping("/back")
  public String back(/* @RequestParam Integer userid, ModelMap model */) {

    /*
     * ユーザをDBで管理する際にいじる部分の一つ
     *
     * Users user1 = new Users();
     *
     * user1 = userMapper.selectById(userid);
     * userMapper.updateZeroById(user1, 0);
     *
     * model.addAttribute("user1", user1);
     */

    return "shifts.html";
  }

}
