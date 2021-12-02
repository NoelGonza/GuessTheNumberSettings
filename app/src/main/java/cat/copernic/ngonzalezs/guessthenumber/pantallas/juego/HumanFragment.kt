package cat.copernic.ngonzalezs.guessthenumber.pantallas.juego

import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import cat.copernic.ngonzalezs.guessthenumber.R
import cat.copernic.ngonzalezs.guessthenumber.databinding.HumanFragmentBinding
import cat.copernic.ngonzalezs.guessthenumber.pantallas.juego.HumanFragmentArgs

class HumanFragment : Fragment() {

    private lateinit var viewModel: HumanViewModel

    private lateinit var binding: HumanFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProvider(this).get(HumanViewModel::class.java)

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.human_fragment,
            container,
            false
        )

        binding.humanViewModel = viewModel

        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.juegoAcaba.observe(viewLifecycleOwner, Observer<Boolean> { haAcabado ->
            if (haAcabado) juegoAcabado()
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val safeArgs: HumanFragmentArgs by navArgs()
        val lvl = safeArgs.lvl

        when (lvl) {
            0 -> {
                binding.txtTit.text = "Nivel 1-10"
                viewModel.setLvl(lvl)
            }
            1 -> {
                binding.txtTit.text = "Nivel 1-50"
                viewModel.setLvl(lvl)
            }
            2 -> {
                binding.txtTit.text = "Nivel 1-100"
                viewModel.setLvl(lvl)
            }
            else -> {
                binding.txtTit.text = "Error"
            }
        }

        binding.btnEnviar.setOnClickListener {
            val num = binding.textboxNum.text.toString().toInt()
            viewModel.entraNum(num)
        }

        binding.chrTiempo.start()
    }

    private fun juegoAcabado() {
        binding.chrTiempo.stop()

        val tiempo = SystemClock.elapsedRealtime() - binding.chrTiempo.base;
        val minutos: Int = (tiempo / 1000 / 60).toInt()
        val segundos: Int = (tiempo / 1000 % 60).toInt()


    }
}