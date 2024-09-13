package gregor.developer.training_program_compose.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "calculation_calorie_table")
data class CalculationCalorieItem (
    @PrimaryKey
    val id: Int? = null,
    val name: String,
    val calories: Double = 0.0,
    val proteins: Double = 0.0,
    val fats: Double = 0.0,
    val carbohydrates: Double = 0.0,
    val date: String
)