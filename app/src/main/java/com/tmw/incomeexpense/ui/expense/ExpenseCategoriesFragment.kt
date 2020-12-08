package com.tmw.incomeexpense.ui.expense

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tmw.incomeexpense.R
import com.tmw.incomeexpense.model.ExpenseCategory
import com.tmw.incomeexpense.model.IncomeCategory
import kotlinx.android.synthetic.main.fragment_expense_categories.view.*
import kotlinx.android.synthetic.main.fragment_income_categories.view.*
import kotlinx.android.synthetic.main.new_dialog.view.*


class ExpenseCategoriesFragment : Fragment(), ExpenseCategoriesAdapter.RowClickListener {

    lateinit var expenseCategoriesViewModel: ExpenseCategoriesViewModel
    lateinit var expenseCategoriesAdapter: ExpenseCategoriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_expense_categories, container, false)

        view.recycler_expense.apply {
            layoutManager = LinearLayoutManager(requireContext())
            expenseCategoriesAdapter = ExpenseCategoriesAdapter(this@ExpenseCategoriesFragment)
            adapter = expenseCategoriesAdapter
        }

        expenseCategoriesViewModel =
            ViewModelProviders.of(this).get(ExpenseCategoriesViewModel::class.java)
        expenseCategoriesViewModel.allExpenseCategories.observe(
            viewLifecycleOwner, Observer { expensecategories ->
                expenseCategoriesAdapter.setDataExpenseCategory(expensecategories)
                expenseCategoriesAdapter.notifyDataSetChanged()
            }
        )
        view.btn_add_expense.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            val builderView =
                LayoutInflater.from(requireContext()).inflate(R.layout.new_dialog, null)
            builder.setTitle("New Expense Category")
            builder.setView(builderView)
            val mAlertDialog = builder.show()
            builderView.btn_save.setOnClickListener {
                if(builderView.etd_new.text.isEmpty()){
                    Toast.makeText(context,"Category must not be empty",Toast.LENGTH_LONG).show()
                }else {
                    val cname = builderView.etd_new.text.toString()
                    val expenseCategory = ExpenseCategory(0, cname)
                    expenseCategoriesViewModel.insertExpenseCategory(expenseCategory)
                    Toast.makeText(context,"Successfully added",Toast.LENGTH_SHORT).show()
                    mAlertDialog.dismiss()
                }
            }
            builderView.btn_cancel.setOnClickListener {
                mAlertDialog.dismiss()
            }
        }

        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteAllCategoreis()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllCategoreis() {
        val dialog = AlertDialog.Builder(requireContext())
        dialog.setPositiveButton("Yes") { _, _ ->
            expenseCategoriesViewModel.deleteAllExpenseCategory()
            Toast.makeText(
                requireContext(), "Successfully Removed All Categories!",
                Toast.LENGTH_LONG
            ).show()
        }
        dialog.setNegativeButton("No") { _, _ ->

        }
        dialog.setTitle("Delete All Categories List")
        dialog.setMessage("Are you sure want to delete all categories?")
        dialog.create().show()
    }

    override fun onDeleteCategoryClick(expenseCategory: ExpenseCategory) {
        val builderdelete = AlertDialog.Builder(requireContext())
        builderdelete.setPositiveButton("Yes") { _, _ ->
            expenseCategoriesViewModel.deleteExpenseCategory(expenseCategory)
            Toast.makeText(context, "Succefully Deleted", Toast.LENGTH_LONG).show()
        }
        builderdelete.setNegativeButton("No") { _, _ ->

        }
        builderdelete.setTitle("Delete Category")
        builderdelete.setMessage("Are you sure want to delete?")
        builderdelete.create().show()
    }

    override fun onCategoryClickListener(expenseCategory: ExpenseCategory) {
        val builderupdate = AlertDialog.Builder(requireContext())
        val builderView = LayoutInflater.from(requireContext()).inflate(R.layout.new_dialog, null)
        builderupdate.setTitle("Update Category Name")
        builderupdate.setView(builderView)
        val mAlertDialog = builderupdate.show()
        builderView.etd_new.setText(expenseCategory.exp_cname)
        builderView.etd_new.setTag(builderView.etd_new.id, expenseCategory.exp_cid)
        builderView.btn_save.setText("Update")
        builderView.btn_save.setOnClickListener {
            val cname = builderView.etd_new.text.toString()
            val expenseCategory = ExpenseCategory(
                builderView.etd_new.getTag(builderView.etd_new.id).toString().toInt(), cname
            )
            expenseCategoriesViewModel.updateExpenseCategory(expenseCategory)
            mAlertDialog.dismiss()
        }
        builderView.btn_cancel.setOnClickListener {
            mAlertDialog.dismiss()
        }
    }
}
