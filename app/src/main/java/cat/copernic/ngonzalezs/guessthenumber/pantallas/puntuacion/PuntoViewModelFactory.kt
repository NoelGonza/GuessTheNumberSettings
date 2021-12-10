package cat.copernic.ngonzalezs.guessthenumber.pantallas.puntuacion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PuntoViewModelFactory(private val txtFin: String, private val lvl: Int, private val tipo: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PuntoViewModel::class.java)) {
            return PuntoViewModel(txtFin, lvl, tipo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}