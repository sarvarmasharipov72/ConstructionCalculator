package com.example.constructioncalculator.ui.jobs

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.constructioncalculator.MainApp
import com.example.constructioncalculator.utils.Constants
import com.example.constructioncalculator.R
import com.example.constructioncalculator.adapter.UiAdapter
import com.example.constructioncalculator.databinding.JobsFragmentBinding
import com.example.constructioncalculator.ui.base.BaseFragment
import javax.inject.Inject

class JobsFragment : BaseFragment<JobsFragmentBinding>(JobsFragmentBinding::inflate) {

    private lateinit var viewModel: JobsViewModel
    private var recyclerView: RecyclerView? = null

    @Inject
    lateinit var factory: JobsViewModelFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as MainApp).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, factory)[JobsViewModel::class.java]

        binding.fabJobs.setOnClickListener {
            fabClicked()
        }

        val adapter = UiAdapter(Constants.JOBS)
        recyclerView = binding.jobsRecycler

        recyclerView?.adapter = adapter

        recyclerView?.layoutManager = LinearLayoutManager(requireContext())

        viewModel.jobs.observe(viewLifecycleOwner) {
            if (it.isNotEmpty())
                adapter.setJobs(it)
        }
    }

    private fun fabClicked() {
        findNavController().navigate(R.id.action_jobsFragment_to_insertJobFragment)
    }


}