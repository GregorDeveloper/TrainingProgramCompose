package gregor.developer.trainingprogramcompose.screen.food_screen

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import gregor.developer.trainingprogramcompose.R
import gregor.developer.trainingprogramcompose.data.static_data.FoodDate
import gregor.developer.trainingprogramcompose.screen.workout_screen.list_workout.SearchScreen
import gregor.developer.trainingprogramcompose.utils.UiEvent


@Composable
fun FoodScreen(
    viewModel: FoodScreenViewModel = hiltViewModel(),
    onBack:(Boolean) -> Unit
) {

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect{ uiEvent ->
            when(uiEvent){
                is UiEvent.BackStack -> {
                    Log.d("LogEvent", "Send ui ")
                    onBack(true)
                }
                else -> {

                }
            }

        }
    }

    val context = LocalContext.current

    var arrayCategoryFood by remember {
        mutableStateOf(context.resources.getStringArray(R.array.food_array_categories))
    }
    var arrayFoodName by remember {
        mutableStateOf(chooseArrayFood(-1, context))
    }
    var searchFood by remember {
        mutableStateOf("")
    }
    var indexCategory by remember {
        mutableStateOf(0)
    }
    var indexCat by remember {
        mutableStateOf(0)
    }
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            SearchScreen(viewModel.searchFood) { search ->
                searchFood = search
                val name = arrayCategoryFood.get(indexCat)
                arrayFoodName = resultSearchFoodList(searchFood, indexCategory, context)
                arrayCategoryFood = searchFood(search, context)
                indexCat = getNewIndexCategory(name, arrayCategoryFood)
                if (arrayFoodName.name.size == 0 && arrayCategoryFood.size > 0) {
                    arrayFoodName = resultSearchFoodList(
                        searchFood,
                        getIndexCategory(arrayCategoryFood.get(0), context),
                        context
                    )
                    indexCat = 0
                }
            }
        },
        floatingActionButton = {
            if (viewModel.fabVisible.value) {
                Column{
                    FloatingActionButton(
                        onClick = {
                            viewModel.onEvent(FoodEvent.ClearList)
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
                            viewModel.onEvent(FoodEvent.SaveListAndBack)
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
                itemsIndexed(arrayCategoryFood) { index, item ->
                    UiFoodCategoriesScreen(
                        name = item,
                        color = if (indexCat == index) Color.Green else Color.White
                    ) {
                        if (searchFood.trim().isNotEmpty()) {
                            arrayFoodName = resultSearchFoodList(
                                searchFood,
                                getIndexForName(item, context),
                                context
                            )
                        } else {
                            arrayFoodName = chooseArrayFood(
                                index,
                                context
                            )
                        }
                        indexCat = index
                        indexCategory = index
                    }
                }
            }

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(bottom = 100.dp)
            ) {
                itemsIndexed(arrayFoodName.name) { index, item ->
                    UiFoodNameScreen(
                        FoodDate(
                            name = item,
                            calories = arrayFoodName.calories.get(index).toDouble(),
                            proteins = arrayFoodName.proteins.get(index).toDouble(),
                            fats = arrayFoodName.fats.get(index).toDouble(),
                            carbohydrates = arrayFoodName.carbohydrates.get(index).toDouble(),
                            checking = viewModel.checkingFood.contains(
                                FoodDate(
                                    name = item,
                                    calories = arrayFoodName.calories.get(index).toDouble(),
                                    proteins = arrayFoodName.proteins.get(index).toDouble(),
                                    fats = arrayFoodName.fats.get(index).toDouble(),
                                    carbohydrates = arrayFoodName.carbohydrates.get(index)
                                        .toDouble(),
                                    toCollectFood(viewModel.checkingFood, item)
                                )
                            ),
                        ),

                        viewModel.fabVisible.value,
                        id = -1,
                        { food ->
                            viewModel.onEvent(FoodEvent.SaveAndBack(food))
                        }
                    ) { food ->
                        viewModel.onEvent(FoodEvent.AddListFood(food))
                    }
                }
            }
        }


    }


}


fun searchFood(
    search: String,
    context: Context
): Array<String> {
    val arrayCategory = context.resources.getStringArray(R.array.food_array_categories)
    val name = mutableListOf<List<String>>()

    val intermediate = mutableListOf<String>()
    val categoryResult = mutableListOf<String>()
    for ((index, value) in arrayCategory.withIndex()) {
        val food = chooseArrayFood(index, context)
        for (i in food.name) {
            if (i.lowercase().trim().contains(search.lowercase().trim())) {
                Log.d("LogSearch", "name $i cat")
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

fun resultSearchFoodList(
    search: String,
    ind: Int,
    context: Context,

): ItemList {
    val name = mutableListOf<String>()
    val calories = mutableListOf<String>()
    val proteins = mutableListOf<String>()
    val fats = mutableListOf<String>()
    val carbohydrates = mutableListOf<String>()
    val food = chooseArrayFood(ind, context)
    for ((index, value) in food.name.withIndex()) {
        if (food.name.get(index).lowercase().trim().contains(search.lowercase().trim())) {
            name.add(food.name.get(index))
            calories.add(food.calories.get(index))
            proteins.add(food.proteins.get(index))
            fats.add(food.fats.get(index))
            carbohydrates.add(food.carbohydrates.get(index))
        }
    }
    return ItemList(
        name = name.toTypedArray(),
        calories = calories.toTypedArray(),
        proteins = proteins.toTypedArray(),
        fats = fats.toTypedArray(),
        carbohydrates = carbohydrates.toTypedArray()
    )
}


fun chooseArrayFood(index: Int, context: Context): ItemList {
    return when (index) {
        0 -> ItemList(
            context.resources.getStringArray(R.array.food_array_milk_products),
            context.resources.getStringArray(R.array.calories_array_milk_products),
            context.resources.getStringArray(R.array.proteins_array_milk_products),
            context.resources.getStringArray(R.array.fats_array_milk_products),
            context.resources.getStringArray(R.array.carbohydrates_array_milk_products)
        )

        1 -> ItemList(
            context.resources.getStringArray(R.array.food_array_fats_oils),
            context.resources.getStringArray(R.array.calories_array_fats_oils),
            context.resources.getStringArray(R.array.proteins_array_fats_oils),
            context.resources.getStringArray(R.array.fats_array_fats_oils),
            context.resources.getStringArray(R.array.carbohydrates_array_fats_oils)
        )

        2 -> ItemList(
            context.resources.getStringArray(R.array.food_array_bread_and_bakery),
            context.resources.getStringArray(R.array.calories_array_bread_and_bakery),
            context.resources.getStringArray(R.array.proteins_array_bread_and_bakery),
            context.resources.getStringArray(R.array.fats_array_bread_and_bakery),
            context.resources.getStringArray(R.array.carbohydrates_array_bread_and_bakery),
        )

        3 -> ItemList(
            context.resources.getStringArray(R.array.food_array_cereals),
            context.resources.getStringArray(R.array.calories_array_cereals),
            context.resources.getStringArray(R.array.proteins_array_cereals),
            context.resources.getStringArray(R.array.fats_array_cereals),
            context.resources.getStringArray(R.array.carbohydrates_array_cereals),
        )

        4 -> ItemList(
            context.resources.getStringArray(R.array.food_array_vegetables),
            context.resources.getStringArray(R.array.calories_array_vegetables),
            context.resources.getStringArray(R.array.proteins_array_vegetables),
            context.resources.getStringArray(R.array.fats_array_vegetables),
            context.resources.getStringArray(R.array.carbohydrates_array_vegetables),
        )

        5 -> ItemList(
            context.resources.getStringArray(R.array.food_array_fruits_berries),
            context.resources.getStringArray(R.array.calories_array_fruits_berries),
            context.resources.getStringArray(R.array.proteins_array_fruits_berries),
            context.resources.getStringArray(R.array.fats_array_fruits_berries),
            context.resources.getStringArray(R.array.carbohydrates_array_fruits_berries),
        )

        6 -> ItemList(
            context.resources.getStringArray(R.array.food_array_dried_fruits),
            context.resources.getStringArray(R.array.calories_array_dried_fruits),
            context.resources.getStringArray(R.array.proteins_array_dried_fruits),
            context.resources.getStringArray(R.array.fats_array_dried_fruits),
            context.resources.getStringArray(R.array.carbohydrates_array_dried_fruits),
        )

        7 -> ItemList(
            context.resources.getStringArray(R.array.food_array_beans),
            context.resources.getStringArray(R.array.calories_array_beans),
            context.resources.getStringArray(R.array.proteins_array_beans),
            context.resources.getStringArray(R.array.fats_array_beans),
            context.resources.getStringArray(R.array.carbohydrates_array_beans),
        )

        8 -> ItemList(
            context.resources.getStringArray(R.array.food_array_mushrooms),
            context.resources.getStringArray(R.array.calories_array_mushrooms),
            context.resources.getStringArray(R.array.proteins_array_mushrooms),
            context.resources.getStringArray(R.array.fats_array_mushrooms),
            context.resources.getStringArray(R.array.carbohydrates_array_mushrooms),
        )

        9 -> ItemList(
            context.resources.getStringArray(R.array.food_array_meat_offal_poultry),
            context.resources.getStringArray(R.array.calories_array_meat_offal_poultry),
            context.resources.getStringArray(R.array.proteins_array_meat_offal_poultry),
            context.resources.getStringArray(R.array.fats_array_meat_offal_poultry),
            context.resources.getStringArray(R.array.carbohydrates_array_meat_offal_poultry)
        )

        10 -> ItemList(
            context.resources.getStringArray(R.array.food_array_sausage_products),
            context.resources.getStringArray(R.array.calories_array_sausage_products),
            context.resources.getStringArray(R.array.proteins_array_sausage_products),
            context.resources.getStringArray(R.array.fats_array_sausage_products),
            context.resources.getStringArray(R.array.carbohydrates_array_sausage_products),
        )

        11 -> ItemList(
            context.resources.getStringArray(R.array.food_array_canned_meat_and_smoked),
            context.resources.getStringArray(R.array.calories_array_canned_meat_and_smoked),
            context.resources.getStringArray(R.array.proteins_array_canned_meat_and_smoked),
            context.resources.getStringArray(R.array.fats_array_canned_meat_and_smoked),
            context.resources.getStringArray(R.array.carbohydrates_array_canned_meat_and_smoked),
        )

        12 -> ItemList(
            context.resources.getStringArray(R.array.food_array_eggs),
            context.resources.getStringArray(R.array.calories_array_eggs),
            context.resources.getStringArray(R.array.proteins_array_eggs),
            context.resources.getStringArray(R.array.fats_array_eggs),
            context.resources.getStringArray(R.array.carbohydrates_array_eggs),
        )

        13 -> ItemList(
            context.resources.getStringArray(R.array.food_array_fish_and_seafood),
            context.resources.getStringArray(R.array.calories_array_fish_and_seafood),
            context.resources.getStringArray(R.array.proteins_array_fish_and_seafood),
            context.resources.getStringArray(R.array.fats_array_fish_and_seafood),
            context.resources.getStringArray(R.array.carbohydrates_array_fish_and_seafood),
        )

        14 -> ItemList(
            context.resources.getStringArray(R.array.food_array_caviar),
            context.resources.getStringArray(R.array.calories_array_caviar),
            context.resources.getStringArray(R.array.proteins_array_caviar),
            context.resources.getStringArray(R.array.fats_array_caviar),
            context.resources.getStringArray(R.array.carbohydrates_array_caviar),

            )

        15 -> ItemList(
            context.resources.getStringArray(R.array.food_array_nuts),
            context.resources.getStringArray(R.array.calories_array_nuts),
            context.resources.getStringArray(R.array.proteins_array_nuts),
            context.resources.getStringArray(R.array.fats_array_nuts),
            context.resources.getStringArray(R.array.carbohydrates_array_nuts),
        )

        16 -> ItemList(
            context.resources.getStringArray(R.array.food_array_sweets),
            context.resources.getStringArray(R.array.calories_array_sweets),
            context.resources.getStringArray(R.array.proteins_array_sweets),
            context.resources.getStringArray(R.array.fats_array_sweets),
            context.resources.getStringArray(R.array.carbohydrates_array_sweets),
        )

        17 -> ItemList(
            context.resources.getStringArray(R.array.food_array_drinks),
            context.resources.getStringArray(R.array.calories_array_drinks),
            context.resources.getStringArray(R.array.proteins_array_drinks),
            context.resources.getStringArray(R.array.fats_array_drinks),
            context.resources.getStringArray(R.array.carbohydrates_array_drinks),
        )

        else -> ItemList(
            context.resources.getStringArray(R.array.food_array_milk_products),
            context.resources.getStringArray(R.array.calories_array_milk_products),
            context.resources.getStringArray(R.array.proteins_array_milk_products),
            context.resources.getStringArray(R.array.fats_array_milk_products),
            context.resources.getStringArray(R.array.carbohydrates_array_milk_products)
        )
    }
}

fun getIndexForName(name: String, context: Context): Int {
    val categoryList = context.resources.getStringArray(R.array.food_array_categories)
    for ((index, value) in categoryList.withIndex()) {
        if (name.equals(value)) {
            return index
        }
    }
    return 0
}

fun getIndexCategory(categoryName: String, context: Context): Int {
    val categoryList = context.resources.getStringArray(R.array.food_array_categories)
    for ((index, value) in categoryList.withIndex()) {
        if (value.trim().lowercase().equals(categoryName.trim().lowercase())) {
            return index
        }
    }
    return -1
}

fun getNewIndexCategory(name: String, array: Array<String>): Int {
    for ((index, value) in array.withIndex()) {
        if (name.trim().lowercase().equals(value.trim().lowercase())) {
            return index
        }
    }
    return 0
}


fun toCollectFood(listFood: MutableList<FoodDate>, name: String): Boolean {
    var check = false
    if (listFood.isEmpty()) {
        return false
    } else {
        listFood.forEach { food ->
            if (food.name.trim().equals(name.trim())) {
                return true
            }
        }
    }
    return check
}

data class ItemList(
    val name: Array<String>,
    val calories: Array<String>,
    val proteins: Array<String>,
    val fats: Array<String>,
    val carbohydrates: Array<String>
)


