package jp.paypay.immutable

trait Stack[E] {
  /**
    * Checks whether stack is empty
    * @return is empty
    */
  def isEmpty: Boolean
  /**
    * Push operation will construct a new stack with the latest element as head and oldest elements as tail.
    * @param e Element to be pushed
    * @return Immutable stack
    */
  def push(e: E): Stack[E]
  /**
    * Pop operation will return stack of oldest elements pushed into the tail.
    * @return Stack of oldest elements
    * @throws Exception
    */
  def pop(): Stack[E]
  /**
    * Head operation will peek the latest element pushed into the stack without removing it.
    * @return Latest element
    */
  def head(): Option[E]
}
