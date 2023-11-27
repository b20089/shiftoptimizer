package oit.is.b20089.shiftoptimizer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import oit.is.b20089.shiftoptimizer.model.OptimizedShift;
import oit.is.b20089.shiftoptimizer.service.OptimizedShiftService;

@RestController
@RequestMapping("/api")
public class ShiftRestController {

  @Autowired
  private OptimizedShiftService optimizedShiftService;

  @GetMapping("/shifts")
  public ResponseEntity<List<OptimizedShift>> getShifts() {
    List<OptimizedShift> shifts = optimizedShiftService.getAllOptimizedShifts();
    return new ResponseEntity<>(shifts, HttpStatus.OK);
  }
}
