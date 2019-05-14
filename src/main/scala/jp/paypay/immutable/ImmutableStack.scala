package jp.paypay.immutable

case class ImmutableStack[E](h: E, tail: Stack[E]) extends Stack[E] {

  override def isEmpty: Boolean = {
    false
  }

  /**
    * Push operation is performed on the copy of the Immutable Stack.
    * @param e Element to be pushed
    * @return Immutable stack
    */
  override def push(e: E): Stack[E] = {
    this.copy(e, this)
  }

  override def pop: Stack[E] = {
    tail
  }

  override def head(): Option[E] = {
    Some(h)
  }

  def canEqual(a: Any) = a.isInstanceOf[ImmutableStack[E]]

  override def equals(that: Any): Boolean =
    that match {
      case that: ImmutableStack[E] => that.canEqual(this) && this.head == that.head && this.tail == that.tail
      case _ => false
    }
}

/**
  * Immutable instance of empty stack
  * @param E The element to be pushed and popped from the stack
  */
case class EmptyStack[E]() extends Stack[E] {

  override def isEmpty: Boolean = {
    true
  }

  override def push(e: E): Stack[E] = {
    ImmutableStack(e, EmptyStack[E])
  }

  override def pop(): Stack[E] = {
    throw new NoSuchElementException("Stack is empty")
  }

  override def head(): Option[E] = {
    throw new NoSuchElementException("Stack is empty")
  }
}
