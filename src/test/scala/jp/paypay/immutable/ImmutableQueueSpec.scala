package jp.paypay.immutable

import org.scalatest._

class ImmutableQueueSpec extends FlatSpec {

  "A Queue" should "not be empty as a result of enQueue operations" in {
    var q: Queue[String] = EmptyQueue[String]
    q = q.enQueue("A")
    q = q.enQueue("B")
    assertResult(false) (q.isEmpty)
  }

  "A Queue" should "become empty as a result of deQueue operations" in {
    var q: Queue[String] = EmptyQueue[String]
    q = q.enQueue("B")
    q = q.enQueue("C")
    q = q.deQueue()
    q = q.deQueue()
    assertResult(true) (q.isEmpty)
  }

  "A Queue" should "have value B on deQueue operation" in {
    var q: Queue[String] = EmptyQueue[String]
    q = q.enQueue("B")
    q = q.enQueue("C")
    assertResult("B") (q.head.get)
  }

}
