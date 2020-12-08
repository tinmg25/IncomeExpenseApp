package com.tmw.incomeexpense.ui.report

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.tmw.incomeexpense.R
import com.tmw.incomeexpense.ui.expense.ExpenseViewModel
import kotlinx.android.synthetic.main.fragment_expense_by_cash.view.*
import java.util.*
import kotlin.collections.ArrayList


class ExpenseByCashFragment : Fragment() {

    private lateinit var expenseViewModel:ExpenseViewModel
    private lateinit var expensebyCashAdapter:ExpenseByCashAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_expense_by_cash, container, false)

        expensebyCashAdapter= ExpenseByCashAdapter()

        view.recycler_cash.apply {
            layoutManager=LinearLayoutManager(requireContext())
            adapter=expensebyCashAdapter
        }

        expenseViewModel=ViewModelProviders.of(this).get(ExpenseViewModel::class.java)

        expenseViewModel.allExpense.observe(viewLifecycleOwner, Observer {
            if(it!=null){
                var expenseCashName=LinkedList<String>()
                for(data in it){
                    if(!expenseCashName.contains(data.cash)){
                        expenseCashName.add(data.cash)
                    }
                }
                var count=0
                var expenseCashReportList=ArrayList<ExpenseCashReport>()
                for(data in expenseCashName){
                    count+=1

                    expenseViewModel.getExpenseByCash(data).observe(viewLifecycleOwner, Observer {
                        var expenseReport=ExpenseCashReport(data,it)
                        expenseCashReportList.add(expenseReport)
                        if(expenseCashReportList.size==count){
                            expensebyCashAdapter.setDataExpense(expenseCashReportList)
                        }
                    })
                }
            }
        })

        return view
    }

}

data class ExpenseCashReport(val cash:String,val amount:Int)