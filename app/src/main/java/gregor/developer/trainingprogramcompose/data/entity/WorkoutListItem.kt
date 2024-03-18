package gregor.developer.training_program_compose.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workout_list_table")
data class WorkoutListItem(
    @PrimaryKey
    val id: Int? = null,
    val workoutName: String,
    val date: String,
    val listId: Int
)
