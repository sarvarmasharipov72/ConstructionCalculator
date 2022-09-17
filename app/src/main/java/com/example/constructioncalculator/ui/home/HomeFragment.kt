package com.example.constructioncalculator.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.constructioncalculator.MainApp
import com.example.constructioncalculator.R
import com.example.constructioncalculator.databinding.HomeFragmentBinding
import com.example.constructioncalculator.ui.base.BaseFragment

class HomeFragment : BaseFragment<HomeFragmentBinding>(HomeFragmentBinding::inflate) {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireContext().applicationContext as MainApp).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.jobsButton.setOnClickListener {
            jobsButtonClicked()
        }

        binding.projectButton.setOnClickListener {
            projectButtonClicked()
        }

        binding.materialButton.setOnClickListener {
            materialButtonClicked()
        }
    }
    private fun materialButtonClicked() {
        findNavController().navigate(R.id.action_homeFragment_to_materialFragment)
    }

    private fun projectButtonClicked() {
        findNavController().navigate(R.id.action_homeFragment_to_projectFragment)
    }

    private fun jobsButtonClicked() {
        findNavController().navigate(R.id.action_homeFragment_to_jobsFragment)
    }

}