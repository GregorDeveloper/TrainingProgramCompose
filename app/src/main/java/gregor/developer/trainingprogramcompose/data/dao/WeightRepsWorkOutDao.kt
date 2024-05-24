package gregor.developer.training_program_compose.data.dao

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import gregor.developer.training_program_compose.data.entity.WeightRepsWorkoutItem

@Dao
interface WeightRepsWorkOutDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: WeightRepsWorkoutItem)

    @Delete
    suspend fun deleteItem(item: WeightRepsWorkoutItem)

    @Query("SELECT * FROM weight_reps_table WHERE workoutName = :workoutName")
    suspend fun getAllItemsByName(workoutName: String): List<WeightRepsWorkoutItem>

    @Query("SELECT * FROM weight_reps_table WHERE workOutName = :workoutName AND date = :currentTime")
    suspend fun getCurrentWeightReps(workoutName: String, currentTime: String): WeightRepsWorkoutItem

    //AND id = (SELECT max(id) FROM weight_reps_table)
    @Query("SELECT * FROM weight_reps_table WHERE workOutName = :workoutName ORDER BY id DESC")
    suspend fun getWeightReps(workoutName: String): WeightRepsWorkoutItem
    @Query("SELECT * FROM weight_reps_table WHERE workOutName = :workoutName AND id < :currentId ORDER BY id DESC")
    suspend fun getLastTraining(workoutName: String, currentId: Int): WeightRepsWorkoutItem

    @Query("SELECT * FROM weight_reps_table WHERE workOutName = :workoutName AND id > :currentId")
    suspend fun getNextTraining(workoutName: String, currentId: Int): WeightRepsWorkoutItem

    @Query("SELECT * FROM weight_reps_table WHERE workOutName = :workoutName ORDER BY id DESC LIMIT 10")
    suspend fun getLastTraining(workoutName: String): List<WeightRepsWorkoutItem>
}