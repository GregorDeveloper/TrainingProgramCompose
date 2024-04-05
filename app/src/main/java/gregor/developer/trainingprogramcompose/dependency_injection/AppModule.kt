package gregor.developer.training_program_compose.dependency_injection

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import gregor.developer.training_program_compose.data.MainDb
import gregor.developer.training_program_compose.data.repository.TrainingNameRepoImpl
import gregor.developer.training_program_compose.data.repository.TrainingNameRepository
import gregor.developer.training_program_compose.data.repository.WeightRepsWorkoutRepoImpl
import gregor.developer.training_program_compose.data.repository.WeightRepsWorkoutRepository
import gregor.developer.training_program_compose.data.repository.WorkOutListRepository
import gregor.developer.training_program_compose.data.repository.WorkOutRepoImpl
import gregor.developer.trainingprogramcompose.data.entity.WorkoutListTraining
import gregor.developer.trainingprogramcompose.data.repository.WorkoutListTrainingRepoImpl
import gregor.developer.trainingprogramcompose.data.repository.WorkoutListTrainingRepository
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideMainDb(app: Application): MainDb{
        return Room.databaseBuilder(
            app,
            MainDb::class.java,
            "training_program"
            ).build()
    }

    @Provides
    @Singleton
    fun provideTrainingRepo(db: MainDb): TrainingNameRepository{
        return TrainingNameRepoImpl(db.trainingNameDao)
    }

    @Provides
    @Singleton
    fun provideWorkoutListRepo(db: MainDb): WorkOutListRepository{
        return WorkOutRepoImpl(db.workoutListDao)
    }

    @Provides
    @Singleton
    fun provideWeightRepsWorkoutRepo(db: MainDb): WeightRepsWorkoutRepository{
        return WeightRepsWorkoutRepoImpl(db.weightRepsWorkOutDao)
    }

    @Provides
    @Singleton
    fun provideWorkoutListTraining(db: MainDb): WorkoutListTrainingRepository{
        return WorkoutListTrainingRepoImpl(db.workoutListTrainingDao)
    }
}