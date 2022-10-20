package com.example.cocktailsapp.model


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DrinkViewModel: ViewModel() {
    private val selected  = MutableLiveData<Drink>()
    val result = MutableLiveData<List<Drink>?>()

    fun select(drink: Drink){
        selected.value = drink
    }

    init {
        getResultByName("margarita")
        getResulByIngredient("Vodka")
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
                        result.value = resp["drinks"]
                    }
                }
            }

            override fun onFailure(call: Call<Map<String, List<Drink>>>, t: Throwable) {
                Log.e("RetrofitViewModel", t.message.toString())            }

        })
    }
    fun getResulByIngredient(name: String){
        provideApi().getCocktailByIngredient(name).enqueue(object: Callback<Map<String, List<Drink>>> {
            override fun onResponse(
                call: Call<Map<String, List<Drink>>>,
                response: Response<Map<String, List<Drink>>>,
            ) {
                if (response.isSuccessful){
                    val resp = response.body()
                    if (resp != null) {
                        result.value = resp["drinks"]
                    }
                }
            }

            override fun onFailure(call: Call<Map<String, List<Drink>>>, t: Throwable) {
                Log.e("RetrofitViewModel", t.message.toString())
            }

        })
    }
}