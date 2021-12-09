package cat.copernic.ngonzalezs.guessthenumber.pantallas.juego

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HumanViewModel  : ViewModel() {

    private val _num = MutableLiveData<Int>()
    val num: LiveData<Int> get() = _num

    private var _comment = MutableLiveData<String>()
    val comment: LiveData<String> get() = _comment

    private val _juegoAcaba = MutableLiveData<Boolean>()
    val juegoAcaba: LiveData<Boolean> get() = _juegoAcaba

    private val min = 1

    private var nivel = 0

    fun setLvl(lvl: Int){
        nivel = lvl
        resetNum(nivel)
    }

    private fun resetNum(lvl: Int) {
        var max = 0
        when (lvl){
            0 -> max = 10
            1 -> max = 50
            2 -> max = 100
        }
        _num.value = (min..max).random()
    }

    fun entraNum(n: Int) {
        when{
            n == _num.value -> {
                _comment.value = "Es igual"
                cuandoAcaba()
            }
            n > _num.value!! -> _comment.value = "Es menor que $n"
            n < _num.value!! -> _comment.value = "Es mayor que $n"
        }
    }

    fun cuandoAcaba() {
        _juegoAcaba.value = true
    }

    fun cuandoAcabaFinal() {
        _juegoAcaba.value = false
    }
}