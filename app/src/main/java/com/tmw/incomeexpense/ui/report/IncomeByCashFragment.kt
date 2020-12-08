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
import com.tmw.incomeexpense.ui.income.IncomeViewModel
import kotlinx.android.synthetic.main.fragment_expense_by_category.view.*
import kotlinx.android.synthetic.main.fragment_income_by_cash.view.*
import java.util.*


class IncomeByCashFragment : Fragment() {

    private lateinit var incomeViewModel: IncomeViewModel
    private lateinit var incomeByCashAdapter: IncomeByCashAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_income_by_cash, container, false)

        incomeByCashAdapter= IncomeByCashAdapter()

        view.recycler_cash.apply {
            layoutManager=LinearLayoutManager(requireContext())
            adapter=incomeByCashAdapter
        }

        incomeViewModel=ViewModelProviders.of(this).get(IncomeViewModel::class.java)

        incomeViewModel.allIncome.observe(viewLifecycleOwner, Observer {
            if(it!=null){
                var incomeCashName= LinkedList<String>()
                for(data in it){
                    if(!incomeCashName.contains(data.cash)) {
                        incomeCashName.add(data.cash)
                    }
                }
                var count=0
                var incomeCashReportList=ArrayList<IncomeCashReport>()
                for(data in incomeCashName){
                    count +=1

                    incomeViewModel.getIncomeByCash(data).observe(viewLifecycleOwner, Observer {
                        var incomeReport=IncomeCashReport(data,it)
                        incomeCashReportList.add(incomeReport)
                        if(incomeCashReportList.size==count){
                            incomeByCashAdapter.setDataIncome(incomeCashReportList)
                        }
                    })
                }
            }
        })

        return view
    }

}

data class IncomeCashReport(val cash:String,val amount:Int)