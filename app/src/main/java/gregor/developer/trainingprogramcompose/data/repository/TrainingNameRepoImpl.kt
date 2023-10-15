package gregor.developer.training_program_compose.data.repository

import gregor.developer.training_program_compose.data.dao.TrainingNameDao
import gregor.developer.training_program_compose.data.entity.TrainingNameItem
import kotlinx.coroutines.flow.Flow

class TrainingNameRepoImpl(
    private val dao: TrainingNameDao
): TrainingNameRepository {
    override suspend fun insertItem(item: TrainingNameItem) {
        dao.insertItem(item)
    }

    override suspend fun deleteItem(item: TrainingNameItem) {
        dao.deleteItem(item)
    }

    override fun getAllItems(): Flow<List<TrainingNameItem>> {
        return dao.getAllItems()
    }

    override suspend fun getLastElement(): TrainingNameItem {
        return dao.getLastElement()
    }


}