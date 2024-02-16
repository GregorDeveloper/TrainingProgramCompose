package gregor.developer.trainingprogramcompose.screen.progress_screen

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gregor.developer.training_program_compose.data.entity.WeightRepsWorkoutItem
import gregor.developer.training_program_compose.data.repository.WeightRepsWorkoutRepository
import gregor.developer.trainingprogramcompose.R
import gregor.developer.trainingprogramcompose.data.static_data.calculatinItem
import gregor.developer.trainingprogramcompose.data.static_data.resultItem
import gregor.developer.trainingprogramcompose.utils.StringResourcesProvider
import gregor.developer.trainingprogramcompose.utils.splitString
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@HiltViewModel
class ViewModelProgress @Inject constructor(
    private val repository: WeightRepsWorkoutRepository,
    private val stringResProv: StringResourcesProvider,
) : ViewModel() {
    private var listWeightReps = listOf<WeightRepsWorkoutItem?>(null)
    val workoutNameList = listOf<String>(
        // stringResProv.getString(R.string.barbell_guillotine_bench_press),
        stringResProv.getString(R.string.barbell_incline_shoulder_raise),
        stringResProv.getString(R.string.lying_face_up_plate_neck_resistance),
        stringResProv.getString(R.string.cable_wrist_curl),
        //stringResProv.getString(R.string.clean_deadlift),
        //  stringResProv.getString(R.string.barbell_squat),
    )
    val openCalculationRM = mutableStateOf(false)
    private var date = mutableStateOf("")
    private var weight = mutableStateOf(0.0)
    private var reps = mutableStateOf(0)
    private var result = mutableStateOf(0.0)


    val resultList = mutableStateListOf<resultItem>()
    // private var splitListWorkout = mutableStateListOf<WeightRepsWorkoutItem>()

    init {
        // getWorkoutDate()
    }

    fun onEvent(event: ProgressEvent) {
        when (event) {
            ProgressEvent.openRM -> {
                if (openCalculationRM.value) {
                    openCalculationRM.value = false
                } else {
                    openCalculationRM.value = true
                    if (resultList.isEmpty()) {
                        getWorkoutDate()
                    }

                }
            }
        }
    }


    private fun addResultItem(nameWorkout: String, splitList: List<WeightRepsWorkoutItem>) {
        if (splitList.isNotEmpty()) {
            resultList.add(
                resultItem(
                    nameWorkout,
                    searchMaxWeight(splitList),
                    searchMaxWeight(splitList).date,
                )
            )
            clearDate()
        }
    }

    private fun getWorkoutDate() {
        viewModelScope.launch {
            for ((index, element) in workoutNameList.withIndex()) {
                listWeightReps = repository.getAllItemsCurrentTime(element)
                val splitList: MutableList<WeightRepsWorkoutItem> = mutableListOf()
                if (listWeightReps.isNotEmpty()) {

                    for ((ind, el) in listWeightReps.withIndex()) {
                        splitList.addAll(splitString(listWeightReps, ind))
                        // splitListWorkout.addAll(splitString(listWeightReps, ind))
                    }
                    addResultItem(element, splitList)
                }
            }
        }
    }

    private fun searchMaxWeight(splitList: List<WeightRepsWorkoutItem>): calculatinItem {
        var calculateItem = calculatinItem(0.0, 0.0, 0, "")
        for ((index, element) in splitList.withIndex()) {
            if (element.weight.isNotEmpty() && element.reps.isNotEmpty()) {
                val res =
                    calculationMaxWeight(element.weight.toDouble(), element.reps.toInt())
                calculateItem =
                    resultCalculation(
                        res,
                        element.date,
                        element.weight.toDouble(),
                        element.reps.toInt()
                    )
            }
        }
        return calculateItem
    }

    private fun calculationMaxWeight(weight: Double, reps: Int): Double {
        val result: Double = weight * (36 / (37 - reps.toDouble()))
        return result
    }

    private fun resultCalculation(res: Double, dt: String, weig: Double, rep: Int): calculatinItem {
        val calculate = calculatinItem(result.value, weight.value, reps.value, date.value)
        if (res > result.value) {
            result.value = res
            date.value = dt
            weight.value = weig
            reps.value = rep
            return calculate.copy(res, weig, rep)
        }
        return calculate
    }

    private fun clearDate() {
        result.value = 0.0
        weight.value = 0.0
        reps.value = 0
    }
}