package com.baeldung.springsecuredsockets.transfer.socket;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OutputMessage extends Message {

  private String time;

  public OutputMessage(final String from, final String text, final String time) {
    setFrom(from);
    setText(text);
    this.time = time;
  }
}
