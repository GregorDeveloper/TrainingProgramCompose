package gregor.developer.training_program_compose.data.repository

import gregor.developer.training_program_compose.data.entity.TrainingNameItem
import kotlinx.coroutines.flow.Flow

interface TrainingNameRepository {

    suspend fun insertItem(item: TrainingNameItem)

    suspend fun deleteItem(item: TrainingNameItem)

    fun getAllItems(): Flow<List<TrainingNameItem>>

    suspend fun getLastElement(): TrainingNameItem
}