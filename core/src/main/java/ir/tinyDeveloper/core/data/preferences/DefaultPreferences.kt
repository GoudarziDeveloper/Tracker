package ir.tinyDeveloper.core.data.preferences

import android.content.SharedPreferences
import ir.tinyDeveloper.core.domain.model.ActivityLevel
import ir.tinyDeveloper.core.domain.model.Gender
import ir.tinyDeveloper.core.domain.model.GoalType
import ir.tinyDeveloper.core.domain.model.UserInfo
import ir.tinyDeveloper.core.domain.preferences.Preferences

class DefaultPreferences(
    private val sharedPref: SharedPreferences
): Preferences{
    override fun saveGender(gender: Gender) {
        sharedPref.edit().putString(Preferences.KEY_GENDER, gender.name).apply()
    }

    override fun saveAge(age: Int) {
        sharedPref.edit().putInt(Preferences.KEY_AGE, age).apply()
    }

    override fun saveWeight(weight: Float) {
        sharedPref.edit().putFloat(Preferences.KEY_WEIGHT, weight).apply()
    }

    override fun saveHeight(height: Int) {
        sharedPref.edit().putInt(Preferences.KEY_HEIGHT, height).apply()
    }

    override fun saveActivityLevel(activityLevel: ActivityLevel) {
        sharedPref.edit().putString(Preferences.KEY_ACTIVITY_LEVEL, activityLevel.name).apply()
    }

    override fun saveGoalType(goalType: GoalType) {
        sharedPref.edit().putString(Preferences.KEY_GOAL_TYPE, goalType.name).apply()
    }

    override fun saveCarbRatio(ratio: Float) {
        sharedPref.edit().putFloat(Preferences.KEY_CARB_RATIO, ratio).apply()
    }

    override fun saveProteinRatio(ratio: Float) {
        sharedPref.edit().putFloat(Preferences.KEY_PROTEIN_RATIO, ratio).apply()
    }

    override fun saveFatRatio(ratio: Float) {
        sharedPref.edit().putFloat(Preferences.KEY_FAT_RATIO, ratio).apply()
    }

    override fun saveShouldShowOnBoarding(shouldSHow: Boolean) {
        sharedPref.edit().putBoolean(Preferences.SHOULD_SHOW_ON_BOARDING, shouldSHow).apply()
    }

    override fun loadShouldShowOnBoarding(): Boolean {
        return sharedPref.getBoolean(Preferences.SHOULD_SHOW_ON_BOARDING, true)
    }

    override fun loadUserInfo(): UserInfo {
        val gender = sharedPref.getString(Preferences.KEY_GENDER, null)
        val age = sharedPref.getInt(Preferences.KEY_AGE, -1)
        val weight = sharedPref.getFloat(Preferences.KEY_WEIGHT, -1f)
        val height = sharedPref.getInt(Preferences.KEY_HEIGHT, -1)
        val activityLevel = sharedPref.getString(Preferences.KEY_ACTIVITY_LEVEL, null)
        val goalType = sharedPref.getString(Preferences.KEY_GOAL_TYPE, null)
        val carbRatio = sharedPref.getFloat(Preferences.KEY_CARB_RATIO, -1f)
        val proteinRatio = sharedPref.getFloat(Preferences.KEY_PROTEIN_RATIO, -1f)
        val fatRatio = sharedPref.getFloat(Preferences.KEY_FAT_RATIO, -1f)
        return UserInfo(
            gender = Gender.fromString(name = gender),
            age = age,
            weight = weight,
            height = height,
            activityLevel = ActivityLevel.fromString(activityLevel),
            goalType = GoalType.fromString(goalType),
            carbRatio = carbRatio,
            proteinRatio = proteinRatio,
            fatRatio = fatRatio
        )
    }
}