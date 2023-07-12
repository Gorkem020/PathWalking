package com.example.pathwalkings.view

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pathwalkings.data.Path
import com.example.pathwalkings.R
import com.example.pathwalkings.adapter.PathAdapter
import com.example.pathwalkings.databinding.FragmentTittleImageBinding



@Suppress("UNREACHABLE_CODE")
class TittleImageFragment : Fragment(), AdapterView.OnItemSelectedListener {
    private var          binding      : FragmentTittleImageBinding? = null
    private lateinit var pathArrayList : ArrayList<Path>
    private  lateinit var adapter       : PathAdapter


    lateinit var toggle: ActionBarDrawerToggle

    lateinit  var imageId    : Array<Int>
    lateinit  var heading    : Array<String>
    lateinit var description : Array<String>
    lateinit var camp : Array<Int>



    val signInFragment = SignInFragment()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTittleImageBinding.inflate(inflater,container,false)

        return binding!!.root


    }
    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    pathArrayList = arrayListOf<Path>()



        toggle = ActionBarDrawerToggle(requireContext() as Activity?,binding!!.drawerLayout,0,0)
        binding?.drawerLayout?.addDrawerListener(toggle)
        toggle.syncState()
        binding?.navigationView?.setNavigationItemSelectedListener {
           // it.isChecked = true
          when(it.itemId){

              R.id.action_logOut -> {setCurrentFragment(signInFragment)
              Toast.makeText(requireContext() as Activity?,"Çıkış yapıldı",Toast.LENGTH_SHORT).show()}
          }
          true

       }
        binding?.spPath?.onItemSelectedListener = this





    }
     @SuppressLint("SuspiciousIndentation")
     private fun setCurrentFragment(fragment : Fragment) {

       val   fragmentManager = (activity as FragmentActivity).supportFragmentManager
         fragmentManager.beginTransaction().apply {
             replace(R.id.fragmentContainerView,signInFragment)
             commit()

         }
     }
    @SuppressLint("ResourceType")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

       if (toggle.onOptionsItemSelected(item)){
           return true
       }
       return super.onOptionsItemSelected(item)
    }

    private fun arrayLists(position: Int) {

    }



    @SuppressLint("SuspiciousIndentation")
    private fun getPathData() {
      for ( position in imageId.indices){
          val path = Path(imageId[position],heading[position],description[position],camp[position])
            pathArrayList.add(path)

      }
         adapter = PathAdapter(pathArrayList)
        binding!!.recyclerview.adapter = adapter
        adapter.setOnClickListener(object : PathAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
         val action =
             TittleImageFragmentDirections.actionTittleImageFragmentToDescriptionPathFragment(
                 pathArrayList[position].imageVPath, pathArrayList[position].heading,
                 pathArrayList[position].description,pathArrayList[position].camp
             )
                findNavController().navigate(action)

            }

        })

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        if ( parent?.getItemAtPosition(position) == "KARADENİZ BÖLGESİ" ) {


            imageId = arrayOf(
                R.drawable.kackar,
                R.drawable.suluklu_gol
            )

            heading = arrayOf(
                "Kaçkar Dağları",
                "Sülüklü Göl"
            )
            description = arrayOf(
                getString(R.string.kackar_dagi),
                getString(R.string.suluklu_gol)
            )

            camp = arrayOf(
                R.drawable.kackar_camp,
                R.drawable.suluklu_camp)

            binding!!.recyclerview.setHasFixedSize(true)
            binding!!.recyclerview.layoutManager = LinearLayoutManager(requireContext())
//
            pathArrayList.clear()
            getPathData()


        }
        if(parent?.getItemAtPosition(position) == "AKDENİZ BÖLGESİ"){

            imageId = arrayOf(
                R.drawable.likyax,
                R.drawable.aladaglar,
                R.drawable.frig_x,
                R.drawable.belemedik_vadisi,
                R.drawable.adana_kapikaya_kanyonu,
                R.drawable.akdag_zirve_yuruyusu
            )

            heading = arrayOf(
                "Likya Yolu",
                "Aladağlar",
                "Frig Vadisi",
                "Belemedik Vadisi",
                "Kapıkaya Kanyonu",
                "Akdağ Zirve Yürüyüşü"
            )
            description = arrayOf(
                getString(R.string.likyadesc1),
                getString(R.string.aladaglar),
                getString(R.string.frig_vadisi),
                getString(R.string.belemedik),
                getString(R.string.kapikaya_kanyonu),
                getString(R.string.akdag_yuruyusu)
            )
            camp = arrayOf(
                R.drawable.x,
                R.drawable.aladaglar_camp,
                R.drawable.frig_camp,
                R.drawable.belemedik_camp,
                R.drawable.kackar_camp,
                R.drawable.akdag_camp
            )

            binding!!.recyclerview.setHasFixedSize(true)
            binding!!.recyclerview.layoutManager = LinearLayoutManager(requireContext())
//
            pathArrayList.clear()
            getPathData()
        }
        if(parent?.getItemAtPosition(position) == "İÇ ANADOLU BÖLGESİ"){

            imageId = arrayOf(
                R.drawable.aladaglar,
                R.drawable.frig_x,
                R.drawable.ihlara,
            )

            heading = arrayOf(
                "Aladağlar",
                "Frig Vadisi",
                "Ihlara Vadisi",

            )
            description = arrayOf(
                getString(R.string.aladaglar),
                getString(R.string.frig_vadisi),
                getString(R.string.ihlara_vadisi)
            )
            camp = arrayOf(
                R.drawable.aladaglar_camp,
                R.drawable.frig_camp,
                R.drawable.ihlara_camp
            )

            binding!!.recyclerview.setHasFixedSize(true)
            binding!!.recyclerview.layoutManager = LinearLayoutManager(requireContext())
//
            pathArrayList.clear()
            getPathData()
        }
        if(parent?.getItemAtPosition(position) == "GÜNEYDOĞU ANADOLU BÖLGESİ"){

            imageId = arrayOf(
                R.drawable.diyarbakir_surlari,
                R.drawable.dara_antik_kenti,
                R.drawable.botan_vadisi,
                R.drawable.hasankeyf

            )

            heading = arrayOf(
                "Diyarbakır Surları(Kalesi)",
                "Dara Antik Kenti",
                "Botan Vadisi",
                "Hasankeyf"

            )
            description = arrayOf(
                getString(R.string.diyarbakir_surlari),
                getString(R.string.dara_antik_kenti),
                getString(R.string.botan_vadisi),
                getString(R.string.hasankeyf)

            )
            camp = arrayOf(
                R.drawable.x,
                R.drawable.dara_camp,
                R.drawable.x,
                R.drawable.hasankyef_camp
            )

            binding!!.recyclerview.setHasFixedSize(true)
            binding!!.recyclerview.layoutManager = LinearLayoutManager(requireContext())
//
            pathArrayList.clear()
            getPathData()
        }
        if(parent?.getItemAtPosition(position) == "DOĞU ANADOLU BÖLGESİ"){

            imageId = arrayOf(
                R.drawable.agri_dagi,
                R.drawable.cilodagi

            )

            heading = arrayOf(
                "Ağrı Dağı",
                "Cilo Dağı"

            )
            description = arrayOf(
                getString(R.string.agri_dagi),
                getString(R.string.cilo_dagi)
            )

            camp = arrayOf(
                R.drawable.agri_campp,
                R.drawable.cilo_campp

            )

            binding!!.recyclerview.setHasFixedSize(true)
            binding!!.recyclerview.layoutManager = LinearLayoutManager(requireContext())
//
            pathArrayList.clear()
            getPathData()
        }
        if(parent?.getItemAtPosition(position) == "MARMARA BÖLGESİ"){

            imageId = arrayOf(
                R.drawable.buyukada,
                R.drawable.heybeliada,
                R.drawable.belgrad_ormani,
                R.drawable.polonezkoy
            )

            heading = arrayOf(
                "Büyükada",
                "Heybeliada",
                "Belgrad Ormanı",
                "Polenezköy"
            )
            description = arrayOf(
                getString(R.string.buyukada),
                getString(R.string.heybeliada),
                getString(R.string.belgrad),
                getString(R.string.polenezkoy)
            )
            camp = arrayOf(
                R.drawable.buyukada_camp,
                R.drawable.heybeliada_camp,
                R.drawable.belgrad_camp,
                R.drawable.polonezkoy_camp
            )

            binding!!.recyclerview.setHasFixedSize(true)
            binding!!.recyclerview.layoutManager = LinearLayoutManager(requireContext())
//
            pathArrayList.clear()
            getPathData()
        }
        if(parent?.getItemAtPosition(position) == "EGE BÖLGESİ"){

            imageId = arrayOf(
                R.drawable.likyax,
                R.drawable.gokceada,
                R.drawable.dilek_yarimadasi
            )

            heading = arrayOf(
                "Likya Yolu (Fethiye - Kelebekler Vadisi - Kaş - Ovacık )",
               "Gökçeada",
                "Dilek Yarımada - Menderes Deltası"
            )
            description = arrayOf(
                getString(R.string.likyadesc1),
                getString(R.string.gokceada),
                getString(R.string.dilek_yarimadasi)
            )
            camp = arrayOf(
                R.drawable.x,
                R.drawable.x,
                R.drawable.dilekyarimada_camp
            )

            binding!!.recyclerview.setHasFixedSize(true)
            binding!!.recyclerview.layoutManager = LinearLayoutManager(requireContext())
//
            pathArrayList.clear()
            getPathData()
        }
        if (parent?.getItemAtPosition(position) == ""){
            onNothingSelected(parent)
        }




    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

         imageId = arrayOf(
             R.drawable.likyax,
             R.drawable.aladaglar,
             R.drawable.frig_x,
             R.drawable.agri_dagi,
             R.drawable.ihlara,
             R.drawable.kackar,
             R.drawable.suluklu_gol,
             R.drawable.cilodagi,
             R.drawable.buyukada,
             R.drawable.belemedik_vadisi,
             R.drawable.adana_kapikaya_kanyonu,
             R.drawable.akdag_zirve_yuruyusu,
             R.drawable.ovacik_dagi,
             R.drawable.gokceada,
             R.drawable.belgrad_ormani,
             R.drawable.polonezkoy,
             R.drawable.diyarbakir_surlari,
             R.drawable.dara_antik_kenti,
             R.drawable.botan_vadisi,
             R.drawable.hasankeyf
         )



        heading = arrayOf(
            "Likya Yolu",
            "Aladağlar",
            "Frig Vadisi",
            "Ağrı Dağı",
            "Ihlara Vadisi",
            "Kaçkar Dağları",
            "Sülüklü Göl",
            "Cilo Dağı",
            "Büyükada",
           "Belemedik - Hacıkırı (Kıralan) Çakıt Vadisi",
            "Kapikaya Kanyonu - Varda Köprüsü",
            "Adana Kapıkaya Kanyonu",
            "Akdağ Zirve Yürüyüşü",
            "Ovacık Dağı Zirve Yürüyüşü",
            "Gökçeada",
            "Belgrad Ormanı",
            "Polenezköy",
            "Diyarbakır Surları(Kalesi)",
            "Dara Antik Kenti",
            "Botan Vadisi",
            "Hasankeyf"
        )

         description = arrayOf(
             getString(R.string.likyadesc1),
             getString(R.string.aladaglar),
             getString(R.string.frig_vadisi),
             getString(R.string.agri_dagi),
             getString(R.string.ihlara_vadisi),
             getString(R.string.kackar_dagi),
             getString(R.string.suluklu_gol),
             getString(R.string.cilo_dagi),
            getString(R.string.buyukada),
            getString(R.string.belemedik),
            getString(R.string.kapikaya_kanyonu),
            getString(R.string.akdag_yuruyusu),
            getString(R.string.ovacik_yuruyusu),
            getString(R.string.gokceada),
             getString(R.string.belgrad),
             getString(R.string.polenezkoy),
             getString(R.string.diyarbakir_surlari),
             getString(R.string.dara_antik_kenti),
             getString(R.string.botan_vadisi),
             getString(R.string.hasankeyf)

             )

        camp = arrayOf(
            R.drawable.x,
            R.drawable.aladaglar_camp,
            R.drawable.frig_camp,
            R.drawable.agri_campp,
            R.drawable.ihlara_camp,
            R.drawable.kackar_camp,
            R.drawable.suluklu_camp,
            R.drawable.cilo_campp,
            R.drawable.buyukada_camp,
            R.drawable.belemedik_camp,
            R.drawable.kapikaya_camp,
            R.drawable.akdag_camp,
            R.drawable.x,
            R.drawable.x,
            R.drawable.belgrad_camp,
            R.drawable.polonezkoy_camp,
            R.drawable.x,
            R.drawable.dara_camp,
            R.drawable.x,
            R.drawable.hasankyef_camp

        )

        binding!!.recyclerview.setHasFixedSize(true)
        binding!!.recyclerview.layoutManager = LinearLayoutManager(requireContext())

        getPathData()

    }


}