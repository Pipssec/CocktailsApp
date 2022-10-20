package com.example.cocktailsapp.startfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.example.cocktailsapp.R
import com.example.cocktailsapp.databinding.FragmentStartBinding

class StartFragment: Fragment() {

    private lateinit var binding: FragmentStartBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val picture = binding.ivShakerPicture
        AnimationUtils.loadAnimation(context,R.anim.my_animation).also {
            picture.startAnimation(it)
        }
    }
}