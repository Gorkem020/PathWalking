package com.example.pathwalkings.view

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.example.pathwalkings.R
import com.example.pathwalkings.databinding.FragmentForgotPasswordBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ForgotPasswordFragment : Fragment() {

    private lateinit var auth    : FirebaseAuth
    private lateinit var binding : FragmentForgotPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentForgotPasswordBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth
        binding.btnResetPassword.setOnClickListener {
            val email = binding.etResetPasswordEmail.text.toString()
            if (checkFieldEmail()){
                auth.sendPasswordResetEmail(email).addOnCompleteListener {
                    auth.signOut()

                    val alertD = AlertDialog.Builder(requireContext())
                    alertD.setTitle("Uyarı")
                    alertD.setMessage("Eposta adresine link gönderildi")
                    alertD.setIcon(R.drawable.baseline_check_24)

                    alertD.setPositiveButton("Tamam"){ dialogInterface , i ->
                        Toast.makeText(requireContext(),"Eposta Link Gönderildi", Toast.LENGTH_SHORT).show()


                    }
                    alertD.create().show()

                    findNavController().navigate(R.id.action_forgotPasswordFragment_to_signInFragment)
                }
            }
        }
    }



    private fun checkFieldEmail(): Boolean{
        val email = binding.etResetPasswordEmail.text.toString()
        if (binding.etResetPasswordEmail.text.toString() == ""){
            binding.textInputLayoutForgotPasswordEmail.error = "Bu alanı boş bırakmayıınız."
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.textInputLayoutForgotPasswordEmail.error = "Email formatında giriniz."
            return false

        }

        return true
    }

}