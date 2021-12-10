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
import cat.copernic.ngonzalezs.guessthenumber.databinding.MachineFragmentBinding
import cat.copernic.ngonzalezs.guessthenumber.pantallas.juego.HumanFragmentArgs

class MachineFragment : Fragment() {

    private lateinit var viewModel: MachineViewModel

    private lateinit var binding: MachineFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProvider(this).get(MachineViewModel::class.java)

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.machine_fragment,
            container,
            false
        )

        binding.machineViewModel = viewModel

        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.juegoAcaba.observe(viewLifecycleOwner, Observer<Boolean> { haAcabado ->
            if (haAcabado) juegoAcabado()
        })

        viewModel.juegaMal.observe(viewLifecycleOwner, Observer<Boolean> { jMal ->
            if (jMal) vueltaInicio()
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
            }
            1 -> {
                binding.txtTit.text = "Nivel 1-50"
            }
            2 -> {
                binding.txtTit.text = "Nivel 1-100"
            }
            else -> {
                binding.txtTit.text = "Error"
            }
        }

        if(viewModel.nivel == 4){
            viewModel.setLvl(lvl)
        }

        binding.btnMayor.setOnClickListener {
            viewModel.masGrande()
        }

        binding.btnMenor.setOnClickListener {
            viewModel.masPequeno()
        }

        binding.btnIgual.setOnClickListener {
            viewModel.cuandoAcaba()
            binding.txtTit.text = "He Acabado"
        }
    }

    private fun juegoAcabado() {
        val safeArgs: MachineFragmentArgs by navArgs()
        val lvl = safeArgs.lvl

        val cont = viewModel.cont.value

        val texto: String = "La maquina ha tardado $cont intentos"

        val action = MachineFragmentDirections.actionMachineFragmentToPunto(texto, lvl, "Maquina")

        NavHostFragment.findNavController(this).navigate(action)
        viewModel.cuandoAcabaFinal()
    }

    private fun vueltaInicio() {
        findNavController().navigate(MachineFragmentDirections.actionMachineFragmentToMenuDestination("Maquina"))
        viewModel.juegaMalFinal()
    }
}