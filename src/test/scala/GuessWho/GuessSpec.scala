package GuessWho

import org.scalatest.FlatSpec

/** name: String,
 hasHair: Boolean,
 hasFacialHair: Boolean,
 hasGlasses: Boolean,
 hasHat: Boolean,
 gender:Gender,
 eyeColour: EyeColour,
 hairColour: HairColour */

class GuessSpec extends FlatSpec {
  val jamie: Character = Character(name = "Jamie", hasHair = false, hasFacialHair = true, hasGlasses = true, hasHat = true, gender = GuessWho.Gender, eyeColour = Blue, hairColour = NONE)
  "guessHasHair" should "return true if character has hair" in {
    val result:
  }
}
