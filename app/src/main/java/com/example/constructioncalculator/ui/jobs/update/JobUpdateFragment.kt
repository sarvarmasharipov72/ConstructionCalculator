package com.example.constructioncalculator.ui.jobs.update

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.constructioncalculator.MainApp
import com.example.constructioncalculator.R
import com.example.constructioncalculator.adapter.InsertMaterialAdapter
import com.example.constructioncalculator.databinding.JobLayoutBinding
import com.example.constructioncalculator.model.Jobs
import com.example.constructioncalculator.ui.base.BaseFragment
import javax.inject.Inject

class JobUpdateFragment : BaseFragment<JobLayoutBinding>(JobLayoutBinding::inflate) {

    private lateinit var viewModel: JobUpdateViewModel
    private val navArgs by navArgs<JobUpdateFragmentArgs>()
    private var adapter: InsertMaterialAdapter? = null

    @Inject
    lateinit var factory: JobUpdateViewModelFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as MainApp).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setData(navArgs.job)

        binding.jobsSaveBtn.setOnClickListener {
            saveButtonClick()
        }
    }

    private fun saveButtonClick() {
        val jobs = Jobs(
            id = navArgs.job.id,
            name = binding.jobName.text.toString(),
            measurement = binding.measurementJob.text.toString(),
            price = binding.jobPrice.text.toString().toInt(),
            materialList = navArgs.job.materialList,
            count = when (binding.radioGroup.checkedRadioButtonId) {
                R.id.radioButton -> 1
                R.id.radioButton2 -> 2
                else -> 3
            }
        )
        viewModel.updateJob(
            jobs
        )
        findNavController().navigate(JobUpdateFragmentDirections.actionJobUpdateFragmentToJobReadFragment(jobs))
    }

    private fun setData(job: Jobs) {
        binding.jobName.text?.append(job.name)
        binding.measurementJob.text?.append(job.measurement)
        binding.jobPrice.text?.append(job.price.toString())
        binding.radioGroup.check(
            when (job.count) {
                1 -> R.id.radioButton
                2 -> R.id.radioButton2
                else -> R.id.radioButton3
            }
        )
        adapter?.setData(job.materialList.map { it.name })
    }

    private fun initView() {
        viewModel = ViewModelProvider(this, factory)[JobUpdateViewModel::class.java]
        adapter = InsertMaterialAdapter()
        binding.jobsAddRecycler.adapter = adapter
        binding.jobsAddRecycler.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroy() {
        super.onDestroy()
        adapter = null
    }

}