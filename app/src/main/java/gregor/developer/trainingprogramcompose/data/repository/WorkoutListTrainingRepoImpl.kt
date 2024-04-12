package gregor.developer.trainingprogramcompose.data.repository

import gregor.developer.trainingprogramcompose.data.dao.WorkoutListTrainingDao
import gregor.developer.trainingprogramcompose.data.entity.WorkoutListTraining
import kotlinx.coroutines.flow.Flow

class WorkoutListTrainingRepoImpl(
    private val dao: WorkoutListTrainingDao
): WorkoutListTrainingRepository {
    override suspend fun insertItem(item: WorkoutListTraining) {
        dao.insertItem(item)
    }

    override suspend fun deleteItem(item: WorkoutListTraining) {
        dao.deleteItem(item)
    }

    override fun getAllItemsById(listId: Int): Flow<List<WorkoutListTraining>> {
        return dao.getAllItemsById(listId)
    }
}