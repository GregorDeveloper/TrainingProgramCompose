package gregor.developer.training_program_compose.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weight_reps_table")
data class WeightRepsWorkoutItem(
    @PrimaryKey
    val id: Int? = null,
    val workOutName: String,
    var weight: String,
    var reps: String,
    val date: String,

)
