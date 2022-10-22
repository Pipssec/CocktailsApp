package com.example.cocktailsapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.cocktailsapp.databinding.FragmentIngredientBinding
import com.example.cocktailsapp.ViewModel
import org.koin.android.ext.android.inject

class FindIngredientInfoFragment : Fragment() {
    private val viewModel by inject<ViewModel>()
    private lateinit var binding: FragmentIngredientBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentIngredientBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnSearchIngredient.setOnClickListener {
            val name = binding.itIngredient.text.toString()
            viewModel.getResultByIngredient(name)
        }
        viewModel.resultIngredientX.observe(viewLifecycleOwner, Observer { it ->
            binding.tvNameIgredient.text = it?.get(0)?.strIngredient.toString()
            binding.tvInfoIngredient.text = it?.get(0)?.strDescription.toString()
        })
    }

}