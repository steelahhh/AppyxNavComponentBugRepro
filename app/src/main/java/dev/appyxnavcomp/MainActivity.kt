package dev.appyxnavcomp

import android.os.Bundle
import com.bumble.appyx.core.integrationpoint.NodeActivity

class MainActivity : NodeActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.container)
  }
}
