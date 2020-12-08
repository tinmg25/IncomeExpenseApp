package com.tmw.incomeexpense.ui.income

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
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.tmw.incomeexpense.R
import com.tmw.incomeexpense.model.Income
import com.tmw.incomeexpense.ui.cash.CashViewModel
import kotlinx.android.synthetic.main.fragment_income.*
import kotlinx.android.synthetic.main.fragment_income.view.*
import kotlinx.android.synthetic.main.fragment_income_by_category.view.*
import java.util.*
import java.lang.*
import java.text.DateFormat
import java.text.SimpleDateFormat

class IncomeFragment : Fragment() {

    lateinit var incomeViewModel: IncomeViewModel
    lateinit var incomeCategoriesViewModel: IncomeCategoriesViewModel
    lateinit var cashViewModel: CashViewModel

    var formatDate=SimpleDateFormat("dd/MM/yyyy")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_income, container, false)

        incomeViewModel = ViewModelProviders.of(this).get(IncomeViewModel::class.java)

        incomeCategoriesViewModel=ViewModelProviders.of(this).get(IncomeCategoriesViewModel::class.java)

        var incomeCategoryAdapter=ArrayAdapter<Any>(requireContext(),R.layout.custom_spinner_layout)
        incomeCategoryAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_layout)

        incomeCategoriesViewModel.allIncomeCategories.observe(viewLifecycleOwner,
            androidx.lifecycle.Observer{ incomecategories ->
                incomecategories?.forEach{
                    incomeCategoryAdapter.add(it.inc_cname)
                }
            })
        view.spinner_category_income.adapter=incomeCategoryAdapter

        cashViewModel=ViewModelProviders.of(this).get(CashViewModel::class.java)

        var cashCategoryAdapter=ArrayAdapter<Any>(requireContext(),R.layout.custom_spinner_layout)
        cashCategoryAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_layout)

        cashViewModel.allCashCategories.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { cashcategories ->
                cashcategories?.forEach {
                    cashCategoryAdapter.add(it.cash_name)
                }
            })

        view.spinner_cash_income.adapter=cashCategoryAdapter

        val c= Calendar.getInstance()
        val currentDate= SimpleDateFormat("dd/MM/yyyy").format(c.time)
        val year=c.get(Calendar.YEAR)
        val month=c.get(Calendar.MONTH)
        val day=c.get(Calendar.DAY_OF_MONTH)

        view.txt_income_date.setText(currentDate)

        view.income_date_picker_actions.setOnClickListener {
            val getDate=Calendar.getInstance()
            val datePicker=DatePickerDialog(requireContext(),android.R.style.Theme_Holo_Dialog_MinWidth,DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->

                val selectDate=Calendar.getInstance()
                selectDate.set(Calendar.YEAR,i)
                selectDate.set(Calendar.MONTH,i2)
                selectDate.set(Calendar.DAY_OF_MONTH,i3)

                val date=formatDate.format(selectDate.time)

                view.txt_income_date.setText(date)

            },getDate.get(Calendar.YEAR),getDate.get(Calendar.MONTH),getDate.get(Calendar.DAY_OF_MONTH))
            datePicker.show()
        }
        view.btn_save_income.setOnClickListener {
            insertDataIntoDatabase()
        }
        return view
    }

    private fun insertDataIntoDatabase() {
        val date = txt_income_date.text.toString()
        val cash = spinner_cash_income.selectedItem.toString()
        val category = spinner_category_income.selectedItem.toString()
        val remark = etd_income_remark.text
        val amount = etd_income_amount.text

        if(inputCheck(remark,amount)){
            val income= Income(0,date,cash,category,remark.toString(),Integer.parseInt(amount.toString()))
            incomeViewModel.insertIncome(income)
            Toast.makeText(context,"Successfully Added",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.nav_home)
        }else{
            Toast.makeText(context,"Please Fill All Fields",Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(
        remark: Editable,
        amount: Editable
    ): Boolean {
        return !(remark.isEmpty()
        && amount.isEmpty())
    }

}