package com.example.cocktailsapp.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktailsapp.R
import com.example.cocktailsapp.databinding.FragmentRvCocktailBinding
import com.squareup.picasso.Picasso

class DrinkAdapter(private val list: List<Drink>, private val delegate: (Drink) -> Unit) : RecyclerView.Adapter<DrinkAdapter.ViewHolder>() {

    class ViewHolder(binding: FragmentRvCocktailBinding): RecyclerView.ViewHolder(binding.root) {
        private val imageDrink = binding.ivRvDrink
        private val nameDrink = binding.tvDrinkName



        fun bind(drink: Drink, holder: ViewHolder){
            nameDrink.text = drink.strDrink
            Picasso.with(holder.itemView.context)
                .load(drink.strDrinkThumb)
                .fit()
                .error(R.drawable.ic_baseline_error_24)
                .into(imageDrink)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FragmentRvCocktailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(list[position], holder)
    }

    override fun getItemCount(): Int = list.size


}
