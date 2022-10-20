package com.example.cocktailsapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.cocktailsapp.databinding.FragmentFindbynameBinding
import com.example.cocktailsapp.model.DrinkAdapter
import com.example.cocktailsapp.model.DrinkViewModel


class FindByNameFragment : Fragment() {
    private val viewModel: DrinkViewModel by viewModels()
    lateinit var binding: FragmentFindbynameBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentFindbynameBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnSearchByName.setOnClickListener {
            val name = binding.itCocktail.text.toString()
            viewModel.getResultByName(name)
        }
        val rv_cocktails = binding.rvCocktail
        viewModel.result.observe(viewLifecycleOwner, Observer { list ->
            val adapter = list?.let {
                DrinkAdapter(it) { drink ->
                    viewModel.select(drink)
                }
            }
            rv_cocktails.adapter = adapter
        })
    }

}