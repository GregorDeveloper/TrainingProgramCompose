package gregor.developer.trainingprogramcompose.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workout_list_training")
data class WorkoutListTraining (
    @PrimaryKey
    val id: Int? = null,
    val name: String,
    val listId: Int
)