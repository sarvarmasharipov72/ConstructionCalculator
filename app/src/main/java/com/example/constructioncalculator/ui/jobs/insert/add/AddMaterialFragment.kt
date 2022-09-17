package com.example.constructioncalculator.ui.jobs.insert.add

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.constructioncalculator.MainApp
import com.example.constructioncalculator.R
import com.example.constructioncalculator.databinding.AddMaterialToJobBinding
import com.example.constructioncalculator.ui.base.BaseFragment
import com.example.constructioncalculator.ui.jobs.insert.SharedData
import javax.inject.Inject

class AddMaterialFragment :
    BaseFragment<AddMaterialToJobBinding>(AddMaterialToJobBinding::inflate) {
    private lateinit var viewModel: AddMaterialViewModel
    private val navArgs by navArgs<AddMaterialFragmentArgs>()

    @Inject
    lateinit var factory: AddMaterialViewModelFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as MainApp).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, factory)[AddMaterialViewModel::class.java]

    }

    override fun onStart() {
        super.onStart()

        binding.count.hint = "0 - ${navArgs.precent}"
        viewModel.nameList.observe(this) { res ->
            if (res.isNotEmpty()) {
                setAdapter(
                    ArrayAdapter(
                        requireContext(),
                        android.R.layout.simple_list_item_1,
                        res.map { it.name })
                )
            }

        }


    }

    override fun onResume() {
        super.onResume()
        binding.selectBtn.setOnClickListener {
            selectMaterial()
        }
    }

    private fun selectMaterial() {
        if (binding.count.text?.isNotBlank() == true) {
            SharedData.sharedData =
                viewModel.nameList.value?.get(binding.spinner.selectedItemPosition)
            SharedData.count = binding.count.text?.toString()?.toByte() ?: 0
            findNavController().navigate(R.id.action_addMaterialFragment_to_insertJobFragment)
        }
    }

    private fun setAdapter(adapter: ArrayAdapter<String>) {
        binding.spinner.adapter = adapter
    }
}