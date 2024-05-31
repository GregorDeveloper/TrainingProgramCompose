package gregor.developer.training_program_compose.data.repository

import androidx.compose.runtime.snapshots.SnapshotStateList
import gregor.developer.training_program_compose.data.entity.WeightRepsWorkoutItem

interface WeightRepsWorkoutRepository {

    suspend fun insertItem(item: WeightRepsWorkoutItem)

    suspend fun deleteItem(item: WeightRepsWorkoutItem)

    suspend fun getAllItemsCurrentTime(workoutName: String): List<WeightRepsWorkoutItem>

    suspend fun getWeightReps(workoutName: String): WeightRepsWorkoutItem?

    suspend fun getWeightRepsByDate(workoutName: String, currentTime: String): WeightRepsWorkoutItem

    suspend fun getLastWeightReps(workoutName: String, currentId: Int): WeightRepsWorkoutItem

    suspend fun getNextTraining(workoutName: String, currentId: Int): WeightRepsWorkoutItem

    suspend fun getLastTraining(workoutName: String): List<WeightRepsWorkoutItem>
}