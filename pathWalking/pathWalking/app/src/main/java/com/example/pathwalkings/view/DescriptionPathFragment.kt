package com.example.pathwalkings.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.pathwalkings.R
import com.example.pathwalkings.databinding.FragmentDescriptionPathBinding
import com.example.pathwalkings.data.pathwalk
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task
import com.google.gson.Gson
import java.io.IOException
import java.nio.charset.Charset


class DescriptionPathFragment : Fragment() {

    private  var descBinding : FragmentDescriptionPathBinding ?= null
    private  val args : DescriptionPathFragmentArgs by navArgs()
    private lateinit var toggle: ActionBarDrawerToggle
    //konum için kullanılacaklar
    private  var permissionControl = 0
    private lateinit var flpc : FusedLocationProviderClient
    private lateinit var locationTask : Task<Location>
    val signInFragment = SignInFragment()


       private var heading : String = ""
       private var image : Int=0
       private var desc : String = ""
       override fun onCreateView(
           inflater: LayoutInflater, container: ViewGroup?,
           savedInstanceState: Bundle?
       ): View? {
          descBinding = FragmentDescriptionPathBinding.inflate(inflater,container,false)
           return descBinding!!.root
       }

       @SuppressLint("SuspiciousIndentation")
       override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
           super.onViewCreated(view, savedInstanceState)
           heading = args.heading
           image   = args.imageVPath
           desc    = args.descrption
           descBinding?.apply {
               tvHeadingDesc.text = heading
               tvDesc.text = desc
               ivImage.setImageResource(image)
           }
               toggle = ActionBarDrawerToggle(requireContext() as Activity,descBinding!!.drawerLayout,0,0)
               descBinding?.drawerLayout?.addDrawerListener(toggle)
               toggle.syncState()
               descBinding?.navigationView?.setNavigationItemSelectedListener {
                   when(it.itemId){

                       R.id.action_logOut -> {setCurrentFragment(signInFragment)
                           Toast.makeText(requireContext() as Activity?,"Çıkış yapıldı",Toast.LENGTH_SHORT).show()}
                   }
                   true
               }


           //yetki alındı
           flpc = LocationServices.getFusedLocationProviderClient(requireContext())
           descBinding?.btnPathMap?.setOnClickListener {
               //iznin onaylanıp onaylanmadığına bakar
               permissionControl = ContextCompat.checkSelfPermission(requireContext(),android.Manifest.permission.ACCESS_FINE_LOCATION)

               if (permissionControl != PackageManager.PERMISSION_GRANTED){ //İzin onaylanmadıysa
                   ActivityCompat.requestPermissions(requireContext() as Activity,
                       arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),100)
               }else{ //izin onaylanmışsa


               }
               context?.let {
                   var list = parseJson(it);
                   Log.e("jsontag", "list is ${list}")
                   list?.forEach {
                       if(it.name == heading ){
                        val action =
                            DescriptionPathFragmentDirections.actionDescriptionPathFragmentToMapsFragment(
                                locName = heading,
                                latitude = it.latitude.toFloat(),
                                longtude = it.longitude.toFloat()
                            )
                           findNavController().navigate(action)
       //                    findNavController().navigate(R.id.action_descriptionPathFragment_to_mapsFragment)

                       }
                   }
               }




           }



   }
    @SuppressLint("SuspiciousIndentation")
    private fun setCurrentFragment(fragment : Fragment) {

        val   fragmentManager = (activity as FragmentActivity).supportFragmentManager
        fragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainerView,signInFragment)
            commit()

        }
    }




    fun parseJson(context: Context): pathwalk? {
        val gson = Gson()
        val json: String

        try {
            val inputStream = context.assets.open("path.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()

            json = String(buffer, Charset.forName("UTF-8"))
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }

        return gson.fromJson(json, pathwalk::class.java)
    }

    private fun locationInfo(){
        locationTask.addOnSuccessListener {
            if (it != null) {

            }
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("MissingSuperCall")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == 100){
            permissionControl = ContextCompat.checkSelfPermission(requireContext(),android.Manifest.permission.ACCESS_FINE_LOCATION)

            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

            }else{

            }
        }

    }

}