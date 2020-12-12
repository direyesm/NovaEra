package com.example.novaera

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
    var mId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            mId = arguments?.getInt("id") ?: 0
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
        Log.d("NNN", mId.toString())

        mViewModel.getDetails(mId).observe(viewLifecycleOwner, Observer {
            if (it != null){
                textView.text = it.name
                Glide.with(this).load(it.image).into(imageView)
                textView3.text = it.description
                textView5.text = it.price.toString()
                textView7.text = it.lastPrice.toString()

            }

            fun email() {

                val intent = Intent(Intent.ACTION_SEND)
                intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("info@novaera.cl"))
                intent.putExtra(Intent.EXTRA_SUBJECT, "Consulta ${it.name} , id : ${it.id} ")
                intent.putExtra(Intent.EXTRA_TEXT, "“Hola\n" +
                        "Vi la propiedad ${it.name} de código ${it.id} y me gustaría que me\n" +
                        "contactaran a este correo o al siguiente número _________\n" +
                        "Quedo atento.”")
                intent.type = "mensaje"
                startActivity(Intent.createChooser(intent, "Contactar area de venta"))
            }

            fav.setOnClickListener {
                email()
            }
        })



    }









}