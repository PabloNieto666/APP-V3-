package com.example.messi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.messi.Games
import com.example.messi.R

class GamesAdapter(
    private var gameList: List<Games>,  // Lista de juegos
    private val onClickListener: (Games) -> Unit,  // Función de clic en un juego
    private val onClickDelete: (Int) -> Unit  // Función de clic en eliminar un juego
) : RecyclerView.Adapter<GamesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesViewHolder {
        // Crea una instancia de GamesViewHolder cuando se necesita mostrar un nuevo elemento en la lista
        val layoutInflater = LayoutInflater.from(parent.context)
        return GamesViewHolder(layoutInflater.inflate(R.layout.item_games, parent, false))
    }

    override fun onBindViewHolder(holder: GamesViewHolder, position: Int) {
        // Vincula los datos de un juego con los elementos visuales en el GamesViewHolder
        val item = gameList[position]
        holder.render(item, onClickListener, onClickDelete)
    }

    override fun getItemCount(): Int {
        // Devuelve la cantidad de elementos en la lista de juegos
        return gameList.size
    }

    fun updateGames(gameList: List<Games>) {
        // Actualiza la lista de juegos y notifica al adaptador para refrescar la vista
        this.gameList = gameList
        notifyDataSetChanged()
    }
}


