package rip.hippo.lwjeb.testing.standard;

import org.junit.jupiter.api.Test;
import rip.hippo.lwjeb.annotation.Handler;
import rip.hippo.lwjeb.annotation.Priority;
import rip.hippo.lwjeb.bus.PubSub;

/**
 * @author Hippo
 * @version 1.0.0, 7/30/21
 * @since 5.3.0
 */
public final class PriorityTest {

  @Test
  public void test() {
    PubSub<String> pubSub = new PubSub<>();
    pubSub.subscribe(this);
    pubSub.post("Message").dispatch();
  }

  @Handler
  @Priority(3)
  public void onMessage3(String topic) {
    System.out.println("3 " + topic);
  }
  @Handler
  @Priority(2)
  public void onMessage2(String topic) {
    System.out.println("2 " + topic);
  }
  @Handler
  @Priority(1)
  public void onMessage1(String topic) {
    System.out.println("1 " + topic);
  }

}
