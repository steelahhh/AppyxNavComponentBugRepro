package dev.appyxnavcomp

import android.os.Parcelable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.composable.Children
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.bumble.appyx.core.node.ParentNode
import com.bumble.appyx.navmodel.backstack.BackStack
import com.bumble.appyx.navmodel.backstack.operation.push
import com.bumble.appyx.navmodel.backstack.transitionhandler.rememberBackstackSlider
import kotlinx.parcelize.Parcelize

class RootNode(
  buildContext: BuildContext,
  private val backStack: BackStack<Routing> =
    BackStack(
      initialElement = Routing.Child("root"),
      savedStateMap = buildContext.savedStateMap,
    )
) : ParentNode<RootNode.Routing>(buildContext = buildContext, navModel = backStack) {
  sealed class Routing : Parcelable {
    @Parcelize data class Child(val name: String) : Routing()
  }

  @Composable
  override fun View(modifier: Modifier) {
    Children(
      modifier = modifier,
      navModel = backStack,
      transitionHandler =
        rememberBackstackSlider(transitionSpec = { spring(stiffness = Spring.StiffnessMediumLow) }),
    )
  }

  override fun resolve(routing: Routing, buildContext: BuildContext): Node {
    return when (routing) {
      is Routing.Child ->
        ChildNode(
          name = routing.name,
          push = { backStack.push(Routing.Child(it)) },
          buildContext = buildContext
        )
    }
  }
}
