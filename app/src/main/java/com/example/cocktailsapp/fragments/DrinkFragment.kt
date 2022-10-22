package com.example.cocktailsapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cocktailsapp.R
import com.example.cocktailsapp.databinding.FragmentDrinkBinding
import com.example.cocktailsapp.ViewModel
import com.squareup.picasso.Picasso
import org.koin.android.ext.android.inject

class DrinkFragment: Fragment() {
    private val viewModel by inject<ViewModel>()
    private lateinit var binding: FragmentDrinkBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDrinkBinding.inflate(layoutInflater, container, false)
        viewModel.selected.observe(viewLifecycleOwner) {
            binding.tvItemDrinkName.text = it.strDrink
            binding.tvItemdescription.text = it.strInstructions
            Picasso.with(context)
                .load(it.strDrinkThumb)
                .fit()
                .placeholder(R.drawable.ic_baseline_error_24)
                .error(R.drawable.ic_baseline_error_24)
                .into(binding.ivItemDrink)
        }
        return binding.root
    }

}
