package gregor.developer.training_program_compose.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import gregor.developer.training_program_compose.data.entity.WorkoutListItem
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkOutListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: WorkoutListItem)

    @Delete
    suspend fun deleteItem(item: WorkoutListItem)

    @Query("SELECT * FROM workout_list_table WHERE listId = :listId")
    fun getAllItemsById(listId: Int): Flow<List<WorkoutListItem>>


}