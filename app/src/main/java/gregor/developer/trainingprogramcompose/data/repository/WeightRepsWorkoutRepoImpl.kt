package gregor.developer.training_program_compose.data.repository

import gregor.developer.training_program_compose.data.dao.WeightRepsWorkOutDao
import gregor.developer.training_program_compose.data.entity.WeightRepsWorkoutItem
import kotlinx.coroutines.flow.Flow

class WeightRepsWorkoutRepoImpl(
    private val dao: WeightRepsWorkOutDao
): WeightRepsWorkoutRepository {
    override suspend fun insertItem(item: WeightRepsWorkoutItem) {
        dao.insertItem(item)
    }

    override suspend fun deleteItem(item: WeightRepsWorkoutItem) {
        dao.deleteItem(item)
    }

    override fun getAllItemsByName(workoutName: String): Flow<List<WeightRepsWorkoutItem>> {
        return dao.getAllItemsByName(workoutName)
    }
}