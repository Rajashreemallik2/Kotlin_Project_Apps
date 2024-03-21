package com.example.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.a7minutesworkout.databinding.ActivityBmiBinding
import java.math.BigDecimal
import java.math.RoundingMode

class BMIActivity : AppCompatActivity() {

    companion object{
        private const val METRIC_UNITS_VIEW ="METRIC_UNITS_VIEW"
        private const val US_UNITS_VIEW = "US_UNITS_VIEW"
    }

    private  var currentVisibleView: String = METRIC_UNITS_VIEW

    private var binding: ActivityBmiBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarBmiActivity)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "CALCULATE BMI"
        }

        binding?.toolbarBmiActivity?.setNavigationOnClickListener {
            onBackPressed()
        }
        metricVisibleUnitsView()

        binding?.rgUnits?.setOnCheckedChangeListener { _, checkedId: Int ->
            if (checkedId == R.id.rbMetricUnits) {
                metricVisibleUnitsView()
            } else {
                metricVisibleUSUnitsView()
            }
        }

        binding?.btnCalculateUnits?.setOnClickListener {

            calculateUnits()

//            if (validateMetricUnits()){
//                val heightValue: Float = binding?.etUnitHeight?.text.toString().toFloat() / 100
//                val weightValue: Float = binding?.etUnitWeight?.text.toString().toFloat()
//
//                val bmi = weightValue / (heightValue * heightValue)
//                displayBMIResults(bmi)
//            }else{
//                Toast.makeText(this@BMIActivity,
//                    "Please Enter Valid Values.",
//                     Toast.LENGTH_LONG)
//                     .show()
//            }
       }
    }

    private fun metricVisibleUnitsView(){
        currentVisibleView = METRIC_UNITS_VIEW
        binding?.tilMetricUnitWeight?.visibility = View.VISIBLE
        binding?.tilMetricUnitHeight?.visibility = View.VISIBLE
        binding?.tilUSMetricUnitWeight?.visibility = View.GONE
        binding?.tilMetricUsUnitHeightInch?.visibility = View.GONE
        binding?.tilMetricUsUnitHeightFeet?.visibility = View.GONE

        binding?.etUnitWeight?.text!!.clear()
        binding?.etUnitHeight?.text!!.clear()

        binding?.llDisplayBMIResult?.visibility = View.INVISIBLE

    }
    private fun metricVisibleUSUnitsView(){
        currentVisibleView = METRIC_UNITS_VIEW
        binding?.tilMetricUnitWeight?.visibility = View.INVISIBLE
        binding?.tilMetricUnitHeight?.visibility = View.INVISIBLE
        binding?.tilUSMetricUnitWeight?.visibility = View.VISIBLE
        binding?.tilMetricUsUnitHeightInch?.visibility = View.VISIBLE
        binding?.tilMetricUsUnitHeightFeet?.visibility = View.VISIBLE

        binding?.etUsMetricUnitHeightFeet?.text!!.clear()
        binding?.etUSMetricUnitWeight?.text!!.clear()
        binding?.etUsMetricUnitHeightInch?.text!!.clear()

        binding?.llDisplayBMIResult?.visibility = View.INVISIBLE

    }

    private fun displayBMIResults(bmi: Float){

        val bmiLabel: String
        val bmiDescription: String

        if (bmi.compareTo(15f) <= 0){
            bmiLabel = "Very Severely Underweight"
            bmiDescription = "Oops! You nearly need take better care of yourself! Eat  "
        }else if (bmi.compareTo(15f) > 0 && bmi.compareTo(16f) <= 0){
            bmiLabel = "Severely underweight"
            bmiDescription = "Oops! You nearly need take better care of yourself! Eat  "
        }else if (bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) <= 0){
            bmiLabel = "Underweight"
            bmiDescription = "Oops! You nearly need take better care of yourself! Eat  "
        }else if (bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) <= 0){
            bmiLabel = "Normal"
            bmiDescription = "Congratulations! You are in a good shape"
        } else if (java.lang.Float.compare(bmi, 25f) > 0 && java.lang.Float.compare(bmi, 30f) <= 0) {
            bmiLabel = "Overweight"
            bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
        } else if (bmi.compareTo(30f) > 0 && bmi.compareTo(35f) <= 0
        ) {
            bmiLabel = "Obese Class | (Moderately obese)"
            bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
        } else if (bmi.compareTo(35f) > 0 && bmi.compareTo(40f) <= 0
        ) {
            bmiLabel = "Obese Class || (Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        } else {
            bmiLabel = "Obese Class ||| (Very Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        }

        val bmiValue = BigDecimal(bmi.toDouble())
                       .setScale(2, RoundingMode.HALF_EVEN).toString()

        binding?.llDisplayBMIResult?.visibility = View.VISIBLE
        binding?.tvBMIValue?.text = bmiValue
        binding?.tvBMIType?.text = bmiLabel
        binding?.tvBMIDescription?.text = bmiDescription

    }

    private fun validateMetricUnits(): Boolean{
        var isValid = true
        if (binding?.etUnitWeight?.text.toString().isEmpty()){
            isValid = false
        }else if(binding?.etUnitHeight?.text.toString().isEmpty()){
            isValid = false
        }
        return isValid
    }

    private fun calculateUnits(){
        if (currentVisibleView == METRIC_UNITS_VIEW){
            if (validateMetricUnits()){
                val heightValue: Float = binding?.etUnitHeight?.text.toString().toFloat() / 100
                val weightValue: Float = binding?.etUnitWeight?.text.toString().toFloat()

                val bmi = weightValue / (heightValue * heightValue)
                displayBMIResults(bmi)
            }else{
                Toast.makeText(this@BMIActivity,
                    "Please Enter Valid Values.",
                    Toast.LENGTH_LONG)
                    .show()
            }
        }else
            if (validateUsUnits()){

            val usUnitHeightValueFeet: String = binding?.etUsMetricUnitHeightFeet?.text.toString()
            val usMetricUnitWeight: Float = binding?.etUSMetricUnitWeight?.text.toString().toFloat()
            val usMetricUnitHeightInch: String = binding?.etUsMetricUnitHeightInch?.text.toString()

            val heightValue = usMetricUnitHeightInch.toFloat() + usUnitHeightValueFeet.toFloat() * 12
            val bmi = 703 * (usMetricUnitWeight / ( heightValue * heightValue))

            displayBMIResults(bmi)

        }else{
            Toast.makeText(this@BMIActivity,
                "Please Enter Valid Values.",
                Toast.LENGTH_LONG)
                .show()
        }
    }


    private fun validateUsUnits(): Boolean{
        var isValid = true
        when{
             binding?.etUSMetricUnitWeight?.text.toString().isEmpty() ->
                isValid = false
             binding?.etUsMetricUnitHeightFeet?.text.toString().isEmpty() ->
                isValid = false
            binding?.etUsMetricUnitHeightInch?.text.toString().isEmpty() ->
                isValid = false
            }
                return isValid
        }
}
