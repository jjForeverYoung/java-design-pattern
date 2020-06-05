/*
 * The MIT License
 * Copyright © 2014-2019 Ilkka Seppälä
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.iluwatar.twin;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Date: 12/30/15 - 18:44 PM
 *
 * @author Jeroen Meulemeester
 */
public class BallItemTest {

  private InMemoryAppender appender;

  @BeforeEach
  public void setUp() {
    appender = new InMemoryAppender();
  }

  @AfterEach
  public void tearDown() {
    appender.stop();
  }

  @Test
  public void testClick() {
    final var ballThread = mock(BallThread.class);
    final var ballItem = new BallItem();
    ballItem.setTwin(ballThread);

    final var inOrder = inOrder(ballThread);

    IntStream.range(0, 10).forEach(i -> {
      ballItem.click();
      inOrder.verify(ballThread).suspendMe();
      ballItem.click();
      inOrder.verify(ballThread).resumeMe();
    });

    inOrder.verifyNoMoreInteractions();
  }

  @Test
  public void testDoDraw() {
    final var ballItem = new BallItem();
    final var ballThread = mock(BallThread.class);
    ballItem.setTwin(ballThread);

    ballItem.draw();
    assertTrue(appender.logContains("draw"));
    assertTrue(appender.logContains("doDraw"));

    verifyNoMoreInteractions(ballThread);
    assertEquals(2, appender.getLogSize());
  }

  @Test
  public void testMove() {
    final var ballItem = new BallItem();
    final var ballThread = mock(BallThread.class);
    ballItem.setTwin(ballThread);

    ballItem.move();
    assertTrue(appender.logContains("move"));

    verifyNoMoreInteractions(ballThread);
    assertEquals(1, appender.getLogSize());
  }

  /**
   * Logging Appender Implementation
   */
  public class InMemoryAppender extends AppenderBase<ILoggingEvent> {
    private List<ILoggingEvent> log = new LinkedList<>();

    public InMemoryAppender() {
      ((Logger) LoggerFactory.getLogger("root")).addAppender(this);
      start();
    }

    @Override
    protected void append(ILoggingEvent eventObject) {
      log.add(eventObject);
    }

    public boolean logContains(String message) {
      return log.stream().anyMatch(event -> event.getMessage().equals(message));
    }

    public int getLogSize() {
      return log.size();
    }
  }

}
