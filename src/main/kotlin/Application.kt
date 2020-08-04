enum class Persons(val view: String) {
    Wolf("🐺"),
    Goat("🐐"),
    Cabbage("🥬")
}

fun main() {

    Persons.Wolf.view.al(::println)
}