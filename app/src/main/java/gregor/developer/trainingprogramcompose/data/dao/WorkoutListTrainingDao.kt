package gregor.developer.trainingprogramcompose.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import gregor.developer.training_program_compose.data.entity.WorkoutListItem
import gregor.developer.trainingprogramcompose.data.entity.WorkoutListTraining
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutListTrainingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: WorkoutListTraining)

    @Delete
    suspend fun deleteItem(item: WorkoutListTraining)

    @Query("SELECT * FROM workout_list_training WHERE listId = :liatId")
    fun getAllItemsById(liatId: Int): Flow<List<WorkoutListTraining>>
}