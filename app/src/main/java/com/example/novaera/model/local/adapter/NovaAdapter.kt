package com.example.novaera.model.local.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.novaera.R
import com.example.novaera.model.local.entities.ProductsEnti
import kotlinx.android.synthetic.main.item_phone_list.view.*

class NovaAdapter(val mphoneData: phoneData): RecyclerView.Adapter<NovaAdapter.PhoneViewHolder>() {
    private var phoneList = emptyList<ProductsEnti>()

    fun updateAdapter(mList : List<ProductsEnti>){
        phoneList = mList
        notifyDataSetChanged()
    }

    inner class PhoneViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imgphone = itemView.img
        val txtname = itemView.txtNombre
        val txtprice = itemView.txtPrice
        val algo = itemView.setOnClickListener {
            mphoneData.passPhone(phoneList[adapterPosition])
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_phone_list
            , parent, false)
        return PhoneViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PhoneViewHolder, position: Int) {
        val phone = phoneList[position]
        Glide.with(holder.itemView.context).load(phone.image).into(holder.imgphone)
        holder.txtname.text = phone.name
        holder.txtprice.text = "$" + phone.price.toString()
    }

    override fun getItemCount()= phoneList.size

    interface phoneData{
        fun passPhone(phone : ProductsEnti)
    }

}