package ir.tinyDeveloper.core.domain.use_case

class FilterOutDigitsAndDot {
    operator fun invoke(text: String): String {
        var dot = true
        return text.filter { if (it == '.') { dot = !dot }; it.isDigit() || (it == '.' && !dot) }
    }
}