package com.example.constructioncalculator.ui.materials.update

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.constructioncalculator.MainApp
import com.example.constructioncalculator.databinding.MaterialLayoutBinding
import com.example.constructioncalculator.model.Material
import com.example.constructioncalculator.ui.base.BaseBottomSheetDialog
import javax.inject.Inject

class MaterialUpdateFragment :
    BaseBottomSheetDialog<MaterialLayoutBinding>(MaterialLayoutBinding::inflate) {
    private val navArgs by navArgs<MaterialUpdateFragmentArgs>()

    private lateinit var viewModel: MaterialUpdateViewModel

    @Inject
    lateinit var factory: MaterialUpdateViewModelFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as MainApp).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

        updateData(navArgs.material)

        binding.save.setOnClickListener {
            updateMaterial()
        }
    }

    private fun updateData(materialUpdate: Material) {
        binding.nameMaterial.text?.append(materialUpdate.name)
        binding.measurementMaterial.text?.append(materialUpdate.measurement)
        binding.priceMaterial.text?.append(materialUpdate.price.toString())
    }

    private fun updateMaterial() {
        viewModel.updateMaterial(
            id = navArgs.material.id,
            name = binding.nameMaterial.text?.toString() ?: "",
            measurement = binding.measurementMaterial.text?.toString() ?: "",
            price = binding.priceMaterial.text.toString().toInt()
        )
        findNavController().navigate(
            MaterialUpdateFragmentDirections.actionMaterialUpdateFragmentToMaterialReadFragment(
                Material(
                    navArgs.material.id,
                    binding.nameMaterial.text.toString(),
                    binding.measurementMaterial.text.toString(),
                    binding.priceMaterial.text.toString().toInt()
                )
            )
        )
    }

    private fun initView() {
        viewModel = ViewModelProvider(this, factory)[MaterialUpdateViewModel::class.java]
    }
}