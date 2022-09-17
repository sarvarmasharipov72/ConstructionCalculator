package com.example.constructioncalculator.ui.projects.read

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.constructioncalculator.MainApp
import com.example.constructioncalculator.R
import com.example.constructioncalculator.adapter.InsertMaterialAdapter
import com.example.constructioncalculator.databinding.ProjectReadFragmentBinding
import com.example.constructioncalculator.model.Project
import com.example.constructioncalculator.ui.base.BaseBottomSheetDialog
import javax.inject.Inject

class ProjectReadFragment :
    BaseBottomSheetDialog<ProjectReadFragmentBinding>(ProjectReadFragmentBinding::inflate) {

    private lateinit var viewModel: ProjectReadViewModel
    private val navArgs by navArgs<ProjectReadFragmentArgs>()
    private var adapter: InsertMaterialAdapter? = null

    @Inject
    lateinit var factory: ProjectReadViewModelFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as MainApp).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, factory)[ProjectReadViewModel::class.java]
        binding.initView()
    }
    private fun deleteProject(id: Int) {
        viewModel.deleteProject(id)
        findNavController().navigate(R.id.action_projectReadDialog_to_projectFragment)
    }

    private fun ProjectReadFragmentBinding.bindData(project: Project) {
        binding.projectNameRead.text = project.name
        binding.priceProjectRead.text = project.price.toString()
        adapter?.setData(project.jobList.map { it.name })
    }

    private fun ProjectReadFragmentBinding.initView() {
        adapter = InsertMaterialAdapter()
        binding.projectRecyclerRead.adapter = adapter
        binding.projectRecyclerRead.layoutManager = LinearLayoutManager(requireContext())
        binding.projectDelete.setOnClickListener {
            deleteProject(navArgs.project.id)
        }
        bindData(navArgs.project)
    }
}