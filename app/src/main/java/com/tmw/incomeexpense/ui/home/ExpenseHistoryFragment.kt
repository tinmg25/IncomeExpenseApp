package com.tmw.incomeexpense.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.tmw.incomeexpense.R
import com.tmw.incomeexpense.model.Expense
import com.tmw.incomeexpense.ui.expense.ExpenseHistoryAdapter
import com.tmw.incomeexpense.ui.expense.ExpenseViewModel
import kotlinx.android.synthetic.main.fragment_expense_history.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.item_expense_history.view.*


class ExpenseHistoryFragment : Fragment(), ExpenseHistoryAdapter.RowClickListener {

    private lateinit var expenseViewModel: ExpenseViewModel
    private lateinit var expenseHistoryAdater: ExpenseHistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_expense_history, container, false)

        view.recycler_expense_history.apply {
            layoutManager = LinearLayoutManager(requireContext())
            expenseHistoryAdater = ExpenseHistoryAdapter(this@ExpenseHistoryFragment)
            adapter = expenseHistoryAdater
        }

        expenseViewModel = ViewModelProviders.of(this).get(ExpenseViewModel::class.java)
        expenseViewModel.allExpense.observe(viewLifecycleOwner, Observer { expense ->
            expenseHistoryAdater.setDataExpenseHistory(expense)
            expenseHistoryAdater.notifyDataSetChanged()
        })

        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteAllExpense()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllExpense() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Delete All Expense")
        builder.setMessage("Are you sure want to delete all expense?")
        builder.setPositiveButton("Yes") { _, _ ->
            expenseViewModel.deleteAllExpense()
            Toast.makeText(context, "Successfully Delete All Expense", Toast.LENGTH_LONG)
        }
        builder.setNegativeButton("No") { _, _ ->

        }
        builder.create().show()
    }

    override fun onDeleteExpenseClick(expense: Expense) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Delete Expense")
        builder.setMessage("Are you sure want to delete expense?")
        builder.setPositiveButton("Yes") { _, _ ->
            expenseViewModel.deleteExpense(expense)
            Toast.makeText(context, "Successfully Delete Expense", Toast.LENGTH_LONG)
        }
        builder.setNegativeButton("No") { _, _ ->

        }
        builder.create().show()
    }
}