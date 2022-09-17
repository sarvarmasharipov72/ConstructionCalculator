package com.example.constructioncalculator.ui.materials.insert

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.constructioncalculator.MainApp
import com.example.constructioncalculator.R
import com.example.constructioncalculator.databinding.MaterialLayoutBinding
import com.example.constructioncalculator.ui.base.BaseBottomSheetDialog
import javax.inject.Inject

class InsertMaterialFragment :
    BaseBottomSheetDialog<MaterialLayoutBinding>(MaterialLayoutBinding::inflate) {

    private lateinit var viewModel: InsertMaterialViewModel

    @Inject
    lateinit var factory: InsertMaterialViewModelFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as MainApp).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, factory)[InsertMaterialViewModel::class.java]

        binding.save.setOnClickListener {
            addButtonClicked()
        }
    }

    private fun addButtonClicked() {
        if (binding.nameMaterial.text?.isNotBlank() == true && binding.measurementMaterial.text?.isNotBlank() == true && binding.priceMaterial.text?.isNotBlank() == true) {
            viewModel.insertMaterial(
                name = binding.nameMaterial.text.toString(),
                measurement = binding.measurementMaterial.text.toString(),
                price = binding.priceMaterial.text.toString().toInt()
            )
            findNavController().navigate(R.id.action_insertDialogFragment_to_materialFragment)
        } else {
            Toast.makeText(requireContext(), "Iltimos ma'lumotlarni kiriting", Toast.LENGTH_SHORT)
                .show()
        }
    }
}