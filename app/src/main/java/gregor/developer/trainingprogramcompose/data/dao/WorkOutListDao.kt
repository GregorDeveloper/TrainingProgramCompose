package gregor.developer.training_program_compose.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import gregor.developer.training_program_compose.data.entity.TrainingNameItem
import gregor.developer.training_program_compose.data.entity.WorkoutListItem
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkOutListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: WorkoutListItem)

    @Delete
    suspend fun deleteItem(item: WorkoutListItem)

//    @Delete
//    suspend fun deleteAllItemByDate(date: String)

    @Query("SELECT * FROM workout_list_table WHERE listId = :liatId")
    fun getAllItemsById(liatId: Int): Flow<List<WorkoutListItem>>

    @Query("SELECT * FROM workout_list_table WHERE date = :date")
    suspend fun getAllItemsByDate(date: String): List<WorkoutListItem>

    @Query("SELECT * FROM workout_list_table WHERE date = :date")
    fun getAllItemsByDateFlow(date: String): Flow<List<WorkoutListItem>>

    @Query("SELECT * FROM workout_list_table")
    fun getAllItems(): Flow<List<WorkoutListItem>>
}