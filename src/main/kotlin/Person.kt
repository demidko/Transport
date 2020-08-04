enum class Person(private val view: String) {

  Cabbage("🥬"),

  Farmer("👨‍🌾"),

  Wolf("🐺") {
    override fun reaction(place: Place) = place.without(Goat)
  },

  Goat("🐐") {
    override fun reaction(place: Place) = place.without(Cabbage)
  };

  open fun reaction(place: Place) = place

  override fun toString() = view
}
