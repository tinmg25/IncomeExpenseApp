package com.tmw.incomeexpense.ui.other

import android.app.AlertDialog
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat.recreate
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.tmw.incomeexpense.R
import com.tmw.incomeexpense.ui.cash.CashViewModel
import com.tmw.incomeexpense.ui.expense.ExpenseCategoriesViewModel
import com.tmw.incomeexpense.ui.expense.ExpenseViewModel
import com.tmw.incomeexpense.ui.income.IncomeCategoriesViewModel
import com.tmw.incomeexpense.ui.income.IncomeViewModel
import kotlinx.android.synthetic.main.fragment_setting.view.*
import java.util.*


class SettingFragment : Fragment() {

    private lateinit var incomecategoriesViewModel: IncomeCategoriesViewModel
    private lateinit var expensecategoriesViewModel: ExpenseCategoriesViewModel
    private lateinit var cashViewModel: CashViewModel
    private lateinit var incomeViewModel: IncomeViewModel
    private lateinit var expenseViewModel: ExpenseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_setting, container, false)

        view.layout_language.setOnClickListener{
            findNavController().navigate(R.id.action_settingFragment2_to_languageActivity)
        }

        incomecategoriesViewModel=ViewModelProviders.of(this).get(IncomeCategoriesViewModel::class.java)
        expensecategoriesViewModel=ViewModelProviders.of(this).get(ExpenseCategoriesViewModel::class.java)
        cashViewModel=ViewModelProviders.of(this).get(CashViewModel::class.java)
        incomeViewModel=ViewModelProviders.of(this).get(IncomeViewModel::class.java)
        expenseViewModel=ViewModelProviders.of(this).get(ExpenseViewModel::class.java)

        view.layout_clear.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Delete All Data")
            builder.setMessage("Are you sure want to delete all data?")
            builder.setPositiveButton("Yes") { _, _ ->
                incomecategoriesViewModel.deleteAllIncomeCategory()
                expensecategoriesViewModel.deleteAllExpenseCategory()
                cashViewModel.deleteAllCash()
                incomeViewModel.deleteAllIncome()
                expenseViewModel.deleteAllExpense()
                findNavController().navigate(R.id.action_settingFragment2_to_nav_home)
                Toast.makeText(context, "Successful delete all data", Toast.LENGTH_LONG).show()
            }
            builder.setNegativeButton("No") { _, _ ->

            }
            builder.create().show()
        }
        return view
    }
}