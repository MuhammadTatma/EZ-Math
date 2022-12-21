package com.example.ez_math.Fragments

//import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.ExpandableListView.OnChildClickListener
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.ez_math.R
import com.example.ez_math.adapter.ExpandableListViewAdapter


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentBelajar.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentBelajar : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var listViewAdapter: ExpandableListAdapter
    private lateinit var expandableListView: ExpandableListView
    private lateinit var listKelas: List<String>
    private lateinit var listMateri: HashMap<String, List<String>>

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_belajar, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentBelajar.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentBelajar().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        expandableListView = view.findViewById<ExpandableListView>(R.id.expandableViewMateriKelas)

        createListData()

        listViewAdapter = ExpandableListViewAdapter(requireContext(), listKelas, listMateri)
        expandableListView.setAdapter(listViewAdapter)

        // Listview on child click listener
        expandableListView.setOnChildClickListener(OnChildClickListener { parent, v, groupPosition, childPosition, id ->
            val arguments = Bundle()
            arguments.putString("materi_name", listMateri.get(listKelas.get(groupPosition))?.get(childPosition))

            val fragment: Fragment = DetailMateri()
            fragment.arguments = arguments

            val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragmentContainer, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
            false
        })
    }

    private fun createListData(){
        listKelas = ArrayList()
        listMateri = HashMap()

        (listKelas as ArrayList<String>).add("Kelas Satu")
        (listKelas as ArrayList<String>).add("Kelas Dua")
        (listKelas as ArrayList<String>).add("Kelas Tiga")
        (listKelas as ArrayList<String>).add("Kelas Empat")
        (listKelas as ArrayList<String>).add("Kelas Lima")
        (listKelas as ArrayList<String>).add("Kelas Enam")

        val materiKelasSatu : MutableList<String> = ArrayList()
        materiKelasSatu.add("Penjumlahan")
        materiKelasSatu.add("Pengurangan")
        materiKelasSatu.add("Perkalian")
        materiKelasSatu.add("Pembagian")

        val materiKelasDua : MutableList<String> = ArrayList()
        materiKelasDua.add("Materi 1")
        materiKelasDua.add("Materi 2")
        materiKelasDua.add("Materi 3")
        materiKelasDua.add("Materi 4")

        val materiKelasTiga : MutableList<String> = ArrayList()
        materiKelasTiga.add("Materi 1")
        materiKelasTiga.add("Materi 2")
        materiKelasTiga.add("Materi 3")
        materiKelasTiga.add("Materi 4")

        val materiKelasEmpat : MutableList<String> = ArrayList()
        materiKelasEmpat.add("Materi 1")
        materiKelasEmpat.add("Materi 2")
        materiKelasEmpat.add("Materi 3")
        materiKelasEmpat.add("Materi 4")

        val materiKelasLima : MutableList<String> = ArrayList()
        materiKelasLima.add("Materi 1")
        materiKelasLima.add("Materi 2")
        materiKelasLima.add("Materi 3")
        materiKelasLima.add("Materi 4")

        val materiKelasEnam : MutableList<String> = ArrayList()
        materiKelasEnam.add("Materi 1")
        materiKelasEnam.add("Materi 2")
        materiKelasEnam.add("Materi 3")
        materiKelasEnam.add("Materi 4")

        listMateri[listKelas[0]] = materiKelasSatu
        listMateri[listKelas[1]] = materiKelasDua
        listMateri[listKelas[2]] = materiKelasTiga
        listMateri[listKelas[3]] = materiKelasEmpat
        listMateri[listKelas[4]] = materiKelasLima
        listMateri[listKelas[5]] = materiKelasEnam



    }
}