package gregor.developer.training_program_compose.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import gregor.developer.training_program_compose.data.entity.TrainingNameItem
import kotlinx.coroutines.flow.Flow


@Dao
interface TrainingNameDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: TrainingNameItem)

    @Delete
    suspend fun deleteItem(item: TrainingNameItem)

    @Query("SELECT * FROM training_name_table")
    fun getAllItems(): Flow<List<TrainingNameItem>>

    @Query("SELECT * FROM training_name_table ORDER BY ID DESC LIMIT 1")
    suspend fun getLastElement(): TrainingNameItem

}