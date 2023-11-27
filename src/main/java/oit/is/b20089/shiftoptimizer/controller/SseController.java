package oit.is.b20089.shiftoptimizer.controller;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@Controller
public class SseController {

  private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

  @GetMapping("/sse")
  public String getSsePage(Model model) {
    return "optimized_shifts.html"; // sse.htmlという名前のThymeleafテンプレートを返す
  }

  @GetMapping("/sse-endpoint")
  public SseEmitter handleSse() {
    SseEmitter emitter = new SseEmitter();
    emitters.add(emitter);
    emitter.onCompletion(() -> emitters.remove(emitter));
    return emitter;
  }

  public void sendUpdateEvent(Object eventData) {
    System.out.println("うんこ");
    emitters.forEach(emitter -> {
      try {
        emitter.send(SseEmitter.event().name("update").data(eventData));
      } catch (IOException e) {
        emitter.complete();
        emitters.remove(emitter);
      }
    });
  }
}
