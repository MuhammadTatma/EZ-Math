package com.example.ez_math.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Debug
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.example.ez_math.R


class ExpandableListViewAdapter internal constructor(private val context: Context, private val listKelas:  List<String>, private val listMateri : HashMap<String,List<String>>) : BaseExpandableListAdapter() {
    override fun getGroupCount(): Int {
        return listKelas.size
    }

    override fun getChildrenCount(p0: Int): Int {
        return this.listMateri[this.listKelas[p0]]!!.size
    }

    override fun getGroup(p0: Int): Any {
        return listKelas[p0]
    }

    override fun getChild(p0: Int, p1: Int): Any {
        return this.listMateri[this.listKelas[p0]]!![p1]
    }

    override fun getGroupId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getChildId(p0: Int, p1: Int): Long {
        return p1.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(p0: Int, p1: Boolean, p2: View?, p3: ViewGroup?): View {
        var convertView = p2
        var namaKelas = getGroup(p0) as String

        if(convertView == null){
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.header_group_kelas_belajar, null)
        }

        val tvKelas = convertView!!.findViewById<TextView>(R.id.tvNamaKelas)

        //        if group selected
        if(p1){
            when(namaKelas){
                "Kelas Satu"  -> tvKelas.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(context, R.drawable.logo_kelas_satu), null, ContextCompat.getDrawable(context, R.drawable.ic_baseline_arrow_drop_up_color2_24), null )
                "Kelas Dua" -> tvKelas.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(context, R.drawable.logo_kelas_dua), null, ContextCompat.getDrawable(context, R.drawable.ic_baseline_arrow_drop_up_color2_24), null )
                "Kelas Tiga" -> tvKelas.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(context, R.drawable.logo_kelas_tiga), null,ContextCompat.getDrawable(context, R.drawable.ic_baseline_arrow_drop_up_color2_24), null )
                "Kelas Empat" -> tvKelas.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(context, R.drawable.logo_kelas_empat), null,ContextCompat.getDrawable(context, R.drawable.ic_baseline_arrow_drop_up_color2_24), null )
                "Kelas Lima" -> tvKelas.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(context, R.drawable.logo_kelas_lima), null, ContextCompat.getDrawable(context, R.drawable.ic_baseline_arrow_drop_up_color2_24), null )
                "Kelas Enam" -> tvKelas.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(context, R.drawable.logo_kelas_enam), null,ContextCompat.getDrawable(context, R.drawable.ic_baseline_arrow_drop_up_color2_24), null )

            }
        }else{
            when(namaKelas){
                "Kelas Satu"  -> tvKelas.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(context, R.drawable.logo_kelas_satu), null, ContextCompat.getDrawable(context, R.drawable.ic_baseline_arrow_drop_down_color2_24), null )
                "Kelas Dua" -> tvKelas.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(context, R.drawable.logo_kelas_dua), null, ContextCompat.getDrawable(context, R.drawable.ic_baseline_arrow_drop_down_color2_24), null )
                "Kelas Tiga" -> tvKelas.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(context, R.drawable.logo_kelas_tiga), null,ContextCompat.getDrawable(context, R.drawable.ic_baseline_arrow_drop_down_color2_24), null )
                "Kelas Empat" -> tvKelas.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(context, R.drawable.logo_kelas_empat), null,ContextCompat.getDrawable(context, R.drawable.ic_baseline_arrow_drop_down_color2_24), null )
                "Kelas Lima" -> tvKelas.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(context, R.drawable.logo_kelas_lima), null, ContextCompat.getDrawable(context, R.drawable.ic_baseline_arrow_drop_down_color2_24), null )
                "Kelas Enam" -> tvKelas.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(context, R.drawable.logo_kelas_enam), null,ContextCompat.getDrawable(context, R.drawable.ic_baseline_arrow_drop_down_color2_24), null )
            }

        }

        tvKelas.setText(namaKelas)


        return convertView
    }

    override fun getChildView(p0: Int, p1: Int, p2: Boolean, p3: View?, p4: ViewGroup?): View {
        var convertView = p3
        val namaMateri = getChild(p0, p1) as String

        if(convertView == null){
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.topic_materi_belajar, null)
        }

        val tvNamaMateri = convertView!!.findViewById<TextView>(R.id.tvNamaMateri)
        tvNamaMateri.setText(namaMateri)

        return convertView
    }

    override fun isChildSelectable(p0: Int, p1: Int): Boolean {
        return true
    }

}