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
import com.tmw.incomeexpense.model.Expense
import com.tmw.incomeexpense.ui.expense.ExpenseViewModel
import kotlinx.android.synthetic.main.fragment_expense_detail.view.*


class ExpenseDetailFragment : Fragment(),ExpenseDetailAdapter.RowClickListener {

    private lateinit var expenseDetailAdapter: ExpenseDetailAdapter
    private lateinit var expenseViewModel:ExpenseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_expense_detail, container, false)

        view.recycler_detail.apply{
            layoutManager=LinearLayoutManager(requireContext())
            expenseDetailAdapter=ExpenseDetailAdapter(this@ExpenseDetailFragment)
            adapter=expenseDetailAdapter
        }

        expenseViewModel=ViewModelProviders.of(this).get(ExpenseViewModel::class.java)
        expenseViewModel.allExpense.observe(viewLifecycleOwner, Observer {expense->
            expenseDetailAdapter.setDataExpense(expense)
            expenseDetailAdapter.notifyDataSetChanged()
        })

        return view
    }

    override fun onItemClickListener(expense: Expense) {
        TODO("Not yet implemented")
    }


}