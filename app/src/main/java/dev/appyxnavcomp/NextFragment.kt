package dev.appyxnavcomp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.bumble.appyx.core.integration.NodeHost
import dev.appyxnavcomp.ui.theme.AppyxNavComponentTheme

class NextFragment : Fragment() {
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val integrationPoint = (requireActivity() as MainActivity).integrationPoint
    return ComposeView(inflater.context).apply {
      setContent {
        AppyxNavComponentTheme {
          NodeHost(integrationPoint = integrationPoint, factory = ::RootNode)
        }
      }
    }
  }
}
