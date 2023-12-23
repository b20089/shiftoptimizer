package oit.is.b20089.shiftoptimizer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import oit.is.b20089.shiftoptimizer.model.Log;
import oit.is.b20089.shiftoptimizer.service.LogService;

import java.util.List;

@Controller
public class LogController {

  private final LogService logService;

  @Autowired
  public LogController(LogService logService) {
    this.logService = logService;
  }

  @GetMapping("/logs")
  public String showLogs(Model model) {
    List<Log> logs = logService.getAllLogs();
    model.addAttribute("logs", logs);
    return "logs"; // ログ表示用のHTMLテンプレート名を返す
  }

  @PostMapping("/logs/add")
  public String addLog(Log log) {
    logService.insertLog(log);
    return "redirect:/logs"; // ログ一覧画面にリダイレクト
  }
}
