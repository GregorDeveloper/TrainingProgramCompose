package gregor.developer.training_program_compose.data.repository

import gregor.developer.training_program_compose.data.dao.CalculationCalorieDao
import gregor.developer.training_program_compose.data.entity.CalculationCalorieItem
import kotlinx.coroutines.flow.Flow

class CalculationCalorieRepoImpl(
    private val dao: CalculationCalorieDao
): CalculationCalorieRepository {
    override suspend fun insertItem(item: CalculationCalorieItem) {
        dao.insertItem(item)
    }

    override suspend fun deleteItem(item: CalculationCalorieItem) {
        dao.deleteItem(item)
    }

//    override suspend fun deleteAllItemByDate(date: String) {
//        dao.deleteAllItemByDate(date)
//    }

    override fun getAllItemByDate(date: String): Flow<List<CalculationCalorieItem>> {
        return dao.getAllItemByDate(date)
    }

    override suspend fun getAllItem(): Flow<List<CalculationCalorieItem>> {
        TODO("Not yet implemented")
    }


}