package com.tmw.incomeexpense.ui.expense

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tmw.incomeexpense.R
import com.tmw.incomeexpense.model.ExpenseCategory
import kotlinx.android.synthetic.main.item_expense_categories.view.*

class ExpenseCategoriesAdapter(var listener: RowClickListener) :RecyclerView.Adapter<ExpenseCategoriesAdapter.ExpenseCategoriesViewHolder>() {

    private var expenseCategoriesList= emptyList<ExpenseCategory>()

    fun clickListener(ecategory:List<ExpenseCategory>){
        this.expenseCategoriesList=ecategory
    }
    inner class ExpenseCategoriesViewHolder(itemView: View, val listener: RowClickListener):RecyclerView.ViewHolder(itemView) {

        val exp_category=itemView.txt_expense_category
        val exp_delete=itemView.img_expense_category_delete

        fun bind(expense_Category:ExpenseCategory){
            exp_category.text=expense_Category.exp_cname

            exp_delete.setOnClickListener {
                listener.onDeleteCategoryClick(expense_Category)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseCategoriesViewHolder {
        val inflater=LayoutInflater.from(parent.context).inflate(R.layout.item_expense_categories,parent,false)
        return ExpenseCategoriesViewHolder(inflater,listener)
    }

    override fun onBindViewHolder(holder: ExpenseCategoriesViewHolder, position: Int) {
        holder.itemView.setOnClickListener{
            listener.onCategoryClickListener(expenseCategoriesList[position])
        }
        holder.bind(expenseCategoriesList[position])
    }

    override fun getItemCount(): Int {
        return expenseCategoriesList.size
    }

    fun setDataExpenseCategory(expenseCategory: List<ExpenseCategory>){
        this.expenseCategoriesList=expenseCategory
        notifyDataSetChanged()
    }

    interface RowClickListener{
        fun onDeleteCategoryClick(expenseCategory: ExpenseCategory)
        fun onCategoryClickListener(expenseCategory: ExpenseCategory)
    }
}