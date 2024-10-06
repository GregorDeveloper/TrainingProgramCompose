package gregor.developer.trainingprogramcompose.screen

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
import gregor.developer.trainingprogramcompose.screen.food_screen.UiFoodCategoriesScreen
import gregor.developer.trainingprogramcompose.screen.food_screen.UiFoodNameScreen
import gregor.developer.trainingprogramcompose.screen.food_screen.chooseArrayFood
import gregor.developer.trainingprogramcompose.screen.food_screen.getIndexCategory
import gregor.developer.trainingprogramcompose.screen.food_screen.getIndexForName
import gregor.developer.trainingprogramcompose.screen.food_screen.getNewIndexCategory
import gregor.developer.trainingprogramcompose.screen.food_screen.resultSearchFoodList
import gregor.developer.trainingprogramcompose.screen.food_screen.searchFood
import gregor.developer.trainingprogramcompose.screen.food_screen.toCollectFood
import gregor.developer.trainingprogramcompose.screen.workout_screen.list_workout.SearchScreen

@Composable
fun ListUniv(
    search: MutableState<String>,
    uiEvent: () -> Unit,
    fabVisible: MutableState<Boolean>,
    clearList: () -> Unit,
    saveListAndBack: () -> Unit,
    checking :MutableList<FoodDate>,
    saveAndBack: (food: FoodDate) -> Unit,
    addListFood: (food: FoodDate) -> Unit
) {

    LaunchedEffect(key1 = true) {
        uiEvent()
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
            SearchScreen(search) { search ->
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
              if (fabVisible.value) {
            Column{
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
                            checking = checking.contains(
                                FoodDate(
                                    name = item,
                                    calories = arrayFoodName.calories.get(index).toDouble(),
                                    proteins = arrayFoodName.proteins.get(index).toDouble(),
                                    fats = arrayFoodName.fats.get(index).toDouble(),
                                    carbohydrates = arrayFoodName.carbohydrates.get(index)
                                        .toDouble(),
                                    toCollectFood(checking, item)
                                )
                            ),
                        ),
                        fabVisible.value,
                        { food ->
                            saveAndBack(food)
                        }
                    ) { food ->
                        addListFood(food)
                    }
                }
            }
        }


    }
}