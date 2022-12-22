package com.example.ez_math.Fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.ez_math.R
import com.example.ez_math.modhel.Student
import com.example.ez_math.LatihanActivity as LatihanActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Home.newInstance] factory method to
 * create an instance of this fragment.
 */
class Home : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var student = com.example.ez_math.modhel.Student

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val ivKelas1 = view.findViewById<ImageView>(R.id.ivKelas1)
        val ivKelas2 = view.findViewById<ImageView>(R.id.ivKelas2)
        val ivKelas3 = view.findViewById<ImageView>(R.id.ivKelas3)
        val ivKelas4 = view.findViewById<ImageView>(R.id.ivKelas4)
        val ivKelas5 = view.findViewById<ImageView>(R.id.ivKelas5)
        val ivKelas6 = view.findViewById<ImageView>(R.id.ivKelas6)


        ivKelas1.setOnClickListener {
            fLatihan("kelas1")
        }
        ivKelas2.setOnClickListener {
            fLatihan("kelas2")
        }
        ivKelas3.setOnClickListener {
            fLatihan("kelas3")
        }
        ivKelas4.setOnClickListener {
            fLatihan("kelas4")
        }
        ivKelas5.setOnClickListener {
            fLatihan("kelas5")
        }
        ivKelas6.setOnClickListener {
            fLatihan("kelas6")
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tvNamaUser = view.findViewById<TextView>(R.id.tvNamaUser)
        tvNamaUser.setText(student.namaPengguna)
    }

    fun fLatihan(kelas: String){
        val intentKeLatihan = Intent(context, LatihanActivity::class.java)
        intentKeLatihan.putExtra("kelas", kelas)
        startActivity(intentKeLatihan)
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Home.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Home().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }



}