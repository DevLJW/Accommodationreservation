package com.example.accommodationreservation.Adapter

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.accommodationreservation.HouseModel
import com.example.accommodationreservation.R

class HouseViewPagerAdapter(val itemClicked : (HouseModel) -> Unit):ListAdapter<HouseModel,HouseViewPagerAdapter.ItemViewHolder>(differ) {


        inner class ItemViewHolder(val view : View): RecyclerView.ViewHolder(view){

            fun bind(houseModel: HouseModel){

               val titleTextView = view.findViewById<TextView>(R.id.titleTextView)
                val priceTextView = view.findViewById<TextView>(R.id.priceTextView)
                val thumbnailImageView = view.findViewById<ImageView>(R.id.thumnailImageView)

                view.setOnClickListener{
                    itemClicked(houseModel)

                }

                titleTextView.text = houseModel.title
                priceTextView.text = houseModel.price
                Glide.with(thumbnailImageView.context)
                    .load(houseModel.imgUrl)
                    .into(thumbnailImageView)

            }


        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

            val inflater = LayoutInflater.from(parent.context) //메인 액티비티의 viewpager 정보 가져옴

            return ItemViewHolder(inflater.inflate(R.layout.house_item_viewpager,parent,false)) //메인 액티비티 viewpager에 item 넣기


    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            holder.bind(currentList[position])

    }

    companion object {
        val differ = object : DiffUtil.ItemCallback<HouseModel>(){

            override fun areItemsTheSame(oldItem: HouseModel, newItem: HouseModel): Boolean {
                   return oldItem.id == newItem.id
            }


            override fun areContentsTheSame(oldItem: HouseModel, newItem: HouseModel): Boolean {
               return oldItem == newItem
            }


        }
    }

}