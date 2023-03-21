package ir.tinyDeveloper.core.domain.model

sealed class Gender(val name: String){
    object Male: Gender(name = "male")
    object Female: Gender(name = "female")

    companion object {
        fun fromString(name: String?): Gender{
            return when(name){
                "mail" -> Male
                "female" -> Female
                else -> Male
            }
        }
    }
}
