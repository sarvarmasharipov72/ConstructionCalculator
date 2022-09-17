package com.example.constructioncalculator.ui.jobs.calculator.one

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.example.constructioncalculator.R
import com.example.constructioncalculator.databinding.FragmentOneBinding
import com.example.constructioncalculator.model.Jobs
import com.example.constructioncalculator.model.Material

class OneFragment : DialogFragment() {

    private val navArgs by navArgs<OneFragmentArgs>()
    private var binding: FragmentOneBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initView(inflater.inflate(R.layout.fragment_one, container, false))
        binding?.calcJobBtn?.setOnClickListener {
            val measurement = binding?.oneParam?.text.toString().toInt()
            calcBtnClick(measurement, navArgs.job.materialList)
        }
        return binding?.root
    }

    private fun calcBtnClick(measurement: Int, materialList: List<Material>) {
        val stringBuilder = StringBuilder()
        var sum = 0.0
        for (it in materialList) {
            val measurement1 = it.percent * measurement / 100.0
            val price = measurement1 * it.price
            stringBuilder.append("${it.name} = $measurement1 ${it.measurement} $price so'm\n")
            sum += price
        }
        stringBuilder.append("Ustalarga ish haqi = ${navArgs.job.price * measurement} so'm \nJami = ${sum + navArgs.job.price * measurement}")
        binding?.receptionOne?.text = stringBuilder.toString()
    }

    override fun onStart() {
        super.onStart()
        setData(navArgs.job)
    }

    private fun setData(job: Jobs) {
        binding?.oneParam?.hint = job.measurement
    }

    private fun initView(view: View) {
        binding = FragmentOneBinding.bind(view)

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }


}