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

final class CellTests extends FunSuite:

  test("neighbours") {
    assertEquals(
      Cell(10, 0).neighbours,
      Set(
        Cell(9, -1),
        Cell(10, -1),
        Cell(11, -1),
        Cell(9, 0),
        Cell(11, 0),
        Cell(9, 1),
        Cell(10, 1),
        Cell(11, 1),
      )
    )
  }
