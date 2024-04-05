package gregor.developer.trainingprogramcompose.data.repository

import gregor.developer.trainingprogramcompose.data.entity.WorkoutListTraining
import kotlinx.coroutines.flow.Flow


interface WorkoutListTrainingRepository {

    suspend fun insertItem(item: WorkoutListTraining)

    suspend fun deleteItem(item: WorkoutListTraining)

    fun getAllItemsById(listId: Int): Flow<List<WorkoutListTraining>>
}