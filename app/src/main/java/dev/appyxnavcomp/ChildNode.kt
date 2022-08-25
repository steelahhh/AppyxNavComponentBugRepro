package dev.appyxnavcomp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.bumble.appyx.core.state.MutableSavedStateMap
import dev.appyxnavcomp.ui.theme.Purple200
import dev.appyxnavcomp.ui.theme.Purple500
import dev.appyxnavcomp.ui.theme.Purple700
import kotlin.random.Random

class ChildNode(
  private val name: String,
  private val push: (String) -> Unit,
  buildContext: BuildContext
) : Node(buildContext = buildContext) {

  private val colors =
    listOf(
      Purple200,
      Purple500,
      Purple700,
      Color.Red,
      Color.Blue,
      Color.Cyan,
      Color.Yellow,
      Color.Gray,
    )

  private val colorIndex =
    buildContext.savedStateMap?.get(KEY_COLOR_INDEX) as? Int ?: Random.nextInt(colors.size)
  private val color = colors[colorIndex]

  override fun onSaveInstanceState(state: MutableSavedStateMap) {
    super.onSaveInstanceState(state)
    state[KEY_COLOR_INDEX] = colorIndex
  }

  @Composable
  override fun View(modifier: Modifier) {
    Box(
      modifier = modifier.fillMaxSize().background(color = color, shape = RoundedCornerShape(6.dp))
    ) {
      Column(modifier = Modifier.padding(24.dp)) {
        Text("Child ($name).")
        Row {
          // Local UI state should be saved too (both in backstack and onSaveInstanceState)
          var counter by rememberSaveable { mutableStateOf(0) }
          Text(text = "Counter $counter", modifier = Modifier.align(CenterVertically))
          Spacer(modifier = Modifier.width(16.dp))
          Button(onClick = { counter++ }) { Text("Increment") }
        }
        Row { Button(onClick = { navigateUp() }) { Text("Go up") } }
        Row { Button(onClick = { push(Random.nextInt().toString()) }) { Text("Push") } }
      }
    }
  }

  companion object {
    private const val KEY_COLOR_INDEX = "ColorIndex"
  }
}

@Preview
@Composable
fun ChildPreview() {
  ChildNode(name = "1", push = {}, buildContext = BuildContext.root(null)).Compose()
}
