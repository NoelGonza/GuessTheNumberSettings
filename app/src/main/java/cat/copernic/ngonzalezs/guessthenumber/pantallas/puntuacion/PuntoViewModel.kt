package cat.copernic.ngonzalezs.guessthenumber.pantallas.puntuacion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PuntoViewModel(txtFin: String, lvl: Int, tipo: String) : ViewModel() {
    private val _textoFin = MutableLiveData<String>()
    val textoFin: LiveData<String> get() = _textoFin

    private val _tipoJuego = MutableLiveData<String>()
    val tipoJuego: LiveData<String> get() = _tipoJuego

    private val _nivel = MutableLiveData<Int>()
    val nivel: LiveData<Int> get() = _nivel

    private val _juegaOtraVez = MutableLiveData<Boolean>()
    val juegaOtraVez: LiveData<Boolean> get() = _juegaOtraVez

    init {
        _textoFin.value = txtFin
        _tipoJuego.value = tipo
        _nivel.value = lvl
    }

    fun vuelveJugar() {
        _juegaOtraVez.value = true
    }
    fun vuelveJugarComplete() {
        _juegaOtraVez.value = false
    }
}