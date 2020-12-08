package com.tmw.incomeexpense.ui.report

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.tmw.incomeexpense.R
import com.tmw.incomeexpense.ui.income.IncomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_income_by_chart.*
import kotlinx.android.synthetic.main.fragment_income_by_chart.view.*
import java.util.*
import kotlin.collections.ArrayList

class IncomeByChartFragment : Fragment() {

    private lateinit var incomeViewModel: IncomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_income_by_chart, container, false)

        incomeViewModel=ViewModelProviders.of(this).get(IncomeViewModel::class.java)

        incomeViewModel.allIncome.observe(viewLifecycleOwner, Observer {
            if(it!=null){
                var categoryName= LinkedList<String>()
                for(data in it){
                    if(!categoryName.contains(data.category)){
                        categoryName.add(data.category)
                    }
                }
                var count=0
                val dataEntry=ArrayList<DataEntry>()
                val pie=AnyChart.pie()
                for(data in categoryName){
                    count+=1
                    incomeViewModel.getIncomeByCategory(data).observe(viewLifecycleOwner, Observer {
                        dataEntry.add(ValueDataEntry(data,it))
                        pie.data(dataEntry)
                    })
                }
                view.income_chart.setChart(pie)
            }
        })

        return view
    }
}