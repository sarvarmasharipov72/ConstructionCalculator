package com.example.constructioncalculator.ui.jobs.read

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.constructioncalculator.MainApp
import com.example.constructioncalculator.R
import com.example.constructioncalculator.databinding.FragmentJobReadBinding
import com.example.constructioncalculator.model.Jobs
import com.example.constructioncalculator.ui.base.BaseFragment
import javax.inject.Inject

class JobReadFragment : BaseFragment<FragmentJobReadBinding>(FragmentJobReadBinding::inflate) {

    private lateinit var viewModel: JobReadViewModel
    private val navArgs by navArgs<JobReadFragmentArgs>()

    @Inject
    lateinit var factory: JobReadViewModelFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as MainApp).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, factory)[JobReadViewModel::class.java]
        setData(navArgs.job)
        binding.jobCalcBtn.setOnClickListener {
            calcButtonClick(navArgs.job.count)
        }
        binding.jobEditBtn.setOnClickListener {
            editButtonClick()
        }
        binding.jobDelete.setOnClickListener {
            deleteButtonClick()
        }
    }
    private fun deleteButtonClick() {
        viewModel.deleteJob(navArgs.job.id)
        findNavController().navigate(R.id.action_jobReadFragment_to_jobsFragment)
    }

    private fun editButtonClick() {
        findNavController().navigate(
            JobReadFragmentDirections.actionJobReadFragmentToJobUpdateFragment(
                navArgs.job
            )
        )
    }

    private fun calcButtonClick(count: Int) = when (count) {
        1 -> findNavController().navigate(
            JobReadFragmentDirections.actionJobReadFragmentToOneFragment(
                navArgs.job
            )
        )
        2 -> findNavController().navigate(
            JobReadFragmentDirections.actionJobReadFragmentToTwoFragment(
                navArgs.job
            )
        )
        else -> findNavController().navigate(
            JobReadFragmentDirections.actionJobReadFragmentToThreeFragment(
                navArgs.job
            )
        )
    }

    private fun setData(job: Jobs) {
        binding.jobNameRead.text = job.name
        binding.measurementJobRead.text = job.measurement
        binding.priceJobRead.text = job.price.toString()
        binding.turiJob.text = job.count.toString()
    }
}