package gregor.developer.training_program_compose.data.repository

import gregor.developer.training_program_compose.data.dao.WorkOutListDao
import gregor.developer.training_program_compose.data.entity.WorkoutListItem
import kotlinx.coroutines.flow.Flow

class WorkOutRepoImpl(
    private val dao: WorkOutListDao
): WorkOutListRepository {
    override suspend fun insertItem(item: WorkoutListItem) {
        dao.insertItem(item)
    }

    override suspend fun deleteItem(item: WorkoutListItem) {
       dao.deleteItem(item)
    }

    override fun getAllItemsById(listId: Int): Flow<List<WorkoutListItem>> {
        return dao.getAllItemsById(listId)
    }

    override suspend fun getAllItemsByDate(date: String): List<WorkoutListItem> {
        return  dao.getAllItemsByDate(date)
    }

    override fun getAllItemsByDateFlow(date: String): Flow<List<WorkoutListItem>>{
        return dao.getAllItemsByDateFlow(date)
    }
}