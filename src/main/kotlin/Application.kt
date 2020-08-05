typealias Place = Set<Person>

private fun Place.animate(person: Iterator<Person> = iterator()): Place = when {
  person.hasNext() -> person.next().reaction(this).animate(person)
  else -> this
}

data class State(val left: Place, val right: Place = emptySet())

fun move(person: Person, from: Place, to: Place): State? {
  val group = setOf(person, Farmer)
  val left = from.minus(group).animate()
  val right = to.plus(group).animate()
  return State(left, right).takeIf { it.left.size + it.right.size == from.size + to.size }
}

fun State.next() =
  (left.mapNotNull { move(it, left, right) } + right.mapNotNull { move(it, right, left) } + this).toSet()

fun Set<State>.evolution(): Unit =
  when (val next = this.also(::println).map { it.next() }.flatten().toSet()) {
    this -> Unit
    else -> next.evolution()
  }

fun main() = setOf(State(setOf(Farmer, Wolf, Goat, Cabbage))).evolution()
