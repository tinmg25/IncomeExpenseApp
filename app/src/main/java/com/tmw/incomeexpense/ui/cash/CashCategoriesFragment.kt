package com.tmw.incomeexpense.ui.cash

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.tmw.incomeexpense.R
import com.tmw.incomeexpense.model.CashCategory
import kotlinx.android.synthetic.main.fragment_cash_categories.view.*
import kotlinx.android.synthetic.main.new_dialog.view.*


class CashCategoriesFragment : Fragment(),CashCategoriesAdapter.RowClickListener {

    private lateinit var cashCategoriesAdapter: CashCategoriesAdapter
    private lateinit var cashViewModel: CashViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_cash_categories, container, false)

        view.recycler_cash.apply{
            layoutManager=LinearLayoutManager(context)
            cashCategoriesAdapter= CashCategoriesAdapter(this@CashCategoriesFragment)
            adapter=cashCategoriesAdapter
        }

        cashViewModel=ViewModelProviders.of(this).get(CashViewModel::class.java)
        cashViewModel.allCashCategories.observe(viewLifecycleOwner, Observer {cashcategories->
            cashCategoriesAdapter.setDataCashCategory(cashcategories)
            cashCategoriesAdapter.notifyDataSetChanged()
        })

        view.btn_add_cash.setOnClickListener {
            val builder=AlertDialog.Builder(requireContext())
            val builderView=LayoutInflater.from(requireContext()).inflate(R.layout.new_dialog,null)
            builder.setTitle("New Cash Category")
            builder.setView(builderView)
            val mAlertDialog=builder.show()
            builderView.btn_save.setOnClickListener {
                if(builderView.etd_new.text.isEmpty()){
                    Toast.makeText(context,"Category must not be empty",Toast.LENGTH_LONG).show()
                }else {
                    val cname = builderView.etd_new.text.toString()
                    val cashCategory = CashCategory(0, cname)
                    cashViewModel.insertCash(cashCategory)
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
            cashViewModel.deleteAllCash()
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

    override fun onDeletCategoryClick(cashCategory: CashCategory) {
        val builderdelete = AlertDialog.Builder(requireContext())
        builderdelete.setPositiveButton("Yes") { _, _ ->
            cashViewModel.deleteCash(cashCategory)
            Toast.makeText(context, "Succefully Deleted", Toast.LENGTH_LONG).show()
        }
        builderdelete.setNegativeButton("No") { _, _ ->

        }
        builderdelete.setTitle("Delete Category")
        builderdelete.setMessage("Are you sure want to delete?")
        builderdelete.create().show()
    }

    override fun onCategoryClickListener(cashCategory: CashCategory) {
        val builderupdate = AlertDialog.Builder(requireContext())
        val builderView = LayoutInflater.from(requireContext()).inflate(R.layout.new_dialog, null)
        builderupdate.setTitle("Update Category Name")
        builderupdate.setView(builderView)
        val mAlertDialog = builderupdate.show()
        builderView.etd_new.setText(cashCategory.cash_name)
        builderView.etd_new.setTag(builderView.etd_new.id, cashCategory.cash_name)
        builderView.btn_save.setText("Update")
        builderView.btn_save.setOnClickListener {
            val cname = builderView.etd_new.text.toString()
            val cashCategory = CashCategory(
                builderView.etd_new.getTag(builderView.etd_new.id).toString().toInt(), cname
            )
            cashViewModel.updateCash(cashCategory)
            mAlertDialog.dismiss()
        }
        builderView.btn_cancel.setOnClickListener {
            mAlertDialog.dismiss()
        }
    }

}