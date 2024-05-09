package gregor.developer.trainingprogramcompose.utils

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import gregor.developer.training_program_compose.data.entity.WeightRepsWorkoutItem

fun ViewModel.splitString(
    list: List<WeightRepsWorkoutItem?>,
    number: Int
): SnapshotStateList<WeightRepsWorkoutItem> {

    val item = list.get(number)
    val weight = splitWeight(item)
    val reps = splitReps(item)
    val itemsList = mutableStateListOf<WeightRepsWorkoutItem>()
    for ((index, element) in weight!!.withIndex()) {

        itemsList.add(
            WeightRepsWorkoutItem(
                id = item!!.id,
                workOutName = item.workOutName,
                weight = element,
                reps = reps!!.get(index),
                date = item.date,
                ""
            )
        )
    }
    return itemsList
}

fun splitWeight(item: WeightRepsWorkoutItem?): List<String>? {
    return item?.weight?.split("_")
}

fun splitReps(item: WeightRepsWorkoutItem?): List<String>? {
    return item?.reps?.split("_")
}