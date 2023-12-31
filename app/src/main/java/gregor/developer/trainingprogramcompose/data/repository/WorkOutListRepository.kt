package gregor.developer.training_program_compose.data.repository

import gregor.developer.training_program_compose.data.entity.WorkoutListItem
import kotlinx.coroutines.flow.Flow

interface WorkOutListRepository {

    suspend fun insertItem(item: WorkoutListItem)

    suspend fun deleteItem(item: WorkoutListItem)

    fun getAllItemsById(listId: Int): Flow<List<WorkoutListItem>>
}