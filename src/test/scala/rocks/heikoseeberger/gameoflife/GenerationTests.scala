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

import munit.FunSuite

final class GenerationTests extends FunSuite:

  test("empty") {
    assertEquals(Generation(Set.empty).next.aliveCells, Set.empty)
  }

  test("block") {
    val block = Set(Cell(0, 0), Cell(1, 0), Cell(0, 1), Cell(1, 1))
    assertEquals(Generation(block).next.aliveCells, block)
  }

  test("oscillator") {
    val horizontalTriple = Set(Cell(-1, 0), Cell(0, 0), Cell(1, 0))
    val verticalTriple   = Set(Cell(0, -1), Cell(0, 0), Cell(0, 1))
    assertEquals(Generation(horizontalTriple).next.aliveCells, verticalTriple)
  }
