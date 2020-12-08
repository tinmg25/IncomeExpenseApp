package com.tmw.incomeexpense.ui.expense

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.tmw.incomeexpense.R
import com.tmw.incomeexpense.model.Expense
import com.tmw.incomeexpense.ui.cash.CashViewModel
import kotlinx.android.synthetic.main.fragment_expense.*
import kotlinx.android.synthetic.main.fragment_expense.view.*
import kotlinx.android.synthetic.main.fragment_income.txt_income_date
import kotlinx.android.synthetic.main.fragment_income.view.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class ExpenseFragment : Fragment() {

    private lateinit var expenseViewModel: ExpenseViewModel
    private lateinit var expenseCategoriesViewModel: ExpenseCategoriesViewModel
    private lateinit var cashViewModel: CashViewModel

    var formatDate=SimpleDateFormat("dd/MM/yyyy")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_expense, container, false)

        expenseViewModel=ViewModelProviders.of(this).get(ExpenseViewModel::class.java)

        expenseCategoriesViewModel=ViewModelProviders.of(this).get(ExpenseCategoriesViewModel::class.java)

        var expenseCategoryAdapter=ArrayAdapter<Any>(requireContext(),R.layout.custom_spinner_layout)
        expenseCategoryAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_layout)

        expenseCategoriesViewModel.allExpenseCategories.observe(viewLifecycleOwner, Observer {expensecategories->
            expensecategories?.forEach {
                expenseCategoryAdapter.add(it.exp_cname)
            }
        })
        view.spinner_category_expense.adapter=expenseCategoryAdapter

        cashViewModel=ViewModelProviders.of(this).get(CashViewModel::class.java)

        var cashCategoryAdapter=ArrayAdapter<Any>(requireContext(),R.layout.custom_spinner_layout)
        cashCategoryAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_layout)

        cashViewModel.allCashCategories.observe(viewLifecycleOwner, Observer { cashcategories->
            cashcategories?.forEach {
                cashCategoryAdapter.add(it.cash_name)
            }
        })
        view.spinner_cash_expense.adapter=cashCategoryAdapter

        val c= Calendar.getInstance()
        val currentDate= SimpleDateFormat("dd/MM/yyyy").format(c.time)
        val year=c.get(Calendar.YEAR)
        val month=c.get(Calendar.MONTH)
        val day=c.get(Calendar.DAY_OF_MONTH)

        view.txt_expense_date.setText(currentDate)

        view.expense_date_picker_actions.setOnClickListener {
            val getDate=Calendar.getInstance()

            val datePicker=DatePickerDialog(requireContext(),android.R.style.Theme_Holo_Dialog_MinWidth,DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->

                val selectDate=Calendar.getInstance()
                selectDate.set(Calendar.YEAR,i)
                selectDate.set(Calendar.MONTH,i2)
                selectDate.set(Calendar.DAY_OF_MONTH,i3)

                val date=formatDate.format(selectDate.time)

                view.txt_expense_date.setText(date)

            },getDate.get(Calendar.YEAR),getDate.get(Calendar.MONTH),getDate.get(Calendar.DAY_OF_MONTH))
            datePicker.show()
        }

        view.btn_save_expense.setOnClickListener {
            insertDataIntoDatabase()
        }

        return view
    }

    private fun insertDataIntoDatabase() {
        val date = txt_expense_date.text.toString()
        val cash = spinner_cash_expense.selectedItem.toString()
        val category = spinner_category_expense.selectedItem.toString()
        val remark = etd_expense_remark.text
        val amount = etd_expense_amount.text

        if(inputCheck(date,cash,category,remark,amount)){
            val expense= Expense(0,date,cash,category,remark.toString(),Integer.parseInt(amount.toString()))
            expenseViewModel.insertExpense(expense)
            Toast.makeText(context,"Successfully Added", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.nav_home)
        }else{
            Toast.makeText(context,"Please Fill All Fields", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(
        date: String,
        cash: String,
        category: String,
        remark: Editable,
        amount: Editable
    ): Boolean {
        return !(TextUtils.isEmpty(date) && TextUtils.isEmpty(cash) && TextUtils.isEmpty(category) && remark.isEmpty()
                && amount.isEmpty())
    }

}