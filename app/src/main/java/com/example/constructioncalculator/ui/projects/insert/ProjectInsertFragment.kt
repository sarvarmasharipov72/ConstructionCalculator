package com.example.constructioncalculator.ui.projects.insert

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.constructioncalculator.MainApp
import com.example.constructioncalculator.R
import com.example.constructioncalculator.adapter.InsertMaterialAdapter
import com.example.constructioncalculator.databinding.ProjectInsertFragmentBinding
import com.example.constructioncalculator.model.Jobs
import com.example.constructioncalculator.ui.base.BaseBottomSheetDialog
import java.util.*
import javax.inject.Inject

class ProjectInsertFragment : BaseBottomSheetDialog<ProjectInsertFragmentBinding>(ProjectInsertFragmentBinding::inflate) {

    private lateinit var viewModel: ProjectInsertViewModel
    private lateinit var adapter: InsertMaterialAdapter

    @Inject
    lateinit var factory: ProjectInsertViewModelFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as MainApp).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        binding.addJob.setOnClickListener {
            addButtonClicked()
        }
        binding.projectSave.setOnClickListener {
            val name = binding.projectInsertName.text.toString()
            val calendar = Calendar.getInstance()
            if (binding.projectInsertName.text?.isNotBlank() == true) {
                saveBtnClick(
                    name,
                    ShareData.price,
                    "${calendar.get(Calendar.DAY_OF_MONTH)}.${calendar.get(Calendar.MONTH) + 1}.${
                        calendar.get(
                            Calendar.YEAR
                        )
                    }",
                    ShareData.list
                )
            } else {
                Toast.makeText(requireContext(), "Iltimos nomini kiriting", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        setData(ShareData.name, ShareData.list)
    }

    private fun setData(name: String, list: MutableList<Jobs>) {
        binding.projectInsertName.text?.append(name)

        adapter.setData(list.map { it.name })
    }

    private fun saveBtnClick(
        name: String,
        price: Int,
        date: String,
        list: MutableList<Jobs>
    ) {
        viewModel.insertProject(
            name = name,
            price = price,
            date = date,
            jobList = list
        )
        ShareData.price = 0
        ShareData.name = ""
        ShareData.list = mutableListOf()
        findNavController().navigate(R.id.action_projectInsertFragment_to_projectFragment)
    }

    private fun addButtonClicked() {
        findNavController().navigate(R.id.action_projectInsertFragment_to_projectAddToJob)
    }

    private fun initView() {
        viewModel = ViewModelProvider(this, factory)[ProjectInsertViewModel::class.java]
        adapter = InsertMaterialAdapter()
        binding.projectRecycler.adapter = adapter
        binding.projectRecycler.layoutManager = LinearLayoutManager(requireContext())
    }
}