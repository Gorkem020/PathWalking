package com.example.pathwalkings.view

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.pathwalkings.R
import com.example.pathwalkings.databinding.FragmentSignInBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase




class SignInFragment : Fragment() {

    private lateinit var auth : FirebaseAuth
    private lateinit var binding: FragmentSignInBinding




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

       binding = FragmentSignInBinding.inflate(inflater,container,false)


        return binding.root
    }

    override  fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
          auth = Firebase.auth



        binding.btnSignIn.setOnClickListener {
            val email = binding.etEmailSignIn.text.toString()
            val password = binding.etPasswordSignIn.text.toString()
            if (checkAllField()){   auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
              if (it.isSuccessful){
                  //if successfuly already sign in
                  Toast.makeText(requireContext(),"Giriş yapıldı",Toast.LENGTH_SHORT).show()
                  //go to another fragment
                  findNavController().navigate(R.id.action_signInFragment_to_tittleImageFragment)




              }else{
                  //not sin in
                  Log.e("error",it.exception.toString())
              }
          }
          }
          }
        binding?.tvPasswordForgot?.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_forgotPasswordFragment)

        }

          binding.tvCreatUser.setOnClickListener {
              findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
          }
      }
    private fun checkAllField():Boolean{
        val email = binding.etEmailSignIn.text.toString()
        val password = binding.etPasswordSignIn.text.toString()
        if (binding.etEmailSignIn.text.toString() == ""){
            binding.textInputLayoutEmailSignIn.error = "Bu alanı boş bırakmayınız."
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.textInputLayoutEmailSignIn.error = "Email formatında giriniz."
            return false
        }
        if (binding.etPasswordSignIn.text.toString() == ""){
            binding.textInputLayoutPasswordSignIn.error = "Bu alanı boş bırakmayınız."
            binding.textInputLayoutPasswordSignIn.errorIconDrawable = null
            return false
        }
        if (binding.etPasswordSignIn.length() <= 6){
            binding.textInputLayoutPasswordSignIn.error = "Şifre 6 karakterden az olamaz."
            binding.textInputLayoutPasswordSignIn.errorIconDrawable = null
            return false
        }

        return true
    }
    private fun checkFieldEmail(view: View): Boolean{
        val etResetPasswordEmail : TextInputEditText = view.findViewById(R.id.etResetPasswordEmail)
        val textInputLayoutForgotPasswordEmail : TextInputLayout = view.findViewById(R.id.textInputLayoutForgotPasswordEmail)

        val email = etResetPasswordEmail.text.toString()
        if (etResetPasswordEmail.text.toString() == ""){
           textInputLayoutForgotPasswordEmail.error = "Bu alanı boş bırakmayıınız."
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            textInputLayoutForgotPasswordEmail.error = "Email formatında giriniz."
            return false

        }

        return true
    }


    }


