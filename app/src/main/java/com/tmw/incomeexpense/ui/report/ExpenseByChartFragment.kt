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
import com.tmw.incomeexpense.R
import com.tmw.incomeexpense.ui.expense.ExpenseViewModel
import kotlinx.android.synthetic.main.fragment_expense_by_chart.view.*
import java.util.*


class ExpenseByChartFragment : Fragment() {

    private lateinit var expenseViewModel:ExpenseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_expense_by_chart, container, false)

        expenseViewModel=ViewModelProviders.of(this).get(ExpenseViewModel::class.java)

        expenseViewModel.allExpense.observe(viewLifecycleOwner, Observer {
            if(it!=null){
                var categoryName=LinkedList<String>()
                for(data in it){
                    if(!categoryName.contains(data.category)){
                        categoryName.add(data.category)
                    }
                }
                var count=0
                var dataEntry=ArrayList<DataEntry>()
                val pie=AnyChart.pie()
                for(data in categoryName){
                    count+=1
                    expenseViewModel.getExpenseByCategory(data).observe(viewLifecycleOwner, Observer {
                        dataEntry.add(ValueDataEntry(data,it))
                        pie.data(dataEntry)
                    })
                }
                view.expense_chart.setChart(pie)
            }
        })

        return view
    }

}