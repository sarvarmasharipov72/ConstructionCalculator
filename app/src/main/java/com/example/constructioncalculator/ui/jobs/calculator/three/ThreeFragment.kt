package com.example.constructioncalculator.ui.jobs.calculator.three

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.constructioncalculator.R
import com.example.constructioncalculator.databinding.FragmentThreeBinding
import com.example.constructioncalculator.model.Material

class ThreeFragment : Fragment() {

    private var binding: FragmentThreeBinding? = null
    private val navArgs by navArgs<ThreeFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initView(inflater.inflate(R.layout.fragment_three, container, false))
        binding?.calcThreeJobBtn?.setOnClickListener {
            calcThreeBtnClick(navArgs.job.materialList)
        }
        return binding?.root
    }

    override fun onStart() {
        super.onStart()
        setDate()
    }

    private fun setDate() {
        binding?.threeParamOne?.hint = "1-o'lcham"
        binding?.threeParamTwo?.hint = "2-o'lcham"
        binding?.threeParamThree?.hint = "3-o'lcham"
    }

    private fun calcThreeBtnClick(materialList: List<Material>) {
        val measurement = binding?.threeParamOne?.text.toString()
            .toInt() * binding?.threeParamTwo?.text.toString()
            .toInt() * binding?.threeParamThree?.text.toString().toInt()
        val stringBuilder = StringBuilder()
        var sum = 0.0
        for (it in materialList) {
            val measurement1 = it.percent * measurement / 100.0
            val price = measurement1 * it.price
            stringBuilder.append("${it.name} = $measurement1 ${it.measurement} $price so'm\n")
            sum += price
        }
        stringBuilder.append("Ustalarga ish haqi = ${navArgs.job.price * measurement} so'm \nJami = ${sum + navArgs.job.price * measurement}")
        binding?.receptionThree?.text = stringBuilder.toString()
    }

    private fun initView(view: View) {
        binding = FragmentThreeBinding.bind(view)
    }

}