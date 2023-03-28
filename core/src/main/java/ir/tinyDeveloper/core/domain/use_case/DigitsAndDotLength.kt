package ir.tinyDeveloper.core.domain.use_case

class DigitsAndDotLength {
    operator fun invoke(text: String): Int {
        var counter = 0
        for (item in text){
            counter++
            if (item == '.')
                return counter + 2
        }
        return 3
    }
}