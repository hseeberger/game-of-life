/*
 * Copyright 2021 Heiko Seeberger
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package rocks.heikoseeberger.gameoflife

import java.awt.Color
import javax.swing.border.LineBorder
import scala.swing.{
  BoxPanel,
  Button,
  FlowPanel,
  GridPanel,
  Label,
  MainFrame,
  Orientation,
  SimpleSwingApplication
}
import scala.swing.event.MouseClicked

object SwingUi extends SimpleSwingApplication:

  private final class Top extends MainFrame:

    title = "Game of Life"
    resizable = false
    contents = new BoxPanel(Orientation.Vertical):
      contents += GenerationPanel(Generation(Set.empty), update(contents(0) = _))
      contents += Button("Next") {
        val nextGeneration = contents(0).asInstanceOf[GenerationPanel].generation.next
        update(contents(0) = _)(nextGeneration)
      }

    private def update(swapPanel: GenerationPanel => Unit)(generation: Generation): this.type =
      swapPanel(GenerationPanel(generation, update(swapPanel)))
      pack()

  private final class GenerationPanel(val generation: Generation, update: Generation => Unit)
      extends GridPanel(dimension, dimension):

    contents.appendAll(renderedCells)

    private def renderedCells =
      def render(cell: Cell, isAlive: Cell => Boolean) =
        new FlowPanel:
          border = LineBorder.createBlackLineBorder
          background = if isAlive(cell) then Color.blue else Color.white
          listenTo(mouse.clicks)
          reactions += { case _: MouseClicked =>
            val aliveCells =
              if isAlive(cell) then generation.aliveCells - cell
              else generation.aliveCells + cell
            update(Generation(aliveCells))
          }
      for
        y <- 0.until(dimension)
        x <- 0.until(dimension)
      yield render(Cell(x, y), generation.aliveCells.contains)

  private inline val dimension = 25

  override def top: MainFrame =
    Top()
