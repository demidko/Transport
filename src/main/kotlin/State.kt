data class State(val left: Place, val right: Place = emptySet())

fun State.next() =
  (left.mapNotNull { it.move(left, right) } + right.mapNotNull { it.move(right, left) } + this)
    .toSet()

fun Set<State>.evolution(): Unit = when (val next = this.also(::println).map(State::next).flatten().toSet()) {
  this -> Unit
  else -> next.evolution()
}