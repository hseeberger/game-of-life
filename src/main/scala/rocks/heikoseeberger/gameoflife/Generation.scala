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

final class Generation(aliveCells: Set[Cell]):

  def next: Generation =
    val stayingAlive =
      aliveCells.filter(hasTwoOrThreeAliveNeighbours)
    val wakingFromDead =
      aliveCells
        .flatMap(deadNeighbours)
        .filter(aliveNeighbours(_).size == 3)
    new Generation(stayingAlive ++ wakingFromDead)

  private def hasTwoOrThreeAliveNeighbours(cell: Cell) =
    2.to(3).contains(aliveNeighbours(cell).size)

  private def aliveNeighbours(cell: Cell) =
    cell.neighbours.filter(aliveCells.contains)

  private def deadNeighbours(cell: Cell) =
    cell.neighbours.filterNot(aliveCells.contains)
