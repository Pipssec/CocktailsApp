package com.example.cocktailsapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.cocktailsapp.R
import com.example.cocktailsapp.databinding.FragmentFindbynameBinding
import com.example.cocktailsapp.model.model.drink.DrinkAdapter
import com.example.cocktailsapp.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject


class FindByNameFragment : Fragment() {
    private val viewModel by inject<ViewModel>()
    lateinit var binding: FragmentFindbynameBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentFindbynameBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnSearchByName.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val name = binding.itCocktail.text.toString()
                viewModel.getResultByName(name)
            }
        }
        val rvCocktails = binding.rvCocktail

        viewModel.resultDrink.observe(viewLifecycleOwner) { list ->
            val adapter = list?.let {
                DrinkAdapter(it) { drink ->
                    viewModel.select(drink)
                    activity?.supportFragmentManager?.beginTransaction()
                        ?.addToBackStack(null)
                        ?.replace(R.id.activity_container, DrinkFragment())
                        ?.commit()
                }
            }
            rvCocktails.adapter = adapter
        }
    }
}