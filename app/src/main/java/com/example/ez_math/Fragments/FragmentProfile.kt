package com.example.ez_math.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.ez_math.EditProfileActivity
import com.example.ez_math.LoginActivity
import com.example.ez_math.R
import com.google.firebase.auth.FirebaseAuth
import java.text.SimpleDateFormat
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentProfile.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentProfile : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val student = com.example.ez_math.modhel.Student

    private lateinit var tvNamaLengkap: TextView
    private lateinit var tvTanggal: TextView
    private lateinit var tvKelas: TextView
    private lateinit var tvTotalBelajar: TextView
    private lateinit var tvTotalXP: TextView
    private lateinit var tvTotalLatihan:TextView
    private lateinit var tvNamaSekolah: TextView
    private lateinit var tvEmail: TextView
    private lateinit var  tvTanggalahir: TextView
    private lateinit var tvJenisKelamin:TextView


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
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        // Inflate the layout for this fragment
        val btnEdit = view.findViewById<TextView>(R.id.btnEdit)
        btnEdit.setOnClickListener {
            startActivity(Intent(context, EditProfileActivity::class.java))
        }
        val btnLogout = view.findViewById<TextView>(R.id.btnLogout)
        btnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()

            val intent = Intent(context, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

        findView(view)


        tvNamaLengkap.setText(student.namaLengkap)
        tvTanggal.setText(getCurrentDate())
        tvKelas.setText("Kelas ${student.kelas}")
        tvTotalBelajar.setText(student.totalBelajar.toString())
        tvTotalXP.setText(student.totalXp.toString())
        tvTotalLatihan.setText(student.totalLatihan.toString())
        tvNamaSekolah.setText(student.namaSekolah)
        tvEmail.setText(student.email)
        tvTanggalahir.setText(student.tanggalLahir)
        tvJenisKelamin.setText(student.jenisKelamin)
        return view
    }

    private fun findView(view: View){
        tvNamaLengkap = view.findViewById(R.id.tvNamaLengkap)
        tvTanggal = view.findViewById(R.id.tvTanggal)
        tvKelas = view.findViewById(R.id.tvKelas)
        tvTotalBelajar = view.findViewById(R.id.tvTotalBelajar)
        tvTotalXP = view.findViewById(R.id.tvTotalXP)
        tvTotalLatihan = view.findViewById(R.id.tvTotalLatihan)
        tvNamaSekolah = view.findViewById(R.id.tvNamaSekolah)
        tvEmail = view.findViewById(R.id.tvEmail)
        tvTanggalahir = view.findViewById(R.id.tvTanggalLahir)
        tvJenisKelamin = view.findViewById(R.id.tvJenisKelamin)

    }

    private fun getCurrentDate(): String{
        val time = Calendar.getInstance().time
        val formatter = SimpleDateFormat("yyyy-MM-dd ")
        val current = formatter.format(time)
        return current
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentProfile.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentProfile().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}