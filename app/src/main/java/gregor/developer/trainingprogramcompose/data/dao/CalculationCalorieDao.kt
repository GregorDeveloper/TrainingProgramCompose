package gregor.developer.training_program_compose.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import gregor.developer.training_program_compose.data.entity.CalculationCalorieItem
import kotlinx.coroutines.flow.Flow


@Dao
interface CalculationCalorieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: CalculationCalorieItem)

    @Delete
    suspend fun deleteItem(item: CalculationCalorieItem)

//    @Delete
//    suspend fun deleteAllItemByDate(date: String)

    @Query("SELECT * FROM calculation_calorie_table WHERE date = :date")
    fun getAllItemByDate(date: String): Flow<List<CalculationCalorieItem>>
}