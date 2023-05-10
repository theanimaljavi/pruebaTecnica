package com.javivg.pruebatecnica.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.javivg.pruebatecnica.R
import com.javivg.pruebatecnica.api.Repositorio
import com.javivg.pruebatecnica.databinding.FragmentListarBinding

/**
 * A simple [Fragment] subclass.
 * Use the [ListarFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListarFragment : Fragment(R.layout.fragment_listar) {

    private var _binding: FragmentListarBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: SharedViewModel by activityViewModels {
        SharedViewModelFactory(
            Repositorio()
        )
    }
    private var adaptador = AdaptadorPersonajes()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        sharedViewModel.getPersonajes(1)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListarBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.recyclerview.layoutManager = StaggeredGridLayoutManager(
            2, StaggeredGridLayoutManager.VERTICAL)

        binding.apply {

            sharedViewModel.listaPersonajes.observe(viewLifecycleOwner, { response ->
                if(response.isSuccessful){
                    adaptador.setPersonajes(response.body()!!.results)
                    txtApiError.visibility = View.GONE
                    recyclerview.visibility = View.VISIBLE
                }else{
                    txtApiError.text = getString(R.string.txt_error, response.code())
                    txtApiError.visibility = View.VISIBLE
                    recyclerview.visibility = View.INVISIBLE
                }
            })
            recyclerview.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            recyclerview.adapter = adaptador
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}