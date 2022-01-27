package cat.copernic.ngonzalezs.guessthenumber.pantallas.corroutine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import kotlin.random.Random as Random

sealed class Numero {
    data class Exitoso(val num: Int): Numero()
    data class Error(val ex: Exception): Numero()
}

suspend fun rndomNum(izq: Int, der: Int): Numero {

    return withContext(Dispatchers.Main) {

        var num = 0

        try {
            num = Random.nextInt(izq, der)
            Numero
                .Exitoso(num)
        }catch (e: NoSuchElementException){
            Numero
                .Error(NoSuchElementException("Operación no válida"))
        }
    }

}