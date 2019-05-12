package jp.paypay.immutable

import org.scalatest.FlatSpec

class ImmutableStackSpec extends FlatSpec {

  "A stack" should "not be empty as a result of push operations" in {
    var s: Stack[String] = EmptyStack[String]
    s = s.push("A")
    s = s.push("B")
    assertResult(false)(s.isEmpty)
  }

  "A stack" should "be empty as a result of pop operations" in {
    var s: Stack[String] = EmptyStack[String]
    s = s.push("A")
    s = s.push("B")
    s = s.pop()
    s = s.pop()
    assertResult(true)(s.isEmpty)
  }

  "A stack" should "have value B on pop operation" in {
    var s: Stack[String] = EmptyStack[String]
    s = s.push("A")
    s = s.push("B")
    assertResult("B")(s.head().get)
  }
}
