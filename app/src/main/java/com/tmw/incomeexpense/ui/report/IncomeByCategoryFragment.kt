package com.tmw.incomeexpense.ui.report

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.tmw.incomeexpense.R
import com.tmw.incomeexpense.model.Income
import com.tmw.incomeexpense.ui.income.IncomeAdapter
import com.tmw.incomeexpense.ui.income.IncomeViewModel
import kotlinx.android.synthetic.main.fragment_income.*
import kotlinx.android.synthetic.main.fragment_income_by_category.*
import kotlinx.android.synthetic.main.fragment_income_by_category.view.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList


class IncomeByCategoryFragment : Fragment() {

    private lateinit var incomeViewModel:IncomeViewModel
    private lateinit var incomebycategoryAdapter:IncomeByCategoryAdapter

    var formatDate=SimpleDateFormat("dd/MM/yyyy")

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_income_by_category, container, false)

        incomebycategoryAdapter= IncomeByCategoryAdapter()

        view.recycler_category.apply {
            layoutManager=LinearLayoutManager(requireContext())
            adapter=incomebycategoryAdapter
        }

//        view.start_date_picker_actions.setOnClickListener {
//            val getDate=Calendar.getInstance()
//            val datePicker=DatePickerDialog(requireContext(),android.R.style.Theme_Holo_Dialog_MinWidth,DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->
//
//                val selectDate=Calendar.getInstance()
//                selectDate.set(Calendar.YEAR,i)
//                selectDate.set(Calendar.MONTH,i2)
//                selectDate.set(Calendar.DAY_OF_MONTH,i3)
//
//                val date=formatDate.format(selectDate.time)
//
//                view.txt_start_date.setText(date)
//
//            },getDate.get(Calendar.YEAR),getDate.get(Calendar.MONTH),getDate.get(Calendar.DAY_OF_MONTH))
//            datePicker.show()
//        }
//
//        view.end_date_picker_actions.setOnClickListener {
//            val getDate=Calendar.getInstance()
//            val datePicker=DatePickerDialog(requireContext(),android.R.style.Theme_Holo_Dialog_MinWidth,DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->
//
//                val selectDate=Calendar.getInstance()
//                selectDate.set(Calendar.YEAR,i)
//                selectDate.set(Calendar.MONTH,i2)
//                selectDate.set(Calendar.DAY_OF_MONTH,i3)
//
//                val date=formatDate.format(selectDate.time)
//
//                view.txt_end_date.setText(date)
//
//            },getDate.get(Calendar.YEAR),getDate.get(Calendar.MONTH),getDate.get(Calendar.DAY_OF_MONTH))
//            datePicker.show()
//        }
        incomeViewModel=ViewModelProviders.of(this).get(IncomeViewModel::class.java)

        incomeViewModel.allIncome.observe(
            viewLifecycleOwner, Observer {
                if(it != null){
                    var incomeCategoryName = LinkedList<String>()
                    for (data in it){
                        if(!incomeCategoryName.contains(data.category)){
                            Log.d("categorynamea>", data.category)
                            incomeCategoryName.add(data.category)
                        }
                    }
                    Log.d("categorynameSize>", incomeCategoryName.size.toString())
                    var count = 0
                    var incomeReportList  = ArrayList<IncomeReport>()
                    for (data in incomeCategoryName){
                        count  += 1
                        Log.d("categorynameb>", data)
                        incomeViewModel.getIncomeByCategory(data).observe(
                            viewLifecycleOwner, Observer {
                                Log.d("Result>>",data)
                                Log.d("income result>>",data + it.toString())
                                var incomeReport = IncomeReport(data,it)
                                incomeReportList.add(incomeReport)
                                Log.d("incomeReportList",incomeReportList.size.toString())
                                if(incomeCategoryName.size == count){
                                    incomebycategoryAdapter.setDataIncome(incomeReportList)
                                }
                            }
                        )
                    }


                }
            }
        )

        incomeViewModel.allIncome.observe(
            viewLifecycleOwner, Observer {
                for (data in it){
                    Log.d("Result income all>>",data.date)
                }
            }
        )

        return view
    }

}

data class IncomeReport(var category : String,var amount : Int)