package com.tmw.incomeexpense.ui.income

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.tmw.incomeexpense.R
import com.tmw.incomeexpense.model.IncomeCategory
import kotlinx.android.synthetic.main.fragment_income_categories.*
import kotlinx.android.synthetic.main.fragment_income_categories.view.*
import kotlinx.android.synthetic.main.item_income_categories.view.*
import kotlinx.android.synthetic.main.new_dialog.*
import kotlinx.android.synthetic.main.new_dialog.view.*


class IncomeCategoriesFragment : Fragment(), IncomeCategoriesAdapter.RowClickListener {

    lateinit var incomeCategoriesViewModel: IncomeCategoriesViewModel
    lateinit var incomeCategoriesAdapter: IncomeCategoriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_income_categories, container, false)

        view.recycler_income.apply {
            layoutManager = LinearLayoutManager(context)
            incomeCategoriesAdapter = IncomeCategoriesAdapter(this@IncomeCategoriesFragment)
            adapter = incomeCategoriesAdapter
        }

        incomeCategoriesViewModel =
            ViewModelProviders.of(this).get(IncomeCategoriesViewModel::class.java)
        incomeCategoriesViewModel.allIncomeCategories.observe(
            viewLifecycleOwner,
            Observer { incomecategories ->
                incomeCategoriesAdapter.setDataIncomeCategory(incomecategories)
                incomeCategoriesAdapter.notifyDataSetChanged()
            })


        view.btn_add_income.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            val builderView =
                LayoutInflater.from(requireContext()).inflate(R.layout.new_dialog, null)
            builder.setTitle("New Income Category")
            builder.setView(builderView)
            val mAlertDialog = builder.show()
            builderView.btn_save.setOnClickListener {
                if(builderView.etd_new.text.isEmpty()){
                    Toast.makeText(context,"Categories must not be empty",Toast.LENGTH_LONG).show()
                }else {
                    val cname = builderView.etd_new.text.toString()
                    val incomeCategory = IncomeCategory(0, cname)
                    incomeCategoriesViewModel.insertIncomeCategory(incomeCategory)
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
            incomeCategoriesViewModel.deleteAllIncomeCategory()
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

    override fun onDeleteCategoryClick(income_category: IncomeCategory) {
        val builderdelete = AlertDialog.Builder(requireContext())
        builderdelete.setPositiveButton("Yes") { _, _ ->
            incomeCategoriesViewModel.deleteIncomeCategory(income_category)
            Toast.makeText(context, "Succefully Deleted", Toast.LENGTH_LONG).show()
        }
        builderdelete.setNegativeButton("No") { _, _ ->

        }
        builderdelete.setTitle("Delete Category")
        builderdelete.setMessage("Are you sure want to delete?")
        builderdelete.create().show()
    }

    override fun onCategoryClickListener(income_category: IncomeCategory) {
        val builderupdate = AlertDialog.Builder(requireContext())
        val builderView = LayoutInflater.from(requireContext()).inflate(R.layout.new_dialog, null)
        builderupdate.setTitle("Update Category Name")
        builderupdate.setView(builderView)
        val mAlertDialog = builderupdate.show()
        builderView.etd_new.setText(income_category.inc_cname)
        builderView.etd_new.setTag(builderView.etd_new.id, income_category.inc_cid)
        builderView.btn_save.setText("Update")
        builderView.btn_save.setOnClickListener {
            val cname = builderView.etd_new.text.toString()
            val incomeCategory = IncomeCategory(
                builderView.etd_new.getTag(builderView.etd_new.id).toString().toInt(), cname
            )
            incomeCategoriesViewModel.updateIncomeCategory(incomeCategory)
            mAlertDialog.dismiss()
        }
        builderView.btn_cancel.setOnClickListener {
            mAlertDialog.dismiss()
        }

    }


}