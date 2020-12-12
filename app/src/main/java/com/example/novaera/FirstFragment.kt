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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.novaera.model.NovaRepository
import com.example.novaera.model.NovaViewModel
import com.example.novaera.model.local.adapter.NovaAdapter
import com.example.novaera.model.local.entities.ProductsEnti
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment(), NovaAdapter.phoneData{

    lateinit var mViewModel: NovaViewModel
    lateinit var mAdapter:  NovaAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(NovaViewModel::class.java)
        mAdapter = NovaAdapter(this)
    }


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = recyclerView
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)

        mViewModel.phoneLivedata.observe(viewLifecycleOwner, Observer {
            mAdapter.updateAdapter(it)
        })

    }

    override fun passPhone(phone: ProductsEnti) {
        val mBundle = Bundle()
        mBundle.putInt("id", phone.id)
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, mBundle)
    }
}