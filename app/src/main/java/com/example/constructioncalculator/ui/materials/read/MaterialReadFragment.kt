package com.example.constructioncalculator.ui.materials.read

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.constructioncalculator.MainApp
import com.example.constructioncalculator.R
import com.example.constructioncalculator.databinding.MaterialReadFragmentBinding
import com.example.constructioncalculator.ui.base.BaseFragment
import javax.inject.Inject

class MaterialReadFragment: BaseFragment<MaterialReadFragmentBinding>(MaterialReadFragmentBinding::inflate) {

    private val navArgs by navArgs<MaterialReadFragmentArgs>()
    private lateinit var viewModel: MaterialReadViewModel

    @Inject
    lateinit var factory: MaterialReadViewModelFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as MainApp).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, factory)[MaterialReadViewModel::class.java]

        binding.deleteMaterial.setOnClickListener {
            deleteMaterial()
        }

        binding.editMaterial.setOnClickListener {
            updateData()
        }
    }
    private fun updateData() {
        findNavController().navigate(MaterialReadFragmentDirections.actionMaterialReadFragmentToMaterialUpdateFragment(navArgs.material))
    }

    override fun onStart() {
        super.onStart()
        binding.materialReadName.text = navArgs.material.name
        binding.materialReadMeasurement.text = navArgs.material.measurement
        binding.materialReadPrice.text = navArgs.material.price.toString()
    }

    private fun deleteMaterial() {
        viewModel.deleteMaterial(navArgs.material.id)
        findNavController().navigate(R.id.action_materialReadFragment_to_materialFragment)
    }
}