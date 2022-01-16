package cat.copernic.ngonzalezs.guessthenumber.pantallas.inicio

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import androidx.navigation.fragment.findNavController
import cat.copernic.ngonzalezs.guessthenumber.R
import cat.copernic.ngonzalezs.guessthenumber.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    private lateinit var binding: MainFragmentBinding

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val prefs = PreferenceManager.getDefaultSharedPreferences(context).getAll()

        /*Ver listado de preferencias
        prefs?.forEach {
            Log.d("Preferences values",it.key + ": " + it.value)
        }*/

        val modo = prefs["type"] as String?
        val nivel = prefs["level"] as String?

        binding.preferencia.text = modo + " Lvl " + nivel

        val btn_ply = binding.btnPlay
        val btn_opc = binding.btnOpciones

        var lvl = 0
        var action = MainFragmentDirections.actionMainFragmentToHumanFragment(lvl)

        when (nivel) {
            "1-50" -> {
                lvl = 1
            }
            "1-100" -> {
                lvl = 2
            }
        }

        when (modo) {
            "Humano" -> {
                action = MainFragmentDirections.actionMainFragmentToHumanFragment(lvl)
            }
            "Maquina" -> {
                action = MainFragmentDirections.actionMainFragmentToMachineFragment(lvl)
            }
        }


        btn_ply.setOnClickListener {
            findNavController().navigate(action)
        }

        val action2 = MainFragmentDirections.actionMainFragmentToSettingsFragment()
        btn_opc.setOnClickListener{
            findNavController().navigate(action2)
        }
    }

}