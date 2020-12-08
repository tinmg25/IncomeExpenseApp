package com.tmw.incomeexpense.ui.cash

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.tmw.incomeexpense.R
import com.tmw.incomeexpense.model.CashCategory
import kotlinx.android.synthetic.main.item_cash_categories.view.*

class CashCategoriesAdapter(var listener:RowClickListener) :RecyclerView.Adapter<CashCategoriesAdapter.CashCategoriesViewHolder>(){

    private  var cashCategoriesList= emptyList<CashCategory>()

    fun setOnClickListener(c_category:List<CashCategory>){
        this.cashCategoriesList=c_category
    }

    inner class CashCategoriesViewHolder(itemView: View,val listener:RowClickListener):RecyclerView.ViewHolder(itemView) {

        val cash_category=itemView.txt_cash_category
        val del_category=itemView.img_cash_category_delete

        fun bind(cashCategory: CashCategory){
            cash_category.text=cashCategory.cash_name

            del_category.setOnClickListener {
                listener.onDeletCategoryClick(cashCategory)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CashCategoriesViewHolder {
        val inflater=LayoutInflater.from(parent.context).inflate(R.layout.item_cash_categories,parent,false)
        return CashCategoriesViewHolder(inflater,listener)
    }

    override fun onBindViewHolder(holder: CashCategoriesViewHolder, position: Int) {
        holder.bind(cashCategoriesList[position])

        holder.itemView.setOnClickListener{
            listener.onCategoryClickListener(cashCategoriesList[position])
        }
    }

    override fun getItemCount(): Int {
        return cashCategoriesList.size
    }

    fun setDataCashCategory(cash_category:List<CashCategory>){
        this.cashCategoriesList=cash_category
        notifyDataSetChanged()
    }

    interface RowClickListener{
        fun onDeletCategoryClick(cashCategory: CashCategory)
        fun onCategoryClickListener(cashCategory: CashCategory)
    }

}