package com.yemen.oshopping.setting

import android.app.PendingIntent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.yemen.oshopping.R
import com.yemen.oshopping.viewmodel.OshoppingViewModel
import kotlinx.android.synthetic.main.fragment_add_category.*


class SettingFragment : Fragment() {
    lateinit var adminTV: CardView
    lateinit var contactUsTV: CardView
    lateinit var aboutUsTV: CardView
    lateinit var myProductTV: CardView
    lateinit var myAccountTV: CardView
    lateinit var signOutTV: Button
    lateinit var signUpTV: Button
    lateinit var chatTv: CardView
    lateinit var close: ImageButton
    lateinit var oshoppingViewModel: OshoppingViewModel
    //yemenoshopping@gmail.com
    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAuth = FirebaseAuth.getInstance()
        oshoppingViewModel = ViewModelProviders.of(this).get(OshoppingViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_setting, container, false)
        myAccountTV=view.findViewById(R.id.my_account)
        myProductTV=view.findViewById(R.id.my_products)
        aboutUsTV=view.findViewById(R.id.about_us)
        contactUsTV=view.findViewById(R.id.contact_us)
        signOutTV=view.findViewById(R.id.sign_out)
        signUpTV=view.findViewById(R.id.sign_up)
        adminTV=view.findViewById(R.id.admin_page)
        chatTv=view.findViewById(R.id.chat)
        close=view.findViewById(R.id.bt_close)

        if (oshoppingViewModel.getStoredEmail().equals("yemenoshopping@gmail.com"))
        {
            adminTV.visibility = View.VISIBLE
           // myProductTV.visibility = View.GONE
        }
        if(oshoppingViewModel.getStoredEmail().equals("none")){
            //myProductTV.visibility = View.GONE
            adminTV.visibility = View.GONE
            signOutTV.visibility=View.GONE
            signUpTV.visibility=View.VISIBLE
        }

        myProductTV.setOnClickListener {
            if (oshoppingViewModel.getStoredEmail().equals("yemenoshopping@gmail.com"))
            {
                Toast.makeText(requireContext(), "You are an admin", Toast.LENGTH_SHORT).show()
            }
            else if (oshoppingViewModel.getStoredEmail().equals("none"))
            {
                Navigation.findNavController(view).navigate(R.id.action_settingFragment_to_signUp2)
            }
            else
            Navigation.findNavController(view).navigate(R.id.action_settingFragment_to_myProductFragment)
        }
        adminTV.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_settingFragment_to_adminFragment)
        }
        myAccountTV.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_settingFragment_to_showUserFragment)
        }
        aboutUsTV.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_settingFragment_to_aboutUsFragment)
        }
        contactUsTV.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_settingFragment_to_contactUsFragment)
        }
        signOutTV.setOnClickListener {
            mAuth.signOut()
            oshoppingViewModel.apply {
                setUserId()
                setUserEmail()
                setQuery()
            }

            Navigation.findNavController(view).navigate(R.id.action_settingFragment_to_loginScreen)
           //write here the sign out code
        }
        signUpTV.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_settingFragment_to_signUp2)
        }

        chatTv.setOnClickListener {
            if(oshoppingViewModel.getStoredUserId()==-1) {
                Toast.makeText(requireContext(), "You must create an account", Toast.LENGTH_SHORT).show()
                Navigation.findNavController(view).navigate(R.id.action_settingFragment_to_signUp2)
            }
            else{
                Navigation.findNavController(view).navigate(R.id.action_settingFragment_to_usersActivity)
            }

        }
        close.setOnClickListener {
            activity?.onBackPressed()
        }


    return view
    }


}