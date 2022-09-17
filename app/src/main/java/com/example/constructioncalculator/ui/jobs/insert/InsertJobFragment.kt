package com.example.constructioncalculator.ui.jobs.insert

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.constructioncalculator.MainApp
import com.example.constructioncalculator.R
import com.example.constructioncalculator.adapter.InsertMaterialAdapter
import com.example.constructioncalculator.databinding.JobLayoutBinding
import com.example.constructioncalculator.model.Material
import com.example.constructioncalculator.ui.base.BaseFragment
import javax.inject.Inject

class InsertJobFragment : BaseFragment<JobLayoutBinding>(JobLayoutBinding::inflate) {

    private lateinit var viewModel: InsertJobViewModel
    private lateinit var list: MutableList<Material>
    private lateinit var adapter: InsertMaterialAdapter

    @Inject
    lateinit var factory: InsertJobViewModelFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as MainApp).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        binding.jobsSaveBtn.setOnClickListener {
            if (binding.jobName.text?.isNotBlank()!! && binding.jobPrice.text?.isNotBlank()!! && binding.measurementJob.text?.isNotBlank()!!) {
                val id = when (binding.radioGroup.checkedRadioButtonId) {
                    R.id.radioButton -> 1
                    R.id.radioButton2 -> 2
                    else -> 3
                }
                saveJobs(id)
            }
        }
        binding.materialAdd.setOnClickListener {
            addMaterial()
        }
    }

    override fun onResume() {
        super.onResume()
        binding.jobName.text?.append(SharedData.name)
        binding.jobPrice.text?.append(SharedData.price)
        binding.measurementJob.text?.append(SharedData.measurement)
        binding.radioGroup.check(
            when (SharedData.id) {
                1 -> R.id.radioButton
                2 -> R.id.radioButton2
                else -> R.id.radioButton3
            }
        )

    }

    private fun addMaterial() {
        if (SharedData.counts > 0) {
            SharedData.name = binding.jobName.text.toString()
            SharedData.measurement = binding.measurementJob.text.toString()
            SharedData.price = binding.jobPrice.text.toString()
            SharedData.id = when (binding.radioGroup.checkedRadioButtonId) {
                R.id.radioButton3 -> 3
                R.id.radioButton2 -> 2
                else -> 1
            }
            SharedData.list = list

            findNavController().navigate(
                InsertJobFragmentDirections.actionInsertJobFragmentToAddMaterialFragment(
                    SharedData.counts.toInt()
                )
            )
        } else
            Toast.makeText(requireContext(), "boshqa material qo'sha olmaysiz", Toast.LENGTH_SHORT)
                .show()
    }

    override fun onStart() {
        super.onStart()
        binding.jobsAddRecycler.adapter = adapter
        binding.jobsAddRecycler.layoutManager = LinearLayoutManager(requireContext())

        adapter.setData(list.map { it.name })

    }


    private fun saveJobs(id: Int) {

        SharedData.sharedData = null
        SharedData.count = 0
        SharedData.counts = 100

        SharedData.name = ""
        SharedData.measurement = ""
        SharedData.price = ""
        SharedData.id = 1
        SharedData.list = mutableListOf()

        viewModel.insertJob(
            name = binding.jobName.text?.toString() ?: "",
            price = binding.jobPrice.text?.toString()?.toInt() ?: 0,
            measurement = binding.measurementJob.text.toString(),
            materialList = list,
            count = id
        )
        findNavController().navigate(R.id.action_insertJobFragment_to_jobsFragment)
    }


    private fun initView() {
        adapter = InsertMaterialAdapter()
        viewModel = ViewModelProvider(this, factory)[InsertJobViewModel::class.java]
        SharedData.counts = (SharedData.counts - SharedData.count).toByte()
        val nameS = SharedData.sharedData
        list = if (SharedData.list.isEmpty()) mutableListOf() else SharedData.list

        if (nameS != null) {
            nameS.percent = SharedData.count
            list.add(nameS); SharedData.sharedData = null
        }
    }
}