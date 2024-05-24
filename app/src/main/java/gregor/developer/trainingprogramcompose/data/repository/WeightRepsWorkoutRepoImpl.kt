package gregor.developer.training_program_compose.data.repository

import androidx.compose.runtime.snapshots.SnapshotStateList
import gregor.developer.training_program_compose.data.dao.WeightRepsWorkOutDao
import gregor.developer.training_program_compose.data.entity.WeightRepsWorkoutItem

class WeightRepsWorkoutRepoImpl(
    private val dao: WeightRepsWorkOutDao
): WeightRepsWorkoutRepository {
    override suspend fun insertItem(item: WeightRepsWorkoutItem) {
        dao.insertItem(item)

    }

    override suspend fun deleteItem(item: WeightRepsWorkoutItem) {
        dao.deleteItem(item)
    }

    override suspend fun getAllItemsCurrentTime(workoutName: String): List<WeightRepsWorkoutItem> {
        return dao.getAllItemsByName(workoutName)
    }

    override suspend fun getWeightReps(workoutName: String): WeightRepsWorkoutItem {
        return dao.getWeightReps(workoutName)
    }

    override suspend fun getWeightRepsByDate(
        workoutName: String,
        currentTime: String
    ): WeightRepsWorkoutItem {
        return dao.getCurrentWeightReps(workoutName, currentTime)
    }

    override suspend fun getLastWeightReps(
        workoutName: String,
        currentId: Int,
    ): WeightRepsWorkoutItem {
        return dao.getLastTraining(workoutName, currentId)
    }

    override suspend fun getNextTraining(
        workoutName: String,
        currentId: Int
    ): WeightRepsWorkoutItem {
        return dao.getNextTraining(workoutName, currentId)
    }

    override suspend fun getLastTraining(workoutName: String): List<WeightRepsWorkoutItem> {
        return dao.getLastTraining(workoutName)
    }
}