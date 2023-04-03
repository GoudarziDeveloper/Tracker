package ir.tinyDeveloper.core.domain.use_case

class FilterOutDigits {
    operator fun invoke(text: String, length: Int = text.length): String {
        var counter = 1
        return text.filter { if (counter <= length) { counter++; it.isDigit() } else false }
    }
}