package gregor.developer.training_program_compose.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "training_name_table")
data class TrainingNameItem(
    @PrimaryKey
    val id: Int? = null,
    val name: String,
    val dayOfWeek: String,
    val numberOfList: Int
)
