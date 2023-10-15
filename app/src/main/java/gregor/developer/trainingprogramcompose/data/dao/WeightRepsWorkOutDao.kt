package gregor.developer.training_program_compose.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import gregor.developer.training_program_compose.data.entity.WeightRepsWorkoutItem
import kotlinx.coroutines.flow.Flow

@Dao
interface WeightRepsWorkOutDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: WeightRepsWorkoutItem)

    @Delete
    suspend fun deleteItem(item: WeightRepsWorkoutItem)

    @Query("SELECT * FROM weight_reps_table WHERE workoutName = :workoutName")
    fun getAllItemsByName(workoutName: String): Flow<List<WeightRepsWorkoutItem>>
}