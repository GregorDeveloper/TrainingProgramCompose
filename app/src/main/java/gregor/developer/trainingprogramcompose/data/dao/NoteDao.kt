package gregor.developer.trainingprogramcompose.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import gregor.developer.trainingprogramcompose.data.entity.NoteItem

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(note: NoteItem)

    @Delete
    suspend fun deleteItem(note: NoteItem)

    @Query("SELECT * FROM note_table WHERE date = :date")
    suspend fun getItemByDate(date: String): NoteItem
}