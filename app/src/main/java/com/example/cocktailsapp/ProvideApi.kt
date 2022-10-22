package com.example.cocktailsapp


import com.example.cocktailsapp.model.model.drink.Drink
import com.example.cocktailsapp.model.model.ingredient.IngredientX
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ProvideApi {
    @GET("search.php?")
        fun getCocktailByName(@Query("s") cocktailName: String): Call<Map<String, List<Drink>>>
    @GET("search.php?")
        fun getCocktailByIngredient(@Query("i") cocktailName: String): Call<Map<String, List<IngredientX>>>
    }


internal fun provideApi(): ProvideApi {
    val baseurl = "https://www.thecocktaildb.com/api/json/v1/1/"
    val loginInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loginInterceptor)
        .build()
    val retrofit = Retrofit.Builder()
        .baseUrl(baseurl)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
    return retrofit.create(ProvideApi::class.java)
}