package com.javivg.pruebatecnica.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.persistableBundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.javivg.pruebatecnica.R
import com.javivg.pruebatecnica.api.Repositorio
import com.javivg.pruebatecnica.api.model.ListaPersonajes
import com.javivg.pruebatecnica.api.model.Personajes
import com.javivg.pruebatecnica.databinding.FragmentDetalleBinding
import com.javivg.pruebatecnica.databinding.FragmentListarBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [DetalleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetalleFragment : Fragment() {

    private var _binding: FragmentDetalleBinding? = null
    private val binding get() = _binding!!
    private val args: DetalleFragmentArgs by navArgs()

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
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetalleBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val personaje = args.personajes

        //cambiar todos los datos de un personaje
        binding.apply {
            txtId.text = personaje.id.toString()
            txtNombrePersonaje.text = personaje.name
            txtStatus.text = personaje.status
            Picasso.get().load(personaje.image).into(imgPersonajeDetalle)
            txtTipo.text = personaje.species
            txtGenero.text = personaje.gender
            txtEpisodios.text = personaje.episode.size.toString()
            txtOrigen.text = personaje.origin.name
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
