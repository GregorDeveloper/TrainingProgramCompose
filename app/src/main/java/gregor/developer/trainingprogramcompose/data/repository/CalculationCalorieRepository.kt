package gregor.developer.training_program_compose.data.repository

import gregor.developer.training_program_compose.data.entity.CalculationCalorieItem
import kotlinx.coroutines.flow.Flow

interface CalculationCalorieRepository {

    suspend fun insertItem(item: CalculationCalorieItem)

    suspend fun deleteItem(item: CalculationCalorieItem)

//    suspend fun deleteAllItemByDate(date: String)

    fun getAllItemByDate(date: String): Flow<List<CalculationCalorieItem>>

    suspend fun getAllItem(): Flow<List<CalculationCalorieItem>>
}