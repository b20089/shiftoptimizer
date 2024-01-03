package oit.is.b20089.shiftoptimizer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import oit.is.b20089.shiftoptimizer.model.Log;
import oit.is.b20089.shiftoptimizer.model.LogMapper;

import java.util.List;

@Service
public class LogService {

  @Autowired
  private final LogMapper logMapper;

  @Autowired
  public LogService(LogMapper logMapper) {
    this.logMapper = logMapper;
  }

  @Transactional
  public void insertLog(Log log) {
    logMapper.insertLog(log);
  }

  public List<Log> getAllLogs() {
    return logMapper.getAllLogs();
  }
}
