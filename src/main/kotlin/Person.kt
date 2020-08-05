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

private fun Place.without(food: Person) = when (Farmer) {
  in this -> this
  else -> this - food
}
