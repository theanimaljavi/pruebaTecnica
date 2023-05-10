package com.javivg.pruebatecnica.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.javivg.pruebatecnica.R
import com.javivg.pruebatecnica.api.model.Personajes
import com.javivg.pruebatecnica.databinding.ListaPersonajesBinding
import com.squareup.picasso.Picasso

class AdaptadorPersonajes: RecyclerView.Adapter<AdaptadorPersonajes.PersonajesViewHolder>(){

    private var listaPersonajes = emptyList<Personajes>()

    class PersonajesViewHolder(private val binding: ListaPersonajesBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(personajes: Personajes){
            binding.nombrePersonaje.text = personajes.name
            Picasso.get().load(personajes.image).into(binding.imgPersonaje)

            //mandar el objeto del recyclerview al fragment de detalle
            itemView.setOnClickListener { view ->
                val action = ListarFragmentDirections.actionListarFragmentToDetalleFragment(personajes)
                view.findNavController().navigate(action)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonajesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListaPersonajesBinding.inflate(layoutInflater, parent, false)
        return PersonajesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonajesViewHolder, position: Int) {
        holder.bind(listaPersonajes[position])

    }

    override fun getItemCount(): Int {
        return listaPersonajes.size
    }


    fun setPersonajes(personajes: List<Personajes>){
        listaPersonajes = personajes
        notifyDataSetChanged()
    }
}