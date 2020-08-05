typealias Place = Set<Person>

fun Place.animate(person: Iterator<Person> = iterator()): Place = when {
  person.hasNext() -> person.next().reaction(this).animate(person)
  else -> this
}

fun Place.without(food: Person) = when (Farmer) {
  in this -> this
  else -> this - food
}