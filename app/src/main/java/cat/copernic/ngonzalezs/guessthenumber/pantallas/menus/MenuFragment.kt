package cat.copernic.ngonzalezs.guessthenumber.pantallas.menus

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import cat.copernic.ngonzalezs.guessthenumber.R
import cat.copernic.ngonzalezs.guessthenumber.databinding.MenuFragmentBinding
import cat.copernic.ngonzalezs.guessthenumber.pantallas.inicio.MainFragmentDirections

class MenuFragment  : Fragment() {

    private lateinit var binding: MenuFragmentBinding

    companion object {
        fun newInstance() = MenuFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MenuFragmentBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val safeArgs: MenuFragmentArgs by navArgs()
        val nom = safeArgs.tip

        val btn_10 = binding.btnLvl10
        val btn_50 = binding.btnLvl50
        val btn_100 = binding.btnLvl100

        var action = MenuFragmentDirections.actionMenuDestinationToHumanFragment(0)
        var action2 = MenuFragmentDirections.actionMenuDestinationToHumanFragment(1)
        var action3 = MenuFragmentDirections.actionMenuDestinationToHumanFragment(2)

        btn_10.text = "$nom LVL 10"
        btn_50.text = "$nom LVL 50"
        btn_100.text = "$nom LVL 100"

        if (nom == "Maquina"){

        }

        btn_10.setOnClickListener {
            findNavController().navigate(action)
        }
        btn_50.setOnClickListener{
            findNavController().navigate(action2)
        }
        btn_100.setOnClickListener{
            findNavController().navigate(action3)
        }
    }
}