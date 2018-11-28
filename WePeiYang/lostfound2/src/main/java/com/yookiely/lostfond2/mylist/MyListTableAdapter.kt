package com.yookiely.lostfond2.mylist

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.lostfond2.R
import com.yookiely.lostfond2.detail.DetailActivity
import com.yookiely.lostfond2.release.ReleaseActivity
import com.yookiely.lostfond2.service.MyListDataOrSearchBean
import com.yookiely.lostfond2.service.Utils
import org.jetbrains.anko.textColor

class MyListTableAdapter(var myListBean: MutableList<MyListDataOrSearchBean>, var context: FragmentActivity?, var lostOrFound: String, var mylistView: MyListService.MyListView) : RecyclerView.Adapter<MyListTableAdapter.MyListViewHolder>() {


    private val idOfBundle = "id"

    class MyListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var myListItemTitle: TextView = view.findViewById(R.id.tv_mylist_item_title)
        var myListItemStatus: TextView = view.findViewById(R.id.tv_mylist_item_status)
        var myListItemType: TextView = view.findViewById(R.id.tv_mylist_item_type)
        var myListItemTime: TextView = view.findViewById(R.id.tv_mylist_item_time)
        var myListItemPlace: TextView = view.findViewById(R.id.tv_mylist_item_place)
        var myListItemPic: ImageView = view.findViewById(R.id.iv_mylist_item_pic)
        var myListItemPencilTouch: TextView = view.findViewById(R.id.tv_mylist_item_pencil_touch)
        var mylistItemOutdata: ImageView = view.findViewById(R.id.iv_mylist_detail_isoutdate)
        var mylistItemButton = view.findViewById<Button>(R.id.bn_mylist_isback)
        var myListItemButtonOutdate = view.findViewById<Button>(R.id.bn_mylist_item_outdate)

    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyListViewHolder, position: Int) {
        val (id: Int, _: Int, _: String, title: String, place: String, time: String, _: String, detail_type: Int, isback: Int, picture: List<String>?, _: String?, _: Int?, campus: Int, isExpired: Int) = myListBean[position]
        val intent = Intent()
        val bundle = Bundle()

        holder.apply {
            myListItemTitle.text = title
            myListItemType.text = Utils.getType(detail_type)
            myListItemTime.text = time
            if (campus == 1) {
                this.myListItemPlace.text = "北洋园 - $place"
            } else {
                this.myListItemPlace.text = "卫津路 - $place"
            }

            if (isExpired == 1) {
                // 0是未过期,1是已过期
                holder.myListItemButtonOutdate.visibility = View.VISIBLE
                holder.mylistItemButton.visibility = View.GONE
                holder.myListItemButtonOutdate.text = "重新编辑"
                holder.myListItemStatus.textColor = Color.parseColor("#999999")
                holder.myListItemStatus.text = "已过期"
                holder.mylistItemOutdata.visibility = View.VISIBLE
            } else {
                holder.mylistItemOutdata.visibility = View.GONE
                holder.myListItemButtonOutdate.visibility = View.GONE
                holder.mylistItemButton.visibility = View.VISIBLE
                if (lostOrFound == "found") {
                    if (isback == 1) {
                        holder.myListItemStatus.text = "已归还"
                        holder.myListItemStatus.textColor = Color.parseColor("#999999")
                        holder.mylistItemButton.text = "取消归还"
                    } else {
                        holder.myListItemStatus.text = "未归还"
                        holder.myListItemStatus.textColor = Color.parseColor("#44a0e3")
                        holder.mylistItemButton.text = "确认归还"
                    }
                } else {
                    if (isback == 1) {
                        holder.myListItemStatus.text = "未找到"
                        holder.myListItemStatus.textColor = Color.parseColor("#999999")
                        holder.mylistItemButton.text = "确认找到"
                    } else {
                        holder.myListItemStatus.text = "已找到"
                        holder.myListItemStatus.textColor = Color.parseColor("#44a0e3")
                        holder.mylistItemButton.text = "取消找到"
                    }
                }
            }

            holder.mylistItemButton.setOnClickListener { mylistView.turnStatus(id) }
            holder.myListItemButtonOutdate.setOnClickListener {
                bundle.apply {
                    if (lostOrFound == "lost") {
                        putString("lostOrFound", "editLost")
                    } else {
                        putString("lostOrFound", "editFound")
                    }
                    putInt("id", id)
                    putInt("type", detail_type)
                }

                intent.apply {
                    putExtras(bundle)
                    setClass(context, ReleaseActivity::class.java)
                    context?.startActivity(this)
                }
            }

            if (picture != null) {
                Glide.with(context)
                        .load(Utils.getPicUrl(picture[0]))
                        .placeholder(R.drawable.lf_waterfall_nopic)
                        .error(R.drawable.lf_waterfall_nopic)
                        .into(myListItemPic)
            } else {
                Glide.with(context)
                        .load(Utils.getPicUrl("julao.jpg"))
                        .placeholder(R.drawable.lf_waterfall_nopic)
                        .error(R.drawable.lf_waterfall_nopic)
                        .into(myListItemPic)
            }

            itemView.setOnClickListener {
                bundle.putInt("id", id)
                intent.putExtras(bundle)
                intent.setClass(context, DetailActivity::class.java)
                context?.startActivity(intent)
            }

            myListItemPencilTouch.setOnClickListener {
                bundle.apply {
                    if (lostOrFound == "lost") {
                        putString("lostOrFound", "editLost")
                    } else {
                        putString("lostOrFound", "editFound")
                    }
                    putInt(idOfBundle, id)
                    putInt("type", detail_type)
                }

                intent.apply {
                    putExtras(bundle)
                    setClass(context, ReleaseActivity::class.java)
                    context?.startActivity(this)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyListViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.lf2_item_mylist, parent, false)
        return MyListViewHolder(view)
    }

    override fun getItemCount(): Int = myListBean.size
}
