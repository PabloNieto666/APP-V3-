package com.example.messi.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.messi.Games
import com.example.messi.R
import com.example.messi.databinding.ItemGamesBinding

class GamesViewHolder(view:View):RecyclerView.ViewHolder(view) {

    val binding = ItemGamesBinding.bind(view) // Vincula la vista del elemento del juego con la clase de enlace (binding)

    fun render(GamesModel: Games, onClickListener: (Games) -> Unit, onClickDelete: (Int) -> Unit) {
        // Vincula los datos del modelo de juego con los elementos visuales en el GamesViewHolder

        // Configuración del TextView para mostrar el nombre del juego
        binding.tvjuego.text = GamesModel.juego

        // Configuración del TextView para mostrar el distribuidor del juego
        binding.tvdistribuidor.text = GamesModel.distribuidor

        // Configuración del TextView para mostrar el precio del juego
        binding.tvprecio.text = GamesModel.precio

        // Carga de la imagen del juego utilizando la biblioteca Glide
        Glide.with(binding.ivGames.context).load(GamesModel.foto).into(binding.ivGames)

        // Configuración del clic en la imagen del juego
        binding.ivGames.setOnClickListener { onClickListener(GamesModel) }

        // Configuración del clic en el elemento completo del juego
        itemView.setOnClickListener { Toast.makeText(itemView.context, "Super Mario Bros.", Toast.LENGTH_SHORT) }

        // Configuración del clic en el botón de eliminar el juego
        binding.btnBorrar.setOnClickListener { onClickDelete(adapterPosition) }
    }
}