package com.gdsc.drsmart.ui.doctor.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gdsc.drsmart.R
import com.gdsc.drsmart.tools.utils.Base64Utils
import com.gdsc.drsmart.tools.utils.CircularTextView
import com.gdsc.drsmart.ui.doctor.models.posts.Post
import com.gdsc.drsmart.ui.question.QuestionActivity


class QuestionAdapter(
    var context: Context,
    var data: ArrayList<Post>,
    private val is_user: Boolean
) :
    RecyclerView.Adapter<QuestionAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.userNameTxt)
        var desc: TextView = itemView.findViewById(R.id.descTxtView)
        var field: TextView = itemView.findViewById(R.id.fieldTxt)
        var image: ImageView = itemView.findViewById(R.id.postImg)
        var typeAnswer: TextView = itemView.findViewById(R.id.typeAnswer_editText)
        var profileImage: CircularTextView = itemView.findViewById(R.id.profileImage)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        bind(holder, position)
    }

    @SuppressLint("SetTextI18n")
    private fun bind(holder: ViewHolder, position: Int) {
        holder.desc.text = data[position].desc
        holder.field.text = data[position].field
        holder.name.text = data[position].user_name

        //convert base64 to image
        if (data[position].img != null) {
            holder.image.setImageBitmap(Base64Utils.decodeToBitmap(data[position].img))
        } else {
            holder.image.visibility = View.GONE
        }

        holder.typeAnswer.setOnClickListener {
            val i = Intent(context, QuestionActivity::class.java)
            i.putExtra("question", data[position])
            i.putExtra("isUser", is_user)
            context.startActivity(i)
        }
        initProfileImage(holder, position)
    }

    private fun initProfileImage(holder: ViewHolder, position: Int) {
        holder.profileImage.setStrokeWidth(0)
        if (is_user) {
            holder.profileImage.setSolidColor(CircularTextView.colors[0])
        } else {
            holder.profileImage.setSolidColor(CircularTextView.colors[1])
        }

        holder.profileImage.setStrokeColor("#000000")
        holder.profileImage.text = data[position].user_name[0].toString()
    }

    fun addData(data: ArrayList<Post>) {
        val size = this.data.size
        this.data.addAll(data)
        val sizeNew = this.data.size
        notifyItemRangeChanged(size, sizeNew)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount() = data.size
}
