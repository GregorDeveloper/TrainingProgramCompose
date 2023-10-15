package gregor.developer.training_program_compose.data.repository

import gregor.developer.training_program_compose.data.entity.WeightRepsWorkoutItem
import kotlinx.coroutines.flow.Flow

interface WeightRepsWorkoutRepository {

    suspend fun insertItem(item: WeightRepsWorkoutItem)

    suspend fun deleteItem(item: WeightRepsWorkoutItem)

    fun getAllItemsByName(workoutName: String): Flow<List<WeightRepsWorkoutItem>>
}