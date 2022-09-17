package com.example.constructioncalculator.ui.projects.insert.add

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.constructioncalculator.MainApp
import com.example.constructioncalculator.R
import com.example.constructioncalculator.databinding.AddMaterialToJobBinding
import com.example.constructioncalculator.ui.base.BaseBottomSheetDialog
import com.example.constructioncalculator.ui.projects.insert.ShareData
import javax.inject.Inject

class AddJobToProjectFragment :
    BaseBottomSheetDialog<AddMaterialToJobBinding>(AddMaterialToJobBinding::inflate) {

    @Inject
    lateinit var factory: AddJobToProjectViewModelFactory
    private lateinit var viewModel: AddJobToProjectViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as MainApp).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, factory)[AddJobToProjectViewModel::class.java]
    }
    override fun onStart() {
        super.onStart()
        viewModel.readAllJobs.observe(viewLifecycleOwner) { list ->
            if (list != null) {
                setAdapter(
                    ArrayAdapter(
                        requireContext(),
                        android.R.layout.simple_list_item_1,
                        list.map { it.name })
                )
            }
        }
        binding.spinner.onItemSelectedListener = itemSelect()
        binding.selectBtn.setOnClickListener {
            shareDate(binding.spinner.selectedItemPosition ?: 0)
        }
        binding.count.visibility = View.GONE
    }

    private fun shareDate(id: Int) {
        val jobsModel = viewModel.readAllJobs.value?.get(id)!!
        val measurement = when (jobsModel.count) {
            1 -> {
                binding.threeParamOneAdd.text.toString().toInt()
            }
            2 -> {
                binding.threeParamOneAdd.text.toString()
                    .toInt() + binding.threeParamTwoAdd.text.toString().toInt()
            }
            else -> {
                binding.threeParamOneAdd.text.toString()
                    .toInt() + binding.threeParamTwoAdd.text.toString()
                    .toInt() + binding.threeParamThreeAdd.text.toString().toInt()
            }
        }
        var price = 0
        for (it in jobsModel.materialList)
            price += it.percent * it.price * measurement
        price += jobsModel.price * measurement
        ShareData.price = price
        ShareData.list.add(jobsModel)
        findNavController().navigate(R.id.action_projectAddToJob_to_projectInsertFragment)
    }

    private fun setAdapter(adapter: ArrayAdapter<String>) {
        binding.spinner.adapter = adapter
        Toast.makeText(requireContext(), "run", Toast.LENGTH_LONG).show()
    }

    private fun itemSelect(): AdapterView.OnItemSelectedListener? {
        return object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val jobsModel = viewModel.readAllJobs.value?.get(position)
                if (jobsModel?.count!! >= 1) {
                    binding.threeParamOneAdd.visibility = View.VISIBLE
                    binding.threeParamTwoAdd.visibility = View.GONE
                    binding.threeParamThreeAdd.visibility = View.GONE
                }
                if (jobsModel.count >= 2) {
                    binding.threeParamTwoAdd.visibility = View.VISIBLE
                    binding.threeParamThreeAdd.visibility = View.GONE
                }
                if (jobsModel.count >= 3) {
                    binding.threeParamThreeAdd.visibility = View.VISIBLE
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}


        }

    }
}