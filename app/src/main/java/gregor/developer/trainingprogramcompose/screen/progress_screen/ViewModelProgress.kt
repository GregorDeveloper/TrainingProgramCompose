package gregor.developer.trainingprogramcompose.screen.progress_screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import gregor.developer.training_program_compose.data.entity.WeightRepsWorkoutItem
import gregor.developer.trainingprogramcompose.data.static_data.calculatinItem
import gregor.developer.trainingprogramcompose.data.static_data.resultItem
import javax.inject.Inject

@HiltViewModel
class ViewModelProgress @Inject constructor(

): ViewModel() {

    private var weight = mutableStateOf(0.0)
    private var reps = mutableStateOf(0)
    private var result = mutableStateOf(0.0)

    private val listTest = mutableListOf<WeightRepsWorkoutItem>()
    val resultList = mutableListOf<resultItem>()
    init{
        getList()
        list()
        Log.d("MyLog", resultList.get(0).toString())
        Log.d("MyLog", resultList.size.toString())
    }

    private fun getList(){
        val listWeightReps = listOf<WeightRepsWorkoutItem>(
            WeightRepsWorkoutItem(1, "Test workout", "120", "3", "22.03.2022"),
            WeightRepsWorkoutItem(2, "Test workout", "100", "5", "22.03.2022"),
            WeightRepsWorkoutItem(3, "Test workout", "80", "10", "22.03.2022")
        )
        listTest.add(listWeightReps.get(0))
        listTest.add(listWeightReps.get(1))
        listTest.add(listWeightReps.get(2))
    }

    private fun list(){
        resultList.add(resultItem(calculationBrzycki(), calculationEpley(), calculationLender()))
    }

    private fun calculationBrzycki(): calculatinItem{

        for ((index, element) in listTest.withIndex()){
            val res = element.weight.toDouble()/ (1.0278 - 0.0278 * element.reps.toInt())
            Log.d("MyLog", res.toString() + " = result")
            return resultCalculation(res, element.weight.toDouble(), element.reps.toInt())
        }
        return calculatinItem(0.0, 0.0, 0)
    }

    private fun calculationEpley(): calculatinItem{
        for ((index, element) in listTest.withIndex()){
            val res = element.weight.toDouble() * (1 + element.reps.toInt()/30)
            return resultCalculation(res, element.weight.toDouble(), element.reps.toInt())
        }
        return calculatinItem(0.0, 0.0, 0)
    }
    private fun calculationLender(): calculatinItem{
        for ((index, element) in listTest.withIndex()){
            val res = (100 + element.weight.toDouble()) / (101.3 - 2.67123 * element.reps.toInt())
            return resultCalculation(res, element.weight.toDouble(), element.reps.toInt())
        }
        return calculatinItem(0.0, 0.0, 0)
    }

    private fun resultCalculation(res: Double, weig: Double, rep: Int): calculatinItem{
        var calcul = calculatinItem(result.value, weight.value, reps.value)
        if(res > result.value){
            result.value = res
            weight.value = weig
            reps.value = rep
            return calcul.copy(res, weig, rep)
        }
         return calcul
    }
}