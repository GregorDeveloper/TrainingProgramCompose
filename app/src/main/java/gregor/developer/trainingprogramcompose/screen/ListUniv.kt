package gregor.developer.trainingprogramcompose.screen

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import gregor.developer.trainingprogramcompose.R
import gregor.developer.trainingprogramcompose.data.static_data.FoodDate
import gregor.developer.trainingprogramcompose.data.static_data.WorkoutDate
import gregor.developer.trainingprogramcompose.screen.food_screen.ItemList
import gregor.developer.trainingprogramcompose.screen.food_screen.UiFoodCategoriesScreen
import gregor.developer.trainingprogramcompose.screen.food_screen.UiFoodNameScreen
import gregor.developer.trainingprogramcompose.screen.food_screen.chooseArrayFood
import gregor.developer.trainingprogramcompose.screen.food_screen.getNewIndexCategory
import gregor.developer.trainingprogramcompose.screen.workout_screen.list_workout.SearchScreen
import gregor.developer.trainingprogramcompose.screen.workout_screen.list_workout_univ.UiNameScreen

@Composable
fun ListUniv(
    foodOrWorkout: Boolean,
    search: MutableState<String>,
    fabVisible: MutableState<Boolean>,
    id: Int,
    clickDescription: (String, Int, Int) -> Unit,
    clearList: () -> Unit,
    saveListAndBack: () -> Unit,
    checking: MutableList<WorkoutDate>,
    saveAndBack: (workout: WorkoutDate) -> Unit,
    addListFood: (workout: WorkoutDate) -> Unit
) {

    val context = LocalContext.current

    var arrayCategory by remember {
        mutableStateOf(chooseArrayCategory(foodOrWorkout, context))
    }
    var arrayName by remember {
        mutableStateOf(getArrayList(-1, context, foodOrWorkout))
    }
    var searchFood by remember {
        mutableStateOf("")
    }
    var indexCategory by remember {
        mutableStateOf(0)
    }
    var nameCategory by remember {
        mutableStateOf(chooseArrayCategory(foodOrWorkout, context).get(0))
    }
    var indexCat by remember {
        mutableStateOf(0)
    }
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            SearchScreen(search) { search ->
                searchFood = search
                //val name = arrayCategory.get(if(arrayCategory.size > 0) indexCat else 0)
                arrayName = resultSearchList(
                    searchFood,
                    indexCategory,
                    context,
                    foodOrWorkout
                )
                arrayCategory = searchItemUniv(search, foodOrWorkout, context)
                indexCat = getNewIndexCategory(nameCategory, arrayCategory)
                if (arrayName.name.size == 0 && arrayCategory.size > 0) {
                    val index = getIndexCategoryUniv(arrayCategory.get(0), context, foodOrWorkout)
                    arrayName = resultSearchList(
                        searchFood,
                        index,
                        context,
                        foodOrWorkout
                        // choseArray = getArrayList(index, context, foodOrWorkout)
                    )
                    indexCat = 0
                }
            }
        },
        floatingActionButton = {
            if (fabVisible.value) {
                Column {
                    FloatingActionButton(
                        onClick = {
                            clearList()
                        },
                        containerColor = Color.Green,
                        modifier = Modifier.padding(2.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.delete_icon),
                            contentDescription = "add food",
                            tint = Color.DarkGray,

                            )
                    }
                    FloatingActionButton(
                        onClick = {
                            saveListAndBack()
                        },
                        containerColor = Color.Green,
                        modifier = Modifier.padding(2.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.save_icon),
                            contentDescription = "add food",
                            tint = Color.DarkGray,
                        )
                    }
                }
            }
        },

        ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
            ) {
                itemsIndexed(arrayCategory) { index, item ->
                    UiFoodCategoriesScreen(
                        name = item,
                        color = if (indexCat == index) Color.Green else Color.White
                    ) {

                        if (searchFood.trim().isNotEmpty()) {
                            arrayName = resultSearchList(
                                searchFood,
                                getIndexForNameUniv(item, getArrayCategory(context, foodOrWorkout)),
                                context,
                                foodOrWorkout
                            )
                        } else {
                            arrayName = getArrayList(index, context, foodOrWorkout)
                        }
                        indexCat = index
                        indexCategory = index
                        nameCategory = item
                    }
                }
            }

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(bottom = 20.dp)
            ) {
                itemsIndexed(arrayName.name) { index, item ->
                    if (foodOrWorkout) {
                        UiNameScreen(
                            workoutDate = WorkoutDate(
                                name = item,
                                equipment = arrayName.calories.get(index),
                                primaryMuscles = arrayName.proteins.get(index),
                                secondaryMuscles = arrayName.fats.get(index),
                                additionalPar = "",
                                checking = checkList(item, checking),
                                numberDescription = ""
                            ),
                            checking = fabVisible.value,
                            id = id,
                            clickDescription = { name ->
                                clickDescription(
                                    name,
                                    indexCat,
                                    index
                                )
                            },
                            clickFood = { food ->
                                saveAndBack(createItemFoodOrWorkout(food, indexCat, index))
                            },
                            addList = { food ->
                                addListFood(createItemFoodOrWorkout(food, indexCat, index))
                            }
                        )
                    } else {
                        UiFoodNameScreen(
                            foodDate = FoodDate(
                                name = item,
                                calories = arrayName.calories.get(index).toDouble(),
                                proteins = arrayName.proteins.get(index).toDouble(),
                                fats = arrayName.fats.get(index).toDouble(),
                                carbohydrates = arrayName.carbohydrates.get(index).toDouble(),
                                checking = checkList(item, checking)
                            ),
                            checking = fabVisible.value,
                            id = id,
                            clickFood = { food ->
                                saveAndBack(
                                    WorkoutDate(
                                        name = food.name,
                                        equipment = food.calories.toString(),
                                        primaryMuscles = food.proteins.toString(),
                                        secondaryMuscles = food.fats.toString(),
                                        additionalPar = food.carbohydrates.toString(),
                                        checking = food.checking,
                                        numberDescription = ""
                                    )
                                )
                            },
                            addList = { food ->
                                addListFood(
                                    foodToWorkout(food)
                                )
                            }
                        )

                    }
                }
            }
        }


    }
}
fun createItemFoodOrWorkout(workout: WorkoutDate, indexCat: Int, indexItem: Int): WorkoutDate{
    return WorkoutDate(
        name = workout.name,
        equipment = workout.equipment,
        primaryMuscles = workout.primaryMuscles,
        secondaryMuscles = workout.secondaryMuscles,
        additionalPar = workout.additionalPar,
        checking = workout.checking,
        numberDescription = "${indexCat}_${indexItem}"
    )
}

fun searchItemUniv(
    search: String,
    foodOrWorkout: Boolean,
    context: Context
): Array<String> {
    Log.d("LogSearch", "_______")
    val name = mutableListOf<List<String>>()
    val arrayCategory = getArrayCategory(context, foodOrWorkout)
    val intermediate = mutableListOf<String>()
    val categoryResult = mutableListOf<String>()
    for ((index, value) in arrayCategory.withIndex()) {
        val listItem = if (foodOrWorkout) chooseArrayWorkout(index, context) else chooseArrayFood(
            index,
            context
        )
        for (i in listItem.name) {
            if (i.lowercase().trim().contains(search.lowercase().trim())) {
                intermediate.add(i)
            }
        }
        name.add(index, intermediate.toList())
        intermediate.clear()
    }
    for ((index, value) in name.withIndex()) {
        if (value.size > 0) {
            categoryResult.add(arrayCategory.get(index))
        }
    }
    return if (search.trim().isEmpty()) arrayCategory else categoryResult.toTypedArray()
}

fun resultSearchList(
    search: String,
    ind: Int,
    context: Context,
    foodOrWorkout: Boolean,
): ItemList {
    val name = mutableListOf<String>()
    val calories = mutableListOf<String>()
    val proteins = mutableListOf<String>()
    val fats = mutableListOf<String>()
    val carbohydrates = mutableListOf<String>()
    val listItem = getArrayList(ind, context, foodOrWorkout)
    listItem.name.forEach {
        Log.d("LogSearchResult", it)
    }
    for ((index, value) in listItem.name.withIndex()) {
        if (listItem.name.get(index).lowercase().trim().contains(search.lowercase().trim())) {
            name.add(listItem.name.get(index))
            calories.add(listItem.calories.get(index))
            proteins.add(listItem.proteins.get(index))
            fats.add(listItem.fats.get(index))
            carbohydrates.add(
                if (listItem.carbohydrates.size != 0) listItem.carbohydrates.get(index) else ""
            )
        }
    }
    return ItemList(
        name = name.toTypedArray(),
        calories = calories.toTypedArray(),
        proteins = proteins.toTypedArray(),
        fats = fats.toTypedArray(),
        carbohydrates = carbohydrates.toTypedArray(),
        arrayOf()
    )
}

fun getIndexCategoryUniv(categoryName: String, context: Context, foodOrWorkout: Boolean): Int {
    val categoryList = getArrayCategory(context, foodOrWorkout)
    for ((index, value) in categoryList.withIndex()) {
        if (value.trim().lowercase().equals(categoryName.trim().lowercase())) {
            return index
        }
    }
    return -1
}

fun getIndexForNameUniv(name: String, category: Array<String>): Int {
    for ((index, value) in category.withIndex()) {
        if (name.equals(value)) {

            return index
        }
    }
    return 0
}

fun getArrayCategory(context: Context, foodOrWorkout: Boolean): Array<String> {
    return if (foodOrWorkout) {
        context.resources.getStringArray(R.array.muscle_group)
    } else {
        context.resources.getStringArray(R.array.food_array_categories)
    }
}

fun getArrayList(index: Int, context: Context, foodOrWorkout: Boolean): ItemList {
    return if (foodOrWorkout) {
        chooseArrayWorkout(index, context)
    } else {
        chooseArrayFood(index, context)
    }
}

fun checkList(name: String, list: MutableList<WorkoutDate>): Boolean {
    var a = false
    list.forEach {
        if (it.name.equals(name)) a = true
    }
    return a
}

fun chooseArrayCategory(foodOrWorkout: Boolean, context: Context): Array<String> {
    return context.resources.getStringArray(if (foodOrWorkout) R.array.muscle_group else R.array.food_array_categories)
}

fun chooseArrayWorkout(index: Int, context: Context): ItemList {
    return when (index) {
        0 -> ItemList(
            context.resources.getStringArray(R.array.abs_workout),
            context.resources.getStringArray(R.array.abs_equipment),
            context.resources.getStringArray(R.array.abs_primary_muscles),
            context.resources.getStringArray(R.array.abs_secondary_muscles),
            arrayOf(),
            arrayOf()
        )

        1 -> ItemList(
            context.resources.getStringArray(R.array.back_wing_workout),
            context.resources.getStringArray(R.array.back_wing_equipment),
            context.resources.getStringArray(R.array.back_wing_primary_muscles),
            context.resources.getStringArray(R.array.back_wing_secondary_muscles),
            arrayOf(),
            arrayOf()
        )

        2 -> ItemList(
            context.resources.getStringArray(R.array.biceps_workout),
            context.resources.getStringArray(R.array.biceps_equipment),
            context.resources.getStringArray(R.array.biceps_primary_muscles),
            context.resources.getStringArray(R.array.biceps_secondary_muscles),
            arrayOf(),
            arrayOf()
        )

        3 -> ItemList(
            context.resources.getStringArray(R.array.calf_workout),
            context.resources.getStringArray(R.array.calf_equipment),
            context.resources.getStringArray(R.array.calf_primary_muscles),
            context.resources.getStringArray(R.array.calf_secondary_muscles),
            arrayOf(),
            arrayOf()
        )

        4 -> ItemList(
            context.resources.getStringArray(R.array.calisthenics_workout),
            context.resources.getStringArray(R.array.calisthenics_equipment),
            context.resources.getStringArray(R.array.calisthenics_primary_muscles),
            context.resources.getStringArray(R.array.calisthenics_secondary_muscles),
            arrayOf(),
            arrayOf()
        )

        5 -> ItemList(
            context.resources.getStringArray(R.array.cardio_workout),
            context.resources.getStringArray(R.array.cardio_equipment),
            context.resources.getStringArray(R.array.cardio_primary_muscles),
            context.resources.getStringArray(R.array.cardio_secondary_muscles),
            arrayOf(),
            arrayOf()
        )

        6 -> ItemList(
            context.resources.getStringArray(R.array.chest_workout),
            context.resources.getStringArray(R.array.chest_equipment),
            context.resources.getStringArray(R.array.chest_primary_muscles),
            context.resources.getStringArray(R.array.chest_secondary_muscles),
            arrayOf(),
            arrayOf()
        )

        7 -> ItemList(
            context.resources.getStringArray(R.array.erector_spinae_workout),
            context.resources.getStringArray(R.array.erector_spinae_equipment),
            context.resources.getStringArray(R.array.erector_spinae_primary_muscles),
            context.resources.getStringArray(R.array.erector_spinae_secondary_muscles),
            arrayOf(),
            arrayOf()
        )

        8 -> ItemList(
            context.resources.getStringArray(R.array.forearm_workout),
            context.resources.getStringArray(R.array.forearm_equipment),
            context.resources.getStringArray(R.array.forearm_primary_muscles),
            context.resources.getStringArray(R.array.forearm_secondary_muscles),
            arrayOf(),
            arrayOf()
        )

        9 -> ItemList(
            context.resources.getStringArray(R.array.full_body_workout),
            context.resources.getStringArray(R.array.full_body_equipment),
            context.resources.getStringArray(R.array.full_body_primary_muscles),
            context.resources.getStringArray(R.array.full_body_secondary_muscles),
            arrayOf(),
            arrayOf()
        )

        10 -> ItemList(
            context.resources.getStringArray(R.array.hip_workout),
            context.resources.getStringArray(R.array.hip_equipment),
            context.resources.getStringArray(R.array.hip_primary_muscles),
            context.resources.getStringArray(R.array.hip_secondary_muscles),
            arrayOf(),
            arrayOf()
        )

        11 -> ItemList(
            context.resources.getStringArray(R.array.leg_workout),
            context.resources.getStringArray(R.array.leg_equipment),
            context.resources.getStringArray(R.array.leg_primary_muscles),
            context.resources.getStringArray(R.array.leg_secondary_muscles),
            arrayOf(),
            arrayOf()
        )

        12 -> ItemList(
            context.resources.getStringArray(R.array.neck_workout),
            context.resources.getStringArray(R.array.neck_equipment),
            context.resources.getStringArray(R.array.neck_primary_muscles),
            context.resources.getStringArray(R.array.neck_secondary_muscles),
            arrayOf(),
            arrayOf()
        )

        13 -> ItemList(
            context.resources.getStringArray(R.array.shoulders_workout),
            context.resources.getStringArray(R.array.shoulders_equipment),
            context.resources.getStringArray(R.array.shoulders_primary_muscles),
            context.resources.getStringArray(R.array.shoulders_secondary_muscles),
            arrayOf(),
            arrayOf()
        )

        14 -> ItemList(
            context.resources.getStringArray(R.array.trapezius_workout),
            context.resources.getStringArray(R.array.trapezius_equipment),
            context.resources.getStringArray(R.array.trapezius_primary_muscles),
            context.resources.getStringArray(R.array.trapezius_secondary_muscles),
            arrayOf(),
            arrayOf()
        )

        15 -> ItemList(
            context.resources.getStringArray(R.array.triceps_workout),
            context.resources.getStringArray(R.array.triceps_equipment),
            context.resources.getStringArray(R.array.triceps_primary_muscles),
            context.resources.getStringArray(R.array.triceps_secondary_muscles),
            arrayOf(),
            arrayOf()
        )

        16 -> ItemList(
            context.resources.getStringArray(R.array.yoga_workout),
            context.resources.getStringArray(R.array.yoga_equipment),
            context.resources.getStringArray(R.array.yoga_primary_muscles),
            context.resources.getStringArray(R.array.yoga_secondary_muscles),
            arrayOf(),
            arrayOf()
        )

        else -> ItemList(
            context.resources.getStringArray(R.array.abs_workout),
            context.resources.getStringArray(R.array.abs_equipment),
            context.resources.getStringArray(R.array.abs_primary_muscles),
            context.resources.getStringArray(R.array.abs_secondary_muscles),
            arrayOf(),
            arrayOf()
        )
    }
}

fun foodToWorkout(food: FoodDate): WorkoutDate {
    return WorkoutDate(
        name = food.name,
        equipment = food.calories.toString(),
        primaryMuscles = food.proteins.toString(),
        secondaryMuscles = food.fats.toString(),
        additionalPar = food.carbohydrates.toString(),
        checking = food.checking,
        numberDescription = ""
    )
}

fun chooseArrayFood(index: Int, context: Context): ItemList {
    return when (index) {
        0 -> ItemList(
            context.resources.getStringArray(R.array.food_array_milk_products),
            context.resources.getStringArray(R.array.calories_array_milk_products),
            context.resources.getStringArray(R.array.proteins_array_milk_products),
            context.resources.getStringArray(R.array.fats_array_milk_products),
            context.resources.getStringArray(R.array.carbohydrates_array_milk_products),
            arrayOf()
        )

        1 -> ItemList(
            context.resources.getStringArray(R.array.food_array_fats_oils),
            context.resources.getStringArray(R.array.calories_array_fats_oils),
            context.resources.getStringArray(R.array.proteins_array_fats_oils),
            context.resources.getStringArray(R.array.fats_array_fats_oils),
            context.resources.getStringArray(R.array.carbohydrates_array_fats_oils),
            arrayOf()
        )

        2 -> ItemList(
            context.resources.getStringArray(R.array.food_array_bread_and_bakery),
            context.resources.getStringArray(R.array.calories_array_bread_and_bakery),
            context.resources.getStringArray(R.array.proteins_array_bread_and_bakery),
            context.resources.getStringArray(R.array.fats_array_bread_and_bakery),
            context.resources.getStringArray(R.array.carbohydrates_array_bread_and_bakery),
            arrayOf()
        )

        3 -> ItemList(
            context.resources.getStringArray(R.array.food_array_cereals),
            context.resources.getStringArray(R.array.calories_array_cereals),
            context.resources.getStringArray(R.array.proteins_array_cereals),
            context.resources.getStringArray(R.array.fats_array_cereals),
            context.resources.getStringArray(R.array.carbohydrates_array_cereals),
            arrayOf()
        )

        4 -> ItemList(
            context.resources.getStringArray(R.array.food_array_vegetables),
            context.resources.getStringArray(R.array.calories_array_vegetables),
            context.resources.getStringArray(R.array.proteins_array_vegetables),
            context.resources.getStringArray(R.array.fats_array_vegetables),
            context.resources.getStringArray(R.array.carbohydrates_array_vegetables),
            arrayOf()
        )

        5 -> ItemList(
            context.resources.getStringArray(R.array.food_array_fruits_berries),
            context.resources.getStringArray(R.array.calories_array_fruits_berries),
            context.resources.getStringArray(R.array.proteins_array_fruits_berries),
            context.resources.getStringArray(R.array.fats_array_fruits_berries),
            context.resources.getStringArray(R.array.carbohydrates_array_fruits_berries),
            arrayOf()
        )

        6 -> ItemList(
            context.resources.getStringArray(R.array.food_array_dried_fruits),
            context.resources.getStringArray(R.array.calories_array_dried_fruits),
            context.resources.getStringArray(R.array.proteins_array_dried_fruits),
            context.resources.getStringArray(R.array.fats_array_dried_fruits),
            context.resources.getStringArray(R.array.carbohydrates_array_dried_fruits),
            arrayOf()
        )

        7 -> ItemList(
            context.resources.getStringArray(R.array.food_array_beans),
            context.resources.getStringArray(R.array.calories_array_beans),
            context.resources.getStringArray(R.array.proteins_array_beans),
            context.resources.getStringArray(R.array.fats_array_beans),
            context.resources.getStringArray(R.array.carbohydrates_array_beans),
            arrayOf()
        )

        8 -> ItemList(
            context.resources.getStringArray(R.array.food_array_mushrooms),
            context.resources.getStringArray(R.array.calories_array_mushrooms),
            context.resources.getStringArray(R.array.proteins_array_mushrooms),
            context.resources.getStringArray(R.array.fats_array_mushrooms),
            context.resources.getStringArray(R.array.carbohydrates_array_mushrooms),
            arrayOf()
        )

        9 -> ItemList(
            context.resources.getStringArray(R.array.food_array_meat_offal_poultry),
            context.resources.getStringArray(R.array.calories_array_meat_offal_poultry),
            context.resources.getStringArray(R.array.proteins_array_meat_offal_poultry),
            context.resources.getStringArray(R.array.fats_array_meat_offal_poultry),
            context.resources.getStringArray(R.array.carbohydrates_array_meat_offal_poultry),
            arrayOf()
        )

        10 -> ItemList(
            context.resources.getStringArray(R.array.food_array_sausage_products),
            context.resources.getStringArray(R.array.calories_array_sausage_products),
            context.resources.getStringArray(R.array.proteins_array_sausage_products),
            context.resources.getStringArray(R.array.fats_array_sausage_products),
            context.resources.getStringArray(R.array.carbohydrates_array_sausage_products),
            arrayOf()
        )

        11 -> ItemList(
            context.resources.getStringArray(R.array.food_array_canned_meat_and_smoked),
            context.resources.getStringArray(R.array.calories_array_canned_meat_and_smoked),
            context.resources.getStringArray(R.array.proteins_array_canned_meat_and_smoked),
            context.resources.getStringArray(R.array.fats_array_canned_meat_and_smoked),
            context.resources.getStringArray(R.array.carbohydrates_array_canned_meat_and_smoked),
            arrayOf()
        )

        12 -> ItemList(
            context.resources.getStringArray(R.array.food_array_eggs),
            context.resources.getStringArray(R.array.calories_array_eggs),
            context.resources.getStringArray(R.array.proteins_array_eggs),
            context.resources.getStringArray(R.array.fats_array_eggs),
            context.resources.getStringArray(R.array.carbohydrates_array_eggs),
            arrayOf()
        )

        13 -> ItemList(
            context.resources.getStringArray(R.array.food_array_fish_and_seafood),
            context.resources.getStringArray(R.array.calories_array_fish_and_seafood),
            context.resources.getStringArray(R.array.proteins_array_fish_and_seafood),
            context.resources.getStringArray(R.array.fats_array_fish_and_seafood),
            context.resources.getStringArray(R.array.carbohydrates_array_fish_and_seafood),
            arrayOf()
        )

        14 -> ItemList(
            context.resources.getStringArray(R.array.food_array_caviar),
            context.resources.getStringArray(R.array.calories_array_caviar),
            context.resources.getStringArray(R.array.proteins_array_caviar),
            context.resources.getStringArray(R.array.fats_array_caviar),
            context.resources.getStringArray(R.array.carbohydrates_array_caviar),
            arrayOf()
        )

        15 -> ItemList(
            context.resources.getStringArray(R.array.food_array_nuts),
            context.resources.getStringArray(R.array.calories_array_nuts),
            context.resources.getStringArray(R.array.proteins_array_nuts),
            context.resources.getStringArray(R.array.fats_array_nuts),
            context.resources.getStringArray(R.array.carbohydrates_array_nuts),
            arrayOf()
        )

        16 -> ItemList(
            context.resources.getStringArray(R.array.food_array_sweets),
            context.resources.getStringArray(R.array.calories_array_sweets),
            context.resources.getStringArray(R.array.proteins_array_sweets),
            context.resources.getStringArray(R.array.fats_array_sweets),
            context.resources.getStringArray(R.array.carbohydrates_array_sweets),
            arrayOf()
        )

        17 -> ItemList(
            context.resources.getStringArray(R.array.food_array_drinks),
            context.resources.getStringArray(R.array.calories_array_drinks),
            context.resources.getStringArray(R.array.proteins_array_drinks),
            context.resources.getStringArray(R.array.fats_array_drinks),
            context.resources.getStringArray(R.array.carbohydrates_array_drinks),
            arrayOf()
        )

        else -> ItemList(
            context.resources.getStringArray(R.array.food_array_milk_products),
            context.resources.getStringArray(R.array.calories_array_milk_products),
            context.resources.getStringArray(R.array.proteins_array_milk_products),
            context.resources.getStringArray(R.array.fats_array_milk_products),
            context.resources.getStringArray(R.array.carbohydrates_array_milk_products),
            arrayOf()
        )
    }
}

fun getArrayDescription() {

}