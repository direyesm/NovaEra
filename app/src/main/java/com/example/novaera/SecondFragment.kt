package com.example.novaera

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.novaera.model.NovaViewModel
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.android.synthetic.main.item_phone_list.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    lateinit var mViewModel : NovaViewModel
    var mId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            mId = arguments?.getString("id") ?: ""
        }
        mViewModel = ViewModelProvider(this).get(NovaViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewModel.getDetails(id).observe(viewLifecycleOwner, Observer {
            if (it != null){
                txtNombre.text = it.name
                Glide.with(this).load(it.image).into(imageView)
                txtDescrip.text = it.description
                textView5.text = it.price.toString()
                textView7.text = it.lastPrice.toString()
            }
        })

    }
}