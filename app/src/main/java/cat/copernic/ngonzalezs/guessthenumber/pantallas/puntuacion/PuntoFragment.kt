package cat.copernic.ngonzalezs.guessthenumber.pantallas.puntuacion

import android.content.Intent
import android.os.Bundle
import android.os.Process
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.preference.PreferenceManager
import cat.copernic.ngonzalezs.guessthenumber.R
import cat.copernic.ngonzalezs.guessthenumber.databinding.PuntoFragmentBinding
import cat.copernic.ngonzalezs.guessthenumber.pantallas.inicio.MainFragmentDirections
import kotlinx.android.synthetic.main.punto_fragment.*

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
        val btn_main = binding.btnInicio
        val btn_option = binding.btnOptions
        val btn_cierre = binding.btnCerrar

        val prefs = PreferenceManager.getDefaultSharedPreferences(context).getAll()

        val modo = prefs["type"] as String?
        val nivel2 = prefs["level"] as String?

        var lvl = 0
        var action = PuntoFragmentDirections.actionPuntoToMainFragment()
        var action2 = PuntoFragmentDirections.actionPuntoToMachineFragment(0)
        val action3 = PuntoFragmentDirections.actionPuntoToSettingsFragment()

        when (nivel2) {
            "1-50" -> {
                lvl = 1
            }
            "1-100" -> {
                lvl = 2
            }
        }

        when (modo) {
            "Humano" -> {
                action2 = PuntoFragmentDirections.actionPuntoToHumanFragment(lvl)
            }
            "Maquina" -> {
                action2 = PuntoFragmentDirections.actionPuntoToMachineFragment(lvl)
            }
        }

        btn_main.setOnClickListener {
            findNavController().navigate(action)
        }

        btn_option.setOnClickListener {
            findNavController().navigate(action3)
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

        btn_cierre.setOnClickListener{
            activity?.finishAndRemoveTask()
        }

        return binding.root
    }
}
