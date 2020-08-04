import Person.Farmer

typealias Place = Set<Person>

fun placeOf(vararg persons: Person) = persons.toSet().animate()

fun Place.animate(person: Iterator<Person> = iterator()): Place = when {
  person.hasNext() -> person.next().reaction(this).animate(person)
  else -> this
}

fun Place.without(person: Person) = when {
  contains(Farmer) -> this
  else -> this - person
}