package gregor.developer.training_program_compose.data

import androidx.room.Database
import androidx.room.RoomDatabase
import gregor.developer.training_program_compose.data.dao.TrainingNameDao
import gregor.developer.training_program_compose.data.dao.WeightRepsWorkOutDao
import gregor.developer.training_program_compose.data.dao.WorkOutListDao
import gregor.developer.training_program_compose.data.entity.TrainingNameItem
import gregor.developer.training_program_compose.data.entity.WeightRepsWorkoutItem
import gregor.developer.training_program_compose.data.entity.WorkoutListItem

@Database(
    entities = [
        TrainingNameItem::class,
        WorkoutListItem::class,
        WeightRepsWorkoutItem::class
    ],
    version = 1
)
abstract class MainDb : RoomDatabase() {

    abstract val trainingNameDao: TrainingNameDao
    abstract val workoutListDao: WorkOutListDao
    abstract val weightRepsWorkOutDao: WeightRepsWorkOutDao

}