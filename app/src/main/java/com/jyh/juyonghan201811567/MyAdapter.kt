package com.jyh.juyonghan201811567

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jyh.juyonghan201811567.databinding.RowBinding

class MyAdapter(private val items:ArrayList<MyData>):RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    var itemClickListener:OnItemClickListener? = null

    interface OnItemClickListener{
        fun onItemClick( pos:Int)
    }


    inner class ViewHolder(val binding: RowBinding): RecyclerView.ViewHolder(binding.root) {
        init{
            binding.textView.setOnClickListener {
                itemClickListener?.onItemClick( adapterPosition )
            }
        }
    }

    fun setOnItemClickListener(itemClickListener: (pos:Int) -> Unit){
        this.itemClickListener = object :OnItemClickListener{
            override fun onItemClick( pos:Int) {
                itemClickListener(pos)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.textView.text = items[position].word
        if(items[position].isExpanded) {
            holder.binding.meaning.visibility=View.VISIBLE
            holder.binding.meaning.text = items[position].meaning
        }else{
            holder.binding.meaning.visibility=View.GONE
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun move(oldPos:Int, newPos:Int){
        val item = items[oldPos]
        items.removeAt(oldPos)
        items.add(newPos,item)
        notifyItemMoved(oldPos,newPos)
    }

    fun removeItem(pos:Int){
        items.removeAt(pos)
        notifyItemRemoved(pos)
    }

    fun changeState(pos:Int){
        items[pos].isExpanded = items[pos].isExpanded.not()
        notifyItemChanged(pos)
    }
}