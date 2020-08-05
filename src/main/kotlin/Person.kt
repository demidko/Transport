sealed class Person(private val view: String) {
  override fun toString() = view
  open fun reaction(place: Place) = place
}

object Cabbage : Person("🥬")

object Farmer : Person("👨‍🌾")

object Wolf : Person("🐺") {
  override fun reaction(place: Place) = place.without(Goat)
}

object Goat : Person("🐐") {
  override fun reaction(place: Place) = place.without(Cabbage)
}

fun Person.move(from: Place, to: Place): State? {
  val group = setOf(this, Farmer)
  val left = from.minus(group).animate()
  val right = to.plus(group).animate()
  return State(left, right).takeIf { it.left.size + it.right.size == from.size + to.size }
}