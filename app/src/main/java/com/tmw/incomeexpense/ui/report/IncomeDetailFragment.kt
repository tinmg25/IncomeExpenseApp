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
import com.tmw.incomeexpense.model.Income
import com.tmw.incomeexpense.ui.income.IncomeViewModel
import kotlinx.android.synthetic.main.fragment_income_detail.view.*


class IncomeDetailFragment : Fragment(),IncomeDetailAdapter.RowClickListener {

    lateinit var incomedetailAdapter: IncomeDetailAdapter
    lateinit var incomeViewModel: IncomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_income_detail, container, false)

        view.recycler_detail.apply {
            layoutManager=LinearLayoutManager(requireContext())
            incomedetailAdapter= IncomeDetailAdapter(this@IncomeDetailFragment)
            adapter=incomedetailAdapter
        }

        incomeViewModel=ViewModelProviders.of(this).get(IncomeViewModel::class.java)
        incomeViewModel.allIncome.observe(viewLifecycleOwner, Observer {
            incomedetailAdapter.setDataIncome(it)
            incomedetailAdapter.notifyDataSetChanged()
        })
        return view
    }

    override fun onItemClickListener(income: Income) {
        TODO("Not yet implemented")
    }

}