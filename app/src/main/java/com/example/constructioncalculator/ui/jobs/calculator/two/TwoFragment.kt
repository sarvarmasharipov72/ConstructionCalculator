package com.example.constructioncalculator.ui.jobs.calculator.two

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.constructioncalculator.R
import com.example.constructioncalculator.databinding.FragmentTwoBinding
import com.example.constructioncalculator.model.Jobs
import com.example.constructioncalculator.model.Material

class TwoFragment : Fragment() {

    private var binding: FragmentTwoBinding? = null
    private val navArgs by navArgs<TwoFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        initView(inflater.inflate(R.layout.fragment_two, container, false))
        binding?.calcTwoJobBtn?.setOnClickListener {
            calcTwoBtnClick(navArgs.job.materialList)
        }
        return binding?.root
    }

    override fun onStart() {
        super.onStart()

        setData()
    }

    private fun setData() {
        binding?.twoParamOne?.hint = "1 - o'lchami"
        binding?.twoParamTwo?.hint = "2 - o'lchami"
    }

    private fun calcTwoBtnClick(materialList: List<Material>) {
        val measurement =
            binding?.twoParamOne?.text.toString().toInt() * binding?.twoParamTwo?.text.toString()
                .toInt()
        val stringBuilder = StringBuilder()
        var sum = 0.0
        for (it in materialList) {
            val measurement1 = it.percent * measurement / 100.0
            val price = measurement1 * it.price
            stringBuilder.append("${it.name} = $measurement1 ${it.measurement} $price so'm\n")
            sum += price
        }
        stringBuilder.append("Ustalarga ish haqi = ${navArgs.job.price * measurement} so'm \nJami = ${sum + navArgs.job.price * measurement}")
        binding?.receptionTwo?.text = stringBuilder.toString()
    }

    private fun initView(view: View) {
        binding = FragmentTwoBinding.bind(view)

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}