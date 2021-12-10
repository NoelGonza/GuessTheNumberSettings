package cat.copernic.ngonzalezs.guessthenumber.pantallas.puntuacion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import cat.copernic.ngonzalezs.guessthenumber.R
import cat.copernic.ngonzalezs.guessthenumber.databinding.PuntoFragmentBinding

class PuntoFragment : Fragment() {

    private lateinit var viewModel: PuntoViewModel
    private lateinit var viewModelFactory: PuntoViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: PuntoFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.punto_fragment,
            container,
            false
        )

        viewModelFactory = PuntoViewModelFactory(PuntoFragmentArgs.fromBundle(requireArguments()).txtFin,
            PuntoFragmentArgs.fromBundle(requireArguments()).lvl,
            PuntoFragmentArgs.fromBundle(requireArguments()).tipo)

        viewModel = ViewModelProvider(this, viewModelFactory).get(PuntoViewModel::class.java)

        binding.puntoViewModel = viewModel

        binding.lifecycleOwner = viewLifecycleOwner

        val btn_juego = binding.btnJugar
        val btn_main = binding.btnModo
        val btn_Lvl = binding.btnNivel

        var action = PuntoFragmentDirections.actionPuntoToMenuDestination("Humano")
        var action2 = PuntoFragmentDirections.actionPuntoToMachineFragment(0)

        val action3 = PuntoFragmentDirections.actionPuntoToMainFragment()
        btn_main.setOnClickListener {
            findNavController().navigate(action3)
        }

        val nivel = viewModel.nivel.value

        if (viewModel.tipoJuego.value == "Humano"){
            action = PuntoFragmentDirections.actionPuntoToMenuDestination("Humano")
            action2 = PuntoFragmentDirections.actionPuntoToHumanFragment(nivel!!)
        }else{
            action = PuntoFragmentDirections.actionPuntoToMenuDestination("Maquina")
            action2 = PuntoFragmentDirections.actionPuntoToMachineFragment(nivel!!)
        }

        btn_Lvl.setOnClickListener {
            findNavController().navigate(action)
        }

        btn_juego.setOnClickListener {
            viewModel.vuelveJugar()
        }

        viewModel.juegaOtraVez.observe(viewLifecycleOwner, Observer { vuelveJugar ->
            if (vuelveJugar) {
                findNavController().navigate(action2)
                viewModel.vuelveJugarComplete()
            }
        })

        return binding.root
    }
}
