package com.example.pathwalkings.view

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.pathwalkings.R
import com.example.pathwalkings.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpFragment : Fragment() {

    private lateinit var auth : FirebaseAuth
    private lateinit var binding : FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSignUpBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth
        binding.btnSignUp.setOnClickListener {
            val email = binding.etEmailSignup.text.toString()
            val password = binding.etPasswordSignUp.text.toString()
            if (checkFieldSignUp()){
                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
                    if (it.isSuccessful){
                        auth.signOut()
                        Toast.makeText(requireContext(),"Kullanıcı kaydedildi",Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_signUpFragment_to_tittleImageFragment)
                    }
                }
            }
        }

    }

    private fun checkFieldSignUp(): Boolean{
        val email = binding.etEmailSignup.text.toString()
        val password = binding.etPasswordSignUp.text.toString()
        if (binding.etEmailSignup.text.toString() == ""){
            binding.textInputLayoutEmailSignUp.error = "Bu alanı boş bırakmayınız."
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.textInputLayoutEmailSignUp.error = "Email formatında giriniz."
            return false
        }
        if (binding.etPasswordSignUp.text.toString() == ""){
            binding.textInputLayoutPasswordSignUp.error = "Bu alanı boş bırakmayınız."
            binding.textInputLayoutPasswordSignUp.errorIconDrawable = null
            return false
        }
        if (binding.etPasswordSignUp.length() <=6){
            binding.textInputLayoutPasswordSignUp.error = "Şifre 6 karakterden az olamaz."
            binding.textInputLayoutPasswordSignUp.errorIconDrawable = null
            return false
        }
        if (binding.etPasswordConfirm.text.toString() == ""){
            binding.textInputLayoutConfirmPasswordSignUp.error = "Bu alanı boş bırakmayınız."
            binding.textInputLayoutConfirmPasswordSignUp.errorIconDrawable = null
            return false
        }
        if (binding.etPasswordSignUp.text.toString() != binding.etPasswordConfirm.text.toString()){
            binding.textInputLayoutPasswordSignUp.error = "Parola eşleşmedi."
            binding.textInputLayoutConfirmPasswordSignUp.error = "Parola eşleşmedi."
            return false

        }


        return true
    }

}