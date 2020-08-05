typealias Place = Set<Person>

private fun Place.animate(person: Iterator<Person> = iterator()): Place = when {
  person.hasNext() -> person.next().reaction(this).animate(person)
  else -> this
}

data class State(val left: Place, val right: Place) {

  constructor(vararg persons: Person) : this(persons.toSet().animate(), setOf())

  private fun toRight(person: Person) = move(person, left, right)

  private fun toLeft(person: Person) = move(person, right, left)

  private fun move(person: Person, from: Place, to: Place): State? {
    val group = setOf(person, Farmer)
    val left = from.minus(group).animate()
    val right = to.plus(group).animate()
    return State(left, right).takeIf { it.left.size + it.right.size == from.size + to.size }
  }

  fun next() = (left.mapNotNull { toRight(it) } + right.mapNotNull { toLeft(it) } + this).toSet()
}

fun evolution(curr: Set<State> = setOf(State(Farmer, Wolf, Goat, Cabbage))): Set<State> {
  println(curr)
  return when (val next = curr.map { it.next() }.flatten().toSet()) {
    curr -> curr
    else -> evolution(next)
  }
}

fun main() {
  evolution()
}