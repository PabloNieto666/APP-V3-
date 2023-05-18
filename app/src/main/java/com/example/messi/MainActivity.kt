package com.example.messi

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.messi.adapter.GamesAdapter
import com.example.messi.databinding.ActivityMainBinding
import java.text.FieldPosition

//Clase principal que representa la actividad principal de la aplicación.
class MainActivity : AppCompatActivity() {

    // Variables miembro

    private lateinit var binding: ActivityMainBinding
    private var gamesMutableList: MutableList<Games> =
        GamesProvider.gameFirst.toMutableList()
    private lateinit var adapter: GamesAdapter
    private val llmanager = LinearLayoutManager(this)

    //Método de creación de la actividad.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnAgregar.setOnClickListener{ createGames() }

        configFilter()
        initRecyclerView()
        configSwipe()
    }

    //Configura el SwipeRefreshLayout para permitir el gesto de actualización.

    private fun configSwipe() {
        //binding.swipe.isEnabled = false         desabilita el swipe

        binding.swipe.setColorSchemeResources(R.color.purple, R.color.menta)


        binding.swipe.setOnRefreshListener {
            Log.i("Nintendo", "FUNCIONA :D")
            Handler(Looper.getMainLooper()).postDelayed({
            binding.swipe.isRefreshing = false

            },2000)
        }
    }

    //Configura el EditText para filtrar los juegos en función del texto ingresado.

    private fun configFilter() {
        binding.etFilter.addTextChangedListener { userFilter ->
            val gamesFiltered =
                gamesMutableList.filter { games ->
                    games.juego.lowercase().contains(userFilter.toString().lowercase()) }
            adapter.updateGames(gamesFiltered)
        }
    }
    //Crea un nuevo juego y lo agrega a la lista de juegos en la posición especificada.

    private fun createGames() {
        val games = Games( "juego", "distribuidor", "?$", "https://is4-ssl.mzstatic.com/image/thumb/Purple128/v4/3f/fd/23/3ffd23b7-e48c-ca00-f86b-ecd381132876/source/256x256bb.jpg"
        )
        gamesMutableList.add(index = 2, games)
        adapter.notifyItemInserted(2)
        llmanager.scrollToPositionWithOffset(3, 15)
    }

    //Inicializa el RecyclerView y configura el adaptador.
    private fun initRecyclerView() {
        adapter = GamesAdapter(
            gameList = gamesMutableList,
            onClickListener =  { games -> onItemSelected(games) },
            onClickDelete = { position -> onDeletedItem(position) }
        )
        binding.recyclerGames.layoutManager = llmanager
        binding.recyclerGames.adapter = adapter

    }

    //Maneja el evento cuando se elimina un elemento de la lista.
    private fun onDeletedItem(position: Int) {
        gamesMutableList.removeAt(position)
        adapter.notifyItemRemoved(position)
    }

    //Maneja el evento cuando se selecciona un elemento de la lista.
    private fun onItemSelected(Games: Games) {
        Toast.makeText(this, Games.juego, Toast.LENGTH_SHORT).show()

    }
}