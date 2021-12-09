package cat.copernic.ngonzalezs.guessthenumber.pantallas.juego

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MachineViewModel  : ViewModel() {

    private val _num = MutableLiveData<Int>()
    val num: LiveData<Int> get() = _num

    private val _cont = MutableLiveData<Int>()
    val cont: LiveData<Int> get() = _cont

    private val _pivI = MutableLiveData<Int>()
    val pivI: LiveData<Int> get() = _pivI

    private val _pivD = MutableLiveData<Int>()
    val pivD: LiveData<Int> get() = _pivD

    private val _juegoAcaba = MutableLiveData<Boolean>()
    val juegoAcaba: LiveData<Boolean> get() = _juegoAcaba

    private val _juegaMal = MutableLiveData<Boolean>()
    val juegaMal: LiveData<Boolean> get() = _juegaMal

    private var nivel = 0

    fun setLvl(lvl: Int){
        nivel = lvl
        _cont.value = 0
        when (lvl){
            0 -> _pivD.value = 10
            1 -> _pivD.value = 50
            2 -> _pivD.value = 100
        }
        _pivI.value = 1
        rndNum()
    }

    private fun rndNum() {
        try {
            _num.value = (pivD.value?.let { pivI.value?.rangeTo(it) })?.random()
        }catch (e: NoSuchElementException){
            _juegaMal.value = true
        }
    }

    fun masPequeno() {
        _pivD.value = _num.value?.minus(1)
        _cont.value = _cont.value?.plus(1)
        rndNum()
    }

    fun masGrande() {
        _pivI.value = _num.value?.plus(1)
        _cont.value = _cont.value?.plus(1)
        rndNum()
    }

    fun cuandoAcaba() {
        _juegoAcaba.value = true
    }

    fun cuandoAcabaFinal() {
        _juegoAcaba.value = false
    }

    fun juegaMalFinal() {
        _juegaMal.value = false
    }
}