package gregor.developer.trainingprogramcompose.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class NoteItem(
    @PrimaryKey
    val id: Int? = null,
    val workoutName: String,
    val date: String,
    val note: String,
)
