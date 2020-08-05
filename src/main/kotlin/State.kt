data class State(val left: Place, val right: Place = emptySet())

fun State.next() = left.mapNotNull { it.move(left, right) } + this.right.mapNotNull { it.move(right, left) } + this

fun Set<State>.evolution(): Unit = when (val next = also(::println).map(State::next).flatten().toSet()) {
  this -> Unit
  else -> next.evolution()
}
