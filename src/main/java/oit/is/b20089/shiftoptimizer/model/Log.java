package oit.is.b20089.shiftoptimizer.model;

import java.time.LocalDateTime;

public class Log {

  private Long id;
  private LocalDateTime timestamp;
  private String message;

  // コンストラクタ、ゲッター、セッターなど



  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "Log{" +
        "id=" + id +
        ", timestamp=" + timestamp +
        ", message='" + message + '\'' +
        '}';
  }
}
