package com.example.constructioncalculator.ui.projects

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.constructioncalculator.MainApp
import com.example.constructioncalculator.utils.Constants
import com.example.constructioncalculator.R
import com.example.constructioncalculator.adapter.UiAdapter
import com.example.constructioncalculator.databinding.ProjectsFragmentBinding
import com.example.constructioncalculator.ui.base.BaseFragment
import javax.inject.Inject

class ProjectFragment : BaseFragment<ProjectsFragmentBinding>(ProjectsFragmentBinding::inflate) {

    private lateinit var viewModel: ProjectsViewModel
    private var adapter: UiAdapter? = null

    @Inject
    lateinit var factory: ProjectsViewModelFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as MainApp).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onStart() {
        super.onStart()
        binding.fabProjects.setOnClickListener {
            findNavController().navigate(R.id.action_projectFragment_to_projectInsertFragment)
        }
    }

    private fun initView() {
        viewModel = ViewModelProvider(this, factory)[ProjectsViewModel::class.java]
        adapter = UiAdapter(Constants.PROJECT)
        binding.projectRecycler.adapter = adapter
        binding.projectRecycler.layoutManager = LinearLayoutManager(requireContext())
        viewModel.project.observe(viewLifecycleOwner) {
            if (it != null) {
                adapter?.setProject(it)
            }
        }

    }
}