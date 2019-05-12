package jp.paypay.immutable

/**
  * The ImmutableQueue class represents a first-in-first-out (FIFO) queue of objects.
  * The ImmutableQueue is constructed using forwards and backwards stacks to keep track of the elements.
  * The usual enqueue and dequeue operations are provided, as well as a method to peek at the top item on the forwards stack, a method to test for whether the stack is empty.
  * An ImmutableQueue object is initially constructed using {@link EmptyQueue} which is a singleton instance since every empty queue is the same.
  * To operations performed on the ImmutableQueue will essentially create a new ImmutableQueue. The original ImmutableQueue will remain the same.
  *
  * @param forwards The backwards stack keeps track of the elements being enQueued (since a queue is like a reverse stack).
  * @param backwards The backwards stack keeps track of the elements being enQueued (since a queue is like a reverse stack)
  */
case class ImmutableQueue[E](forwards: Stack[E], backwards: Stack[E]) extends Queue[E] {

  override def isEmpty: Boolean = {
    false
  }

  /**
    * @see {@link Queue#enQueue}
    * enQueue operation is performed on the copy of the Immutable Queue.
    * @param e Element to be enqueued
    * @return The Queue instance
    */
  override def enQueue(e: E): Queue[E] = {
    this.copy(forwards, backwards.push(e))
  }

  /**
    * Reverse the stack.
    * The use case of this operation is when the forwards stack is empty
    * the backwards stack is reversed and pushed into the forwards stack.
    * @param stack The stack to be reversed
    * @return The reversed stack
    */
  def reverse(stack: Stack[E]): Stack[E] = {
      var r: Stack[E] = EmptyStack[E]
      var s: Stack[E] = stack

      while(!s.isEmpty) {
        r = r.push(s.head().get)
        s = s.pop()
      }
    r
  }

  /**
    * @see {@link Queue#deQueue}
    * deQueue operation is performed on the copy of the Immutable Queue.
    * @return The Queue instance
    */
  override def deQueue(): Queue[E] = {
    val f = forwards.pop()

    if (!f.isEmpty) {
      return this.copy(f,backwards)
    } else if (backwards.isEmpty) {
      return EmptyQueue[E]
    } else {
      return this.copy(reverse(backwards), EmptyStack[E])
    }
  }

  override def head: Option[E] = {
    forwards.head()
  }

  def canEqual(a: Any) = a.isInstanceOf[ImmutableQueue[E]]

  override def equals(that: Any): Boolean =
    that match {
      case that: ImmutableQueue[E] => that.canEqual(this) && this.head == that.head && this.forwards == that.forwards && this.backwards == that.backwards
      case _ => false
    }
}

/**
  * Immutable instance of empty Queue
  * @tparam E The element to be enQueued and deQueued
  */
case class EmptyQueue[E]() extends Queue[E] {

  override def isEmpty: Boolean = {
    true
  }

  override def enQueue(e: E): Queue[E] = {
    ImmutableQueue(EmptyStack[E].push(e),EmptyStack[E])
  }

  override def deQueue(): Queue[E] = {
    throw new NoSuchElementException("Queue is empty")
  }

  override def head: Option[E] = {
    throw new NoSuchElementException("Queue is empty")
  }
}

object QueueTest {

  def main(args: Array[String]): Unit = {
    var q: Queue[String] = EmptyQueue[String]
    q = q.enQueue("B")
    q = q.enQueue("C")
    traverse(q)
    q = q.deQueue()
    traverse(q)
  }

  def traverse(queue: Queue[String]): Unit = {
    var q = queue

    while (!q.isEmpty) {
      val h = q.head.get
      print(h + " -> ")
      q = q.deQueue()
    }
    println()
  }
}