package com.example.cocktailsapp


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cocktailsapp.model.model.drink.Drink
import com.example.cocktailsapp.model.model.ingredient.IngredientX
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModel: ViewModel() {
    val selected  = MutableLiveData<Drink>()
    val resultDrink = MutableLiveData<List<Drink>?>()
    val resultIngredientX = MutableLiveData<List<IngredientX>?>()

    fun select(drink: Drink){
        selected.value = drink
    }


    fun getResultByName(name: String){
        provideApi().getCocktailByName(name).enqueue(object: Callback<Map<String, List<Drink>>> {

            override fun onResponse(
                call: Call<Map<String, List<Drink>>>,
                response: Response<Map<String, List<Drink>>>,
            ) {
                if (response.isSuccessful){
                    val resp = response.body()
                    if (resp != null) {
                        resultDrink.value = resp["drinks"]
                    }
                }
            }

            override fun onFailure(call: Call<Map<String, List<Drink>>>, t: Throwable) {
                Log.e("RetrofitViewModel", t.message.toString())            }

        })
    }
    fun getResultByIngredient(name: String){
        provideApi().getCocktailByIngredient(name).enqueue(object: Callback<Map<String, List<IngredientX>>> {
            override fun onResponse(
                call: Call<Map<String, List<IngredientX>>>,
                response: Response<Map<String, List<IngredientX>>>,
            ) {
                if (response.isSuccessful){
                    val resp = response.body()
                    if (resp != null) {
                        resultIngredientX.value = resp["ingredients"]
                    }
                }
            }

            override fun onFailure(call: Call<Map<String, List<IngredientX>>>, t: Throwable) {
                Log.e("RetrofitViewModel", t.message.toString())
            }

        })
    }
}